package com.aisha.blogapp.services;

import com.aisha.blogapp.entities.Post;
import com.aisha.blogapp.payloads.CategoryDto;
import com.aisha.blogapp.payloads.PostDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    List<PostDto> getAll(Integer pageNumber, Integer pageSize);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto> searchPost(String keyword);
}
