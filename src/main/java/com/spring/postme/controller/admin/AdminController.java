package com.spring.postme.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.postme.model.Post;
import com.spring.postme.model.User;
import com.spring.postme.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_MAIN_VIEW = "AdminMain";
    private static final String ADMIN_USER_VIEW = "AdminUser";
    private static final String ADMIN_POST_VIEW = "AdminPost";
    private static final String REDIRECT_ADMIN_USERS = "redirect:/admin/users";
    private static final String REDIRECT_ADMIN_POSTS = "redirect:/admin/posts";
    private static final String REDIRECT_ADMIN_DASHBOARD = "redirect:/admin/dashboard";

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalUsers", adminService.countUsers());
        model.addAttribute("totalPosts", adminService.countPosts());
        model.addAttribute("totalComments", adminService.countComments());

        return ADMIN_MAIN_VIEW;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> userList = adminService.getUserList();
        model.addAttribute("usersList", userList);

        return ADMIN_USER_VIEW;
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> postList = adminService.getPostList();
        model.addAttribute("postsList", postList);

        return ADMIN_POST_VIEW;
    }

    @PostMapping("/users/promote/{userId}")
    public String promoteUserToAdmin(@PathVariable Integer userId, @RequestParam("adminStatus") boolean adminStatus,
                                     RedirectAttributes redirectAttributes) {
        adminService.updateUserAdminStatus(userId, adminStatus);
        addRedirectMessage(redirectAttributes, "사용자의 관리자 상태가 변경되었습니다.");

        return REDIRECT_ADMIN_USERS;
    }

    @PostMapping("/users/update/{userId}")
    public String updateUser(@PathVariable Integer userId, @RequestParam("name") String name,
                             @RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        User user = adminService.getUserById(userId);
        user.setName(name);
        user.setEmail(email);
        adminService.editUser(user);
        addRedirectMessage(redirectAttributes, "사용자 정보가 업데이트되었습니다.");

        return REDIRECT_ADMIN_USERS;
    }

    @PostMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId, RedirectAttributes redirectAttributes) {
        adminService.deleteUserByUserId(userId);
        addRedirectMessage(redirectAttributes, "사용자가 삭제되었습니다.");

        return REDIRECT_ADMIN_USERS;
    }

    @PostMapping("/posts/delete/{postId}")
    public String deletePost(@PathVariable Integer postId, RedirectAttributes redirectAttributes) {
        adminService.deleteCommentByPostId(postId);
        adminService.deletePostById(postId);
        addRedirectMessage(redirectAttributes, "게시물이 삭제되었습니다.");

        return REDIRECT_ADMIN_POSTS;
    }

    @PostMapping("/deleteAllData")
    public String deleteAllData(RedirectAttributes redirectAttributes) {
        adminService.deleteAllData();
        addRedirectMessage(redirectAttributes, "모든 데이터가 삭제되었습니다.");

        return REDIRECT_ADMIN_DASHBOARD;
    }

    @PostMapping("/insertSampleData")
    public String insertSampleData(RedirectAttributes redirectAttributes) {
    	adminService.resetAllTables();
        adminService.insertSampleData();
        addRedirectMessage(redirectAttributes, "샘플 데이터가 추가되었습니다.");

        return "redirect:/admin/settings";
    }

    private void addRedirectMessage(RedirectAttributes attributes, String message) {
        attributes.addFlashAttribute("message", message);
    }
}
