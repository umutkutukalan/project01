package com.example.project01.Repositories;

import com.example.project01.Entities.Comment;
import com.example.project01.Entities.Post;
import com.example.project01.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserIdAndPostId(Optional<User> userId, Optional<Post> postId);

    List<Comment> findByUserId(Optional<User> userId);

    List<Comment> findByPostId(Optional<Post> postId);
}
