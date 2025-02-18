package com.nexon.nexon.service;

import java.util.List;

public interface LikeService {
    boolean toggleLike(Long postId, String username);
    List<String> getUsersWhoLikedPost(Long postId);
}
