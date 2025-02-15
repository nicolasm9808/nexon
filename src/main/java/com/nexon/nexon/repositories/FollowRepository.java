package com.nexon.nexon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexon.nexon.entities.Follow;
import com.nexon.nexon.entities.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    boolean existsByFollowerAndFollowing(User follower, User following);
    Optional <Follow> findByFollowerAndFollowing(User follower, User following);
    List<Follow> findByFollowingId(Long userId);
    List<Follow> findByFollowerId(Long userId);
    @Query("SELECT f.following.id FROM Follow f WHERE f.follower = :follower") 
    List<Long> findFollowingIdsByFollower(@Param("follower") User follower);

}
