package com.spring.postme.mapper;

import com.spring.postme.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	// Create
	void insertUser(User newUser);

	void insertAdmin(User newAdmin);

	// Read
	User getUserByUsername(@Param("username") String username);

	User getUserById(@Param("userId") Integer userId);

	List<User> getUserList();

	int countUsers();

	// Update
	void updateUser(User user);

	void updateUserAdminStatus(@Param("userId") Integer userId, @Param("adminStatus") boolean adminStatus);

	// Delete
	void deleteUserById(@Param("userId") Integer userId);

	int deleteAllUsers();

}
