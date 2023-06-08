package com.aisha.blogapp.services.impl;

import com.aisha.blogapp.entities.User;
import com.aisha.blogapp.exceptions.ResourceNotFoundException;
import com.aisha.blogapp.payloads.UserDto;
import com.aisha.blogapp.services.UserService;
import com.aisha.blogapp.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setAbout(userDto.getAbout());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updateUser=this.userRepo.save(user);
        UserDto userDto1=userToDto(updateUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        UserDto userDto=userToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto)
    {
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        User user= this.modelMapper.map(userDto, User.class);
        return user;
    }
    public UserDto userToDto(User user)
    {
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;

    }
}
