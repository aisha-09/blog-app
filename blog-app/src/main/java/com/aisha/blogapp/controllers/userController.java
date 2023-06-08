package com.aisha.blogapp.controllers;

import com.aisha.blogapp.payloads.ApiResponse;
import com.aisha.blogapp.payloads.UserDto;
import com.aisha.blogapp.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class userController {
    // here we will create different apis to get, update, create and delete user
    @Autowired
    private UserService userService;


    //POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUser= this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE- delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return  new ResponseEntity(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
    }

    //GET- get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());

    }

    //GET- get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
