package com.nexon.nexon.service;

import java.util.List;

import com.nexon.nexon.entities.User;

public interface FollowService {
    void followUser(Long followingId, String username);
    void unfollowUser(Long followingId, String username);
    List<User> getFollowers(Long userId);
    List<User> getFollowing(Long userId);
}
