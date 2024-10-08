package com.example.project01.Services;

import com.example.project01.Entities.Post;
import com.example.project01.Entities.User;
import com.example.project01.Repositories.PostRepository;
import com.example.project01.Requests.PostCreateRequest;
import com.example.project01.Requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()){
            return postRepository.findByUserId(userId);
        } else{
            return postRepository.findAll();
        }
    }
    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getId());
        if (user == null){
            return null;
        } else {
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setUser(user);
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setText(newPostRequest.getText());
            return postRepository.save(toSave);
        }
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setTitle(updatePost.getTitle());
            toUpdate.setText(updatePost.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        } else{
            return null;
        }
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
