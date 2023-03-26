package com.Blog.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {
  List<PostDto> postDtos;
  int currPage;
  int pageSize;
  int totalPages;
}
