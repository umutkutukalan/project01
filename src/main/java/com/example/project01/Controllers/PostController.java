package com.example.project01.Controllers;

import com.example.project01.Entities.Post;
import com.example.project01.Requests.PostCreateRequest;
import com.example.project01.Requests.PostUpdateRequest;
import com.example.project01.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId,
                              @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }

}
