package com.Blog.Dtos;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PostDto {
  private String postId; 
  private String content;
  private LocalDate dateCreated;
}
