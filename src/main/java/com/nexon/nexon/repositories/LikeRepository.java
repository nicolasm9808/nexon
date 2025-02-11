package com.nexon.nexon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Like;
import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    long countByPost(Post post);
}
