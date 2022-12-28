package com.slightly_techie.posts.controller;

import com.slightly_techie.posts.entity.Post;
import com.slightly_techie.posts.error.NonNullableFieldException;
import com.slightly_techie.posts.error.PostNotFoundException;
import com.slightly_techie.posts.model.PostModel;
import com.slightly_techie.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("/posts")
    public Post createPost(@RequestBody PostModel postModel) throws NonNullableFieldException {
        return postService.createPost(postModel);
    }

    @GetMapping("/posts")
    public List<Post> fetchAllPosts(){
        return postService.fetchAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post fetchPostById(@PathVariable("id") Long id) throws PostNotFoundException {
        return postService.fetchPostById(id);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody PostModel postModel, @PathVariable("id") Long id) throws PostNotFoundException{
        return postService.updatePost(postModel,id);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) throws PostNotFoundException{
        return postService.deletePost(id);
    }
}
