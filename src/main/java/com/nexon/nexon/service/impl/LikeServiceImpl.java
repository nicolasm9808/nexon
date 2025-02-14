package com.nexon.nexon.service.impl;

import com.nexon.nexon.entities.Like;
import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.LikeRepository;
import com.nexon.nexon.repositories.PostRepository;
import com.nexon.nexon.service.LikeService;
import com.nexon.nexon.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public boolean toggleLike(Long postId, String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            // Unlike the post
            likeRepository.delete(existingLike.get());
            post.setTotalLikes(post.getTotalLikes() - 1);
            postRepository.save(post);
            return false; // Like removed
        } else {
            // Like the post
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            post.setTotalLikes(post.getTotalLikes() + 1);
            postRepository.save(post);
            return true; // Like added
        }
    }
}
