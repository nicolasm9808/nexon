package com.nexon.nexon.controllers;

import com.nexon.nexon.entities.Post;
import com.nexon.nexon.service.PostService;
import com.nexon.nexon.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, Authentication authentication) {
        String username = authentication.getName(); // Get authenticated user
        return ResponseEntity.ok(postService.createPost(post, username));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @Valid @RequestBody Post post, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(postService.updatePost(postId, post, username));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        postService.deletePost(postId, username);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) String orderBy) {
        return ResponseEntity.ok(postService.getAllPosts(orderBy));
    }
}
