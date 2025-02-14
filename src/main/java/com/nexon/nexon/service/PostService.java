package com.nexon.nexon.service;

import java.util.List;

import com.nexon.nexon.entities.Post;

public interface PostService {
    Post createPost(Post post, String username);
    Post updatePost(Long postId, Post post, String username);
    void deletePost(Long postId, String username);
    List<Post> getAllPosts(String orderBy);
}
