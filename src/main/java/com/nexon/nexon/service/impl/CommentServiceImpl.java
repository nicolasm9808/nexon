package com.nexon.nexon.service.impl;

import com.nexon.nexon.entities.Comment;
import com.nexon.nexon.entities.NotificationType;
import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.CommentRepository;
import com.nexon.nexon.repositories.PostRepository;
import com.nexon.nexon.service.CommentService;
import com.nexon.nexon.service.NotificationService;
import com.nexon.nexon.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserService userService, NotificationService notificationService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public Comment addComment(Long postId, String text, String username) {
        // Find the user based on the username
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();  // Extract the user from the Optional
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(text);
        comment.setCreatedAt(new Date());

        // Extract mentions from comment text
        List<String> mentions = extractMentions(text);
        comment.setMentions(mentions);

        // Save comment
        comment = commentRepository.save(comment);

        // Update total comments in the post
        post.setTotalComments(post.getTotalComments() + 1);
        postRepository.save(post);

        // Notify post owner
        if (!post.getUser().equals(user)) {
            notificationService.createNotification(
                    post.getUser(), user, NotificationType.COMMENT,
                    user.getUsername() + " commented on your post", post
            );
        }

        // Notify mentioned users
        for (String mentionedUsername : mentions) {
            Optional<User> mentionedUserOptional = userService.getUserByUsername(mentionedUsername);
            mentionedUserOptional.ifPresent(mentionedUser -> {
                if (!mentionedUser.equals(user)){
                    notificationService.createNotification(
                            mentionedUser, user, NotificationType.MENTION,
                            user.getUsername() + " mentioned you in a comment", post
                    );
                }
            });
        }

        return comment;
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Post post = comment.getPost();
        
        // Only allow deletion if the comment was created by the authenticated user or is the post owner
        if (!comment.getUser().getUsername().equals(username) && !post.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own comments");
        }

        // Update total comments in the post
        post.setTotalComments(post.getTotalComments() - 1);
        postRepository.save(post);

        commentRepository.delete(comment);
    }

    private List<String> extractMentions(String text) {
        List<String> mentions = new ArrayList<>();
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            mentions.add(matcher.group(1));
        }
        return mentions;
    }
}
