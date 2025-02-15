package com.nexon.nexon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexon.nexon.entities.Follow;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.FollowRepository;
import com.nexon.nexon.repositories.UserRepository;
import com.nexon.nexon.service.FollowService;
import com.nexon.nexon.service.UserService;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void followUser(Long followingId, String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional
        if (followingId.equals(user.getId())) {
            throw new RuntimeException("You cannot follow yourself");
        }

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Following user not found"));

        boolean alreadyFollowing = followRepository.existsByFollowerAndFollowing(user, following);
        if (alreadyFollowing) {
            throw new RuntimeException("You are already following this user");
        }

        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowing(following);
        
        follow = followRepository.save(follow);
    }
    
    @Override
    public void unfollowUser(Long followingId, String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional

        User following = userRepository.findById(followingId)
        .orElseThrow(() -> new RuntimeException("Following user not found"));

        Follow follow = followRepository.findByFollowerAndFollowing(user, following)
                .orElseThrow(() -> new RuntimeException("Follow relationship not found"));

        followRepository.delete(follow);
    }
    
    @Override
    public List<User> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowing(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

}
