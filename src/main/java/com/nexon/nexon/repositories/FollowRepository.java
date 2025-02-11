package com.nexon.nexon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Follow;
import com.nexon.nexon.entities.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
}
