package com.Blog.Service;

import java.util.List;

import com.Blog.Dtos.CategoryDto;

public interface CategoryService {
	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	 //update
	 CategoryDto updateCategory(String categoryTitle,String categoryId)throws Exception;	
	 //delete
	 void deleteCategory(String categoryId)throws Exception;
	 //get
	 CategoryDto getCategoryById(String categoryId)throws Exception;	
	 //get all
	 List<CategoryDto> getAllCategories();
}
