package com.aisha.blogapp.payloads;

import com.aisha.blogapp.entities.Category;
import com.aisha.blogapp.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    private String title;
    private String content;
    private String imageName;
    private Date addedate;
    private CategoryDto categoryDto;
    private UserDto userDto;
}
