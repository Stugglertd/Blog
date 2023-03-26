package com.Blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Dtos.CategoryDto;
import com.Blog.Service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;
  
  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
	CategoryDto saveCategoryDto = categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(saveCategoryDto,HttpStatus.CREATED);
  }
  
  @PutMapping
  public ResponseEntity<CategoryDto> updateCategory(
		  @RequestParam String title,
		  @RequestParam String categoryId)throws Exception{
	CategoryDto saveCategoryDto = categoryService.updateCategory(title,categoryId);  
    return new ResponseEntity<CategoryDto>(saveCategoryDto,HttpStatus.OK);
  }
  
  @DeleteMapping
  public ResponseEntity<String> deleteCategory(
		  @RequestParam String categoryId)throws Exception{
	categoryService.deleteCategory(categoryId);
	String msg = "Category deleted with id : "+categoryId;
	return new ResponseEntity<String>(msg,HttpStatus.OK);
  }
  
  @GetMapping("{categoryId}")
  public ResponseEntity<CategoryDto> getCategoryById(
		  @PathVariable String categoryId)throws Exception{
	CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
	return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategory(){
	List<CategoryDto> categories = categoryService.getAllCategories();
	return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
  }
}
