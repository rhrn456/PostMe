package com.spring.postme.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.postme.model.Comment;
import com.spring.postme.model.Post;
import com.spring.postme.model.PostFile;
import com.spring.postme.service.user.CommentService;
import com.spring.postme.service.user.PostFileService;
import com.spring.postme.service.user.PostService;

@Controller
public class PostController {

	private final PostService postService;
	private final CommentService commentService;
	private final PostFileService postFileService;

	private static final int POSTS_PER_PAGE = 6;

	@Autowired
	public PostController(PostService postService, 
						  CommentService commentService, 
						  PostFileService postFileService) {
		this.postService = postService;
		this.commentService = commentService;
		this.postFileService = postFileService;
	}

	@GetMapping("/")
	public String listPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<Post> posts = postService.findPostsByPage(page, POSTS_PER_PAGE);
		int totalPosts = postService.countPosts();
		int totalPages = (int) Math.ceil((double) totalPosts / POSTS_PER_PAGE);

		model.addAttribute("posts", posts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "main";
	}

	@GetMapping("/posts/search")
	public String searchPosts(@RequestParam String query, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		List<Post> searchResults = postService.findSearchedPostsByPage(page, POSTS_PER_PAGE, query);
		int totalPosts = postService.searchPostsCount(query);
		int totalPages = (int) Math.ceil((double) totalPosts / POSTS_PER_PAGE);

		model.addAttribute("posts", searchResults);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "postListView";
	}

	@GetMapping("/posts/{id}")
	public String showPostDetail(@PathVariable Integer id, Model model) {
		Post post = postService.findById(id);
		List<Comment> comments = commentService.getCommentsByPostId(id);

		PostFile fileInfo = postFileService.getAttachmentFileByPostId(id);

		model.addAttribute("fileInfo", fileInfo);
		model.addAttribute("post", post);
		model.addAttribute("commentsList", comments);

		return "postDetail";
	}

	@PostMapping("/posts/add")
	public String savePosts(MultipartFile file, @ModelAttribute("post") Post post, HttpSession session)
			throws Exception {
		Integer userId = (Integer) session.getAttribute("loggedInUserId");
		post.setUserId(userId);
		postService.savePost(post);
		if (file.getOriginalFilename() != null) {
			postFileService.insertAttachmentFile(file, post.getId(), post.getUserId());
		}
		return "redirect:/";
	}

	@GetMapping("/posts/delete/{postId}")
	public String deletePost(@PathVariable("postId") Integer postId) {
		postService.deletePost(postId);

		return "redirect:/";
	}

	@PostMapping("/posts/update/{id}")
	public String updatePost(@PathVariable("id") Integer id, @ModelAttribute Post post,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("loggedInUserId");
		post.setId(id);
		post.setUserId(userId);

		postFileService.deleteFileByPostId(id);

		if (!file.isEmpty()) {
			postFileService.insertAttachmentFile(file, id, userId);
		}

		postService.updatePost(post);
		redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다.");
		return "redirect:/posts/" + id;
	}
}
