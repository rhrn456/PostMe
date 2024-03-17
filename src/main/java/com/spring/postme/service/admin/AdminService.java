package com.spring.postme.service.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.mapper.PostMapper;
import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.Comment;
import com.spring.postme.model.Post;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.AdminServiceImpl;

@Service
public class AdminService implements AdminServiceImpl {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminService(PostMapper postMapper, CommentMapper commentMapper, UserMapper userMapper, JdbcTemplate jdbcTemplate) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    // User 
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    public void updateUserAdminStatus(Integer userId, boolean adminStatus) {
        userMapper.updateUserAdminStatus(userId, adminStatus);
    }

    public void deleteUserByUserId(Integer userId) {
        userMapper.deleteUserById(userId);
    }

    // Post 
    public List<Post> getPostList() {
        return postMapper.findAll();
    }

    public void deletePostById(Integer postId) {
        postMapper.deleteByPostId(postId);
    }

    // Comment 
    public void deleteCommentByPostId(Integer postId) {
        commentMapper.deleteByPostId(postId);
    }

    // READ - COUNT
    @Override
    public int countUsers() {
        return userMapper.countUsers();
    }

    @Override
    public int countPosts() {
        return postMapper.countPosts();
    }

    @Override
    public int countComments() {
        return commentMapper.countComments();
    }

    // DELETE
    @Override
    public void deleteAllUsers() {
        userMapper.deleteAllUsers();
    }

    @Override
    public void deleteAllPosts() {
        postMapper.deleteAllPosts();
    }

    @Override
    public void deleteAllComments() {
        commentMapper.deleteAllComments();
    }

    @Transactional
    public void deleteAllData() {
        deleteAllComments();
        deleteAllPosts();
        deleteAllUsers();
    }

    // SAMPLE DATA
    @Transactional
    public void insertSampleData() {
        insertSampleUsers();
        insertSamplePosts();
        insertSampleComments();
    }

    private void insertSampleUsers() {
        for (int i = 1; i <= 100; i++) {
            User user = createSampleUser(i);
            if (i == 1) {
                userMapper.insertAdmin(user);
            } else {
                userMapper.insertUser(user);
            }
        }
    }

    private User createSampleUser(int index) {
        String hashedPassword = BCrypt.hashpw("password" + index, BCrypt.gensalt());
        String username = (index == 1) ? "admin" : "user" + index;
        String email = (index == 1) ? "admin@example.com" : "user" + index + "@example.com";
        boolean isAdmin = index == 1;
        return new User(null, username, hashedPassword, "User" + index, email, isAdmin, LocalDateTime.now());
    }

    private void insertSamplePosts() {
        for (int i = 1; i <= 100; i++) {
            Post post = Post.builder()
                            .userId(i)
                            .title("Sample Title " + i)
                            .content("Sample content for post " + i)
                            .createdAt(LocalDateTime.now())
                            .modifiedAt(LocalDateTime.now())
                            .build();
            postMapper.insertPost(post);
        }
    }

    private void insertSampleComments() {
        for (int i = 1; i <= 100; i++) {
            Comment comment = new Comment(null, i, i, "Sample comment " + i, LocalDateTime.now(), LocalDateTime.now());
            commentMapper.insertComment(comment);
        }
    }

    public void resetAllTables() {
        resetAutoIncrement("users");
        resetAutoIncrement("posts");
        resetAutoIncrement("comments");
    }

    private void resetAutoIncrement(String tableName) {
        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        jdbcTemplate.execute(sql);
    }
}
