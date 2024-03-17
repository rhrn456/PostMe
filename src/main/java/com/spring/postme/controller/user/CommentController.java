package com.spring.postme.controller.user;

import com.spring.postme.model.Comment;
import com.spring.postme.service.user.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/add")
	public String addComment(Comment comment) {
		commentService.addComment(comment);
		return "redirect:/posts/" + comment.getPostId();
	}

	@GetMapping("/{id}")
	public Comment getComment(@PathVariable Integer id) {
		return commentService.getCommentById(id);
	}

	@PostMapping("/update/{id}")
	public String updateComment(@PathVariable("id") int commentId, Comment comment) {
		comment.setId(commentId);
		commentService.updateComment(comment);
		return "redirect:/posts/" + comment.getPostId();
	}

	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable Integer id, @RequestParam Integer postId) {
		commentService.deleteComment(id);
		return "redirect:/posts/" + postId;
	}
}
