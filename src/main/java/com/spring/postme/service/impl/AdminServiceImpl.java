package com.spring.postme.service.impl;

public interface AdminServiceImpl {
	
	int countUsers();

	int countPosts();

	int countComments();

	void deleteAllUsers();

	void deleteAllPosts();

	void deleteAllComments();
	
}
