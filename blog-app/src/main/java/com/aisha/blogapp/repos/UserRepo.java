package com.aisha.blogapp.repos;

import com.aisha.blogapp.entities.Category;
import com.aisha.blogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aisha.blogapp.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {


}
