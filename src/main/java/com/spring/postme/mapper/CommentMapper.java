package com.spring.postme.mapper;

import java.util.List;
import com.spring.postme.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    // Create
    void insertComment(Comment comment);

    // Read
    List<Comment> findCommentsByPostId(Integer postId);
    Comment findCommentById(Integer id);
    int countComments();

    // Update
    void updateComment(Comment comment);

    // Delete
    void deleteComment(Integer id);
    void deleteByPostId(Integer postId);
    int deleteAllComments();
}
