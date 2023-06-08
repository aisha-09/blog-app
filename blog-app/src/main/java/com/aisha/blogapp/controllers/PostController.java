package com.aisha.blogapp.controllers;

import com.aisha.blogapp.entities.Post;
import com.aisha.blogapp.payloads.ApiResponse;
import com.aisha.blogapp.payloads.PostDto;
import com.aisha.blogapp.payloads.UserDto;
import com.aisha.blogapp.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){
        PostDto createdPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(
            @PathVariable Integer userId
            ){
        List<PostDto> posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDto> posts=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value="pageNumber",defaultValue = "1",required = false) Integer pageNumber,
                                                     @RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize){

        List<PostDto> posts=this.postService.getAll(pageNumber,pageSize);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    //get post by id
    @GetMapping("/post/{postId}")
    public ResponseEntity getPostById(@PathVariable Integer postId){
        PostDto postDto1=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted", true);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost=this.postService.updatePost(postDto , postId);
        return  new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);

    }

}
