package com.nexon.nexon.repositories;

import com.nexon.nexon.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedAtAsc(); // Orden cronológico ascendente
    List<Post> findAllByOrderByCreatedAtDesc(); // Orden cronológico descendente

    @Query("SELECT p FROM Post p ORDER BY (p.totalLikes + p.totalComments) DESC") // Orden por relevancia
    List<Post> findPostsOrderedByRelevance();
    List<Post> findByUserIdInOrderByCreatedAtDesc(List<Long> UserIds);
}
