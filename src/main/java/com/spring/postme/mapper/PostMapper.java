package com.spring.postme.mapper;

import com.spring.postme.model.Post;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

	// Create
	void insertPost(Post post);

	// Read
	List<Post> findAll();

	Post findById(Integer id);

	List<Post> findPostsByPage(Map<String, Object> params);

	List<Post> searchPosts(String query);

	List<Post> findSearchedPostsByPage(Map<String, Object> params);

	int countPosts();

	int searchPostsCount(String query);

	// Update
	void updatePost(Post post);

	// Delete
	int deleteById(Integer id);

	int deleteByPostId(Integer postId);

	int deleteAllPosts();

}
