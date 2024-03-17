package com.spring.postme.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.mapper.PostMapper;
import com.spring.postme.model.Post;
import com.spring.postme.service.impl.PostServiceImpl;

@Service
public class PostService implements PostServiceImpl {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public PostService(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    // Create
    @Override
    public void savePost(Post post) {
        if (post.getId() == null) {
            postMapper.insertPost(post);
        } else {
            postMapper.updatePost(post);
        }
    }

    // Read
    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    @Override
    public Post findById(Integer id) {
        return postMapper.findById(id);
    }

    @Override
    public List<Post> findPostsByPage(int page, int pageSize) {
        return getPagedPosts(page, pageSize, null);
    }

    @Override
    public List<Post> findSearchedPostsByPage(int page, int pageSize, String query) {
        return getPagedPosts(page, pageSize, query);
    }

    @Override
    public List<Post> searchPosts(String query) {
        return postMapper.searchPosts(query);
    }

    @Override
    public int countPosts() {
        return postMapper.countPosts();
    }

    @Override
    public int searchPostsCount(String query) {
        return postMapper.searchPostsCount(query);
    }

    // Update
    @Override
    public void updatePost(Integer id, Post post) {
        post.setId(id);
        postMapper.updatePost(post);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    // Delete
    @Override
    @Transactional
    public void deletePost(Integer postId) {
        commentMapper.deleteByPostId(postId);
        postMapper.deleteByPostId(postId);
    }
    
    // Extracted Method
    private List<Post> getPagedPosts(int page, int pageSize, String query) {
        int offset = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        if (query != null) {
            params.put("query", query);
            return postMapper.findSearchedPostsByPage(params);
        } else {
            return postMapper.findPostsByPage(params);
        }
    }
}
