package com.nexon.nexon.controllers;

import com.nexon.nexon.service.LikeService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        boolean liked = likeService.toggleLike(postId, username);
        return ResponseEntity.ok(liked ? "Liked" : "Unliked");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<String>> getUsersWhoLikedPost(@PathVariable Long postId) {
        List<String> users = likeService.getUsersWhoLikedPost(postId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{postId}/liked")
    public ResponseEntity<Boolean> hasUserLikedPost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        boolean hasLiked = likeService.hasUserLikedPost(postId, username);
        return ResponseEntity.ok(hasLiked);
    }
    

}
