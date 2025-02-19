package com.nexon.nexon.entities;
import lombok.Getter;

@Getter
public class UserProfileResponse {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String bio;
    private String profilePicture;
    private int followersCount;
    private int followingCount;

    public UserProfileResponse(User user, int followersCount, int followingCount) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.profilePicture = user.getProfilePicture();
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }
}