package com.Blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,String>{

}
