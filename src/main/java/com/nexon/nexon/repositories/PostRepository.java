package com.nexon.nexon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
