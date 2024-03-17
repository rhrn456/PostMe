package com.spring.postme.service.impl;

import com.spring.postme.model.Post;
import java.util.List;

public interface PostServiceImpl {

	List<Post> findAll();

	Post findById(Integer id);

	void savePost(Post post);

	void updatePost(Integer id, Post post);

	void deletePost(Integer id);

	List<Post> findPostsByPage(int page, int pageSize);

	List<Post> findSearchedPostsByPage(int page, int pageSize, String query);

	int countPosts();

	void updatePost(Post post);

	List<Post> searchPosts(String query);

	int searchPostsCount(String query);

}
