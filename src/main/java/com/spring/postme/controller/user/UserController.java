package com.spring.postme.controller.user;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.postme.model.User;
import com.spring.postme.service.user.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login.do")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		User user = userService.getUserByUsername(username);

		if (user != null && BCrypt.checkpw(password, user.getPassword())) {
			session.setAttribute("user", user.getUsername());
			session.setAttribute("loggedInUserId", user.getId());
			session.setAttribute("isAdmin", user.getIsAdmin());
			System.out.println(user.getIsAdmin());
			if (user.getIsAdmin()) {
				return "redirect:/admin/dashboard";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login?error=true";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/register.do")
	public String register(@ModelAttribute User newUser) {
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		System.out.println(newUser);
		boolean result = userService.insertUser(newUser);
		if (result) {
			return "redirect:/login";
		} else {
			return "redirect:/register?error=true";
		}
	}

}