package com.spring.postme.service.impl;

import com.spring.postme.model.User;

public interface UserServiceImpl {

	User getUserByUsername(String username);

	int countUsers();

}
