package com.spring.postme.service.impl;

import com.spring.postme.model.Comment;
import java.util.List;

public interface CommentServiceImpl {
	
	void addComment(Comment comment);

	List<Comment> getCommentsByPostId(Integer postId);

	Comment getCommentById(Integer id);

	void updateComment(Comment comment);

	void deleteComment(Integer id);

	int countComments();

}
