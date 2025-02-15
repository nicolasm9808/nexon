package com.nexon.nexon.service.impl;

import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.FollowRepository;
import com.nexon.nexon.repositories.PostRepository;
import com.nexon.nexon.service.PostService;
import com.nexon.nexon.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FollowRepository followRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.followRepository = followRepository;
    }

    @Override
    public Post createPost(Post post, String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional
    
        // Set the user to the post
        post.setUser(user);
        post.setCreatedAt(new Date()); // Set current date for post creation
        post.setTotalComments(0L); // Initialize comment count
        post.setTotalLikes(0L); // Initialize like count
        return postRepository.save(post);
    }
    

    @Override
    public Post updatePost(Long postId, Post post, String username) {
        // Fetch the post by its ID
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Only allow update if the post was created by the authenticated user
        if (!existingPost.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own posts");
        }

        // Update only the fields that were provided
        if (post.getText() != null) {
            existingPost.setText(post.getText());
        }
        if (post.getImageUrl() != null) {
            existingPost.setImageUrl(post.getImageUrl());
        }
        if (post.getTags() != null) {
            existingPost.setTags(post.getTags());
        }
    
        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Long postId, String username) {
        // Fetch the post by its ID
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Only allow deletion if the post was created by the authenticated user
        if (!existingPost.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own posts");
        }

        // Delete the post
        postRepository.delete(existingPost);
    }

    @Override
    public List<Post> getAllPosts(String orderBy) {
        // Order posts based on the query parameter
        if ("relevance".equalsIgnoreCase(orderBy)) {
            return postRepository.findPostsOrderedByRelevance(); // Custom query to order by relevance
        } else if ("asc".equalsIgnoreCase(orderBy)) {
            return postRepository.findAllByOrderByCreatedAtAsc(); // Order by ascending date
        } else if ("desc".equalsIgnoreCase(orderBy)) {
            return postRepository.findAllByOrderByCreatedAtDesc(); // Order by descending date
        }
        // Default: Return all posts
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public List<Post> getFeed(String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional
        List<Long> followingUserIds = followRepository.findFollowingIdsByFollower(user);
        return postRepository.findByUserIdInOrderByCreatedAtDesc(followingUserIds);
    }
}
