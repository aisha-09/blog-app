package com.aisha.blogapp.services;

import com.aisha.blogapp.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
