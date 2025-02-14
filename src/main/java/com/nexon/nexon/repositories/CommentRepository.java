package com.nexon.nexon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}