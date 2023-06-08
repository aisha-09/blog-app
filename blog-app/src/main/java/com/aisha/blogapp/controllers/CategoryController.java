package com.aisha.blogapp.controllers;

import com.aisha.blogapp.payloads.ApiResponse;
import com.aisha.blogapp.payloads.CategoryDto;
import com.aisha.blogapp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Post
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }


    //Put- Update response
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{categoryId}")
    //Get- get resposne
    public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable Integer categoryId){
        CategoryDto getCategory=this.categoryService.getCategory(categoryId);
        return ResponseEntity.ok(getCategory);
    }

    @GetMapping("/")
    //Get- get all categories

    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    //Delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
    }
}
