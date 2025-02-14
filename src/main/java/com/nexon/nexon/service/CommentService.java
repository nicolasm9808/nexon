package com.nexon.nexon.service;

import com.nexon.nexon.entities.Comment;

import java.util.List;

// This file should be renamed to CommentService.java
public interface CommentService {
    Comment addComment(Long postId, String text, String username);
    List<Comment> getCommentsByPost(Long postId);
    void deleteComment(Long commentId, String username);
}
