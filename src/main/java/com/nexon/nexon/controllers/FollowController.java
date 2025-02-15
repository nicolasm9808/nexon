package com.nexon.nexon.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nexon.nexon.entities.User;
import com.nexon.nexon.service.FollowService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable Long followingId, Authentication authentication) {
        String username = authentication.getName(); // Get authenticated user
        followService.followUser(followingId, username);
        return ResponseEntity.ok("You are now following this user");
    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followingId, Authentication authentication) {
        String username = authentication.getName(); // Get authenticated user
        followService.unfollowUser(followingId, username);
        return ResponseEntity.ok("You have unfollowed this user");
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }
}
