package com.nexon.nexon.controllers;

import com.nexon.nexon.dto.CommentRequestDTO;
import com.nexon.nexon.entities.Comment;
import com.nexon.nexon.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, 
                                              @RequestBody CommentRequestDTO commentRequestDTO, 
                                              Authentication authentication) {
        String username = authentication.getName();
        Comment comment = commentService.addComment(postId, commentRequestDTO.getText(), username);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        commentService.deleteComment(commentId, username);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
