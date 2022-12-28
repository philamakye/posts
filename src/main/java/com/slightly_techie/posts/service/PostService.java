package com.slightly_techie.posts.service;

import com.slightly_techie.posts.entity.Post;
import com.slightly_techie.posts.error.NonNullableFieldException;
import com.slightly_techie.posts.error.PostNotFoundException;
import com.slightly_techie.posts.model.PostModel;

import java.util.List;

public interface PostService {
    Post createPost(PostModel postModel) throws NonNullableFieldException;

    List<Post> fetchAllPosts();

    Post fetchPostById(Long id) throws PostNotFoundException;

    Post updatePost(PostModel postModel, Long id) throws PostNotFoundException;

    String deletePost(Long id) throws PostNotFoundException;
}
