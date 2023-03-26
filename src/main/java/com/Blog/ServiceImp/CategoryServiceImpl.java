package com.Blog.ServiceImp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Dtos.CategoryDto;
import com.Blog.Exception.ResourceNotFound;
import com.Blog.Repo.CategoryRepo;
import com.Blog.Service.CategoryService;
import com.Blog.entities.Category;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	  Category category = new Category();
	  category.setTitle(categoryDto.getTitle());
	  
	  String categoryIdString = UUID.randomUUID().toString();
	  category.setId(categoryIdString);
	  
	  Category savedCategory = categoryRepo.save(category);
	  return modelMapper.map(savedCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(String categoryTitle,String categoryId) throws Exception {
	  Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category Not Found with id : "+categoryId));
	  category.setTitle(categoryTitle);
	  categoryRepo.save(category);
	  return modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public void deleteCategory(String categoryId) throws Exception{
	  Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category Not Found with id : "+categoryId));	
	  categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(String categoryId) throws Exception{
	  Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category Not Found with id : "+categoryId));	
	  return modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
	  List<Category> categories = categoryRepo.findAll();
	  return categories.stream().map(cat->modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	}

}
