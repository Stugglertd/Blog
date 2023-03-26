package com.Blog.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.entities.Comment;
import com.Blog.entities.Post;
import com.Blog.entities.User;

public interface CommentRepo extends JpaRepository<Comment,String>{
  List<Comment> findByPost(Post post);
  List<Comment> findByUser(User user);
}
