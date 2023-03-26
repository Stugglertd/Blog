package com.Blog.Service;

import java.util.List;

import com.Blog.Dtos.PostDto;
import com.Blog.Dtos.PostResponse;


public interface PostService {
	  //create	
	  PostDto createPost(PostDto postDto,String userId,String categoryId)throws Exception;
	 //update
	  PostDto updatePost(PostDto postDto)throws Exception;
	 //delete
	  void deletePost(String postId)throws Exception;
	 //get all posts
	 // PostResponse getAllPost(int pageNumber,int pageSize, String sortBy,String sortDir);
	 //get single post
	  PostDto getPostById(String postId)throws Exception;
	  PostResponse getAllPosts(int currPage,int pageSize,String sortDir);
	 //get post by category
	  List<PostDto> getPostsByCategory(String categoryId)throws Exception;
	 //get post by user
	  List<PostDto> getPostsByUser(String userId)throws Exception;
	 //search posts 
	  List<PostDto> searchPosts(String keyword);
}
