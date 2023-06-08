package com.aisha.blogapp.repos;

import com.aisha.blogapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
