package com.aisha.blogapp.services.impl;

import com.aisha.blogapp.entities.Category;
import com.aisha.blogapp.entities.Post;
import com.aisha.blogapp.entities.User;
import com.aisha.blogapp.exceptions.ResourceNotFoundException;
import com.aisha.blogapp.payloads.PostDto;
import com.aisha.blogapp.repos.CategoryRepo;
import com.aisha.blogapp.repos.PostRepo;
import com.aisha.blogapp.repos.UserRepo;
import com.aisha.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddeddate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);


    }

    @Override
    public List<PostDto> getAll(Integer pageNumber, Integer pageSize) {
        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost=this.postRepo.findAll(p);

        List<Post> posts= this.pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;


    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
