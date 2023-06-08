package com.aisha.blogapp.services.impl;

import com.aisha.blogapp.entities.Category;
import com.aisha.blogapp.exceptions.ResourceNotFoundException;
import com.aisha.blogapp.payloads.CategoryDto;
import com.aisha.blogapp.repos.CategoryRepo;
import com.aisha.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.categoryDtoToCategory(categoryDto);
        Category savedCategory=this.categoryRepo.save(category);
        return this.categoryToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=this.categoryRepo.save(category);
        return this.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        this.categoryRepo.delete(category);
    }
    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        return this.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map(category -> this.categoryToCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        Category category=this.modelMapper.map(categoryDto,Category.class);
        return category;
    }

    public CategoryDto categoryToCategoryDto(Category category){
        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
