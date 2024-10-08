package com.example.project01.Services;

import com.example.project01.Entities.Comment;
import com.example.project01.Entities.Post;
import com.example.project01.Entities.User;
import com.example.project01.Repositories.CommentRepository;
import com.example.project01.Requests.CommentCreateRequest;
import com.example.project01.Requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository,
                          UserService userService,
                          PostService postService
                          ){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public List<Comment> getAllCommentsWithParam(Optional<User> userId, Optional<Post> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()){
            return commentRepository.findByUserId(userId);
        } else if(postId.isPresent()){
            return commentRepository.findByPostId(postId);
        } else{
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneCommentById(CommentCreateRequest newCommentRequest) {
        User user = userService.getOneUserById(newCommentRequest.getUserId());
        Post post = postService.getOnePostById(newCommentRequest.getPostId());
        if (user != null && post != null){
            Comment commentSave = new Comment();
            commentSave.setId(newCommentRequest.getId());
            commentSave.setUser(user);
            commentSave.setPost(post);
            commentSave.setText(newCommentRequest.getText());
            return commentRepository.save(commentSave);
        }else{
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentUpdate = comment.get();
            commentUpdate.setText(updateComment.getText());
            return commentRepository.save(commentUpdate);
        } else {
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
