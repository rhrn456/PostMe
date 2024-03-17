package com.spring.postme.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // Read
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    // Create
    public boolean insertUser(User newUser) {
        try {
            userMapper.insertUser(newUser); 
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    // Read
    @Override
    public int countUsers() {
        return userMapper.countUsers();
    }
}
