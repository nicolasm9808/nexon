package com.nexon.nexon.controllers;

import com.nexon.nexon.service.LikeService;
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
}
