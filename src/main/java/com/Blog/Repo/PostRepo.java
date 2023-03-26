package com.Blog.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Blog.entities.Category;
import com.Blog.entities.Post;
import com.Blog.entities.User;

public interface PostRepo extends JpaRepository<Post,String>{
  List<Post> findByCategory(Category category);
  List<Post> findByUser(User user);
  
  //Like query
  List<Post> findByContentContaining(String key);
  
}
