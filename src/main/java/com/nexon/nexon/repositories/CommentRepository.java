package com.nexon.nexon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Comment;
import com.nexon.nexon.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}