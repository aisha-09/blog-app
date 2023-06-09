package com.aisha.blogapp.repos;

import com.aisha.blogapp.entities.Category;
import com.aisha.blogapp.entities.Post;
import com.aisha.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
