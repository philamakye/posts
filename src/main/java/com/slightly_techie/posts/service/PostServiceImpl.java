package com.slightly_techie.posts.service;

import com.slightly_techie.posts.entity.Post;
import com.slightly_techie.posts.error.NonNullableFieldException;
import com.slightly_techie.posts.error.PostNotFoundException;
import com.slightly_techie.posts.model.PostModel;
import com.slightly_techie.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(PostModel postModel) throws NonNullableFieldException {
        Post post = new Post();
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        if(Objects.nonNull(postModel.getTitle()) && Objects.nonNull(postModel.getContent())){
            if(p.matcher(postModel.getTitle()).find() && p.matcher(postModel.getContent()).find()){
                post.setTitle(postModel.getTitle());
                post.setContent(postModel.getContent());
            }else{
                throw new NonNullableFieldException("Request body contains an empty or null non-nullable field");
            }
            if(Objects.nonNull(postModel.getAuthor()) && p.matcher(postModel.getAuthor()).find()){
                post.setAuthor(postModel.getAuthor());
            }else {
                post.setAuthor("Anonymous");
            }
           return postRepository.save(post);
        }else{
            throw new NonNullableFieldException("Request body contains an empty or null non-nullable field");
        }
    }

    @Override
    public List<Post> fetchAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post fetchPostById(Long id) throws PostNotFoundException {
        if(postRepository.findById(id).isEmpty())
            throw new PostNotFoundException("Id does not correspond to an existing post");
        return postRepository.findById(id).get();
    }

    @Override
    public Post updatePost(PostModel postModel, Long id) throws PostNotFoundException {
        Post post = fetchPostById(id);
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        if(Objects.nonNull(postModel.getTitle()) && p.matcher(postModel.getTitle()).find()){
            post.setTitle(postModel.getTitle());
        }
        if(Objects.nonNull(postModel.getAuthor()) && p.matcher(postModel.getAuthor()).find()){
            post.setAuthor(postModel.getAuthor());
        }
        if(Objects.nonNull(postModel.getContent()) && p.matcher(postModel.getContent()).find()){
            post.setContent(postModel.getContent());
        }
        return postRepository.save(post);

    }

    @Override
    public String deletePost(Long id) throws PostNotFoundException {
        if(postRepository.findById(id).isEmpty())
            return "No post with specified Id present";
        Post post = fetchPostById(id);
        postRepository.deleteById(id);
        return "Post titled "+post.getTitle()+" by "+post.getAuthor()+" deleted";
    }


}
