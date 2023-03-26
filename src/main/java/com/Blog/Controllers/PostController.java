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

import com.Blog.Dtos.PostDto;
import com.Blog.Dtos.PostResponse;
import com.Blog.Service.PostService;

@RestController
@RequestMapping("post")
public class PostController {
  @Autowired	
  private PostService postService;
  
  @PostMapping	
  public ResponseEntity<PostDto> createPost(
	@RequestBody PostDto postDto,@RequestParam String userId,@RequestParam String categoryId)throws Exception{
    PostDto savedPostDto = postService.createPost(postDto, userId, categoryId);	  
    return new ResponseEntity<PostDto>(savedPostDto,HttpStatus.OK);
  }
  
  @PutMapping
  public ResponseEntity<PostDto> updatePost(
		  @RequestBody PostDto postDto) throws Exception{
	PostDto updatedPostDto = postService.updatePost(postDto);  
	return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
  }
  
  @DeleteMapping
  public ResponseEntity<String> deletePost(
		  @RequestParam String postId)throws Exception{
	postService.deletePost(postId);
	String msg = "Post deleted with id : " + postId;
	return new ResponseEntity<String>(msg,HttpStatus.OK);
  }
  
  @GetMapping("postId/{postId}")
  public ResponseEntity<PostDto> getPostByPostId(
		  @PathVariable String postId)throws Exception{
	PostDto postDto = postService.getPostById(postId);
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
  }
  
  @GetMapping("categoryId/{categoryId}")
  public ResponseEntity<List<PostDto>> getPostsByCategory(
		  @PathVariable String categoryId)throws Exception{
	List<PostDto> postDto = postService.getPostsByCategory(categoryId);
	return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
  }
  
  @GetMapping("userId/{userId}")
  public ResponseEntity<List<PostDto>> getPostsByUser(
		 @PathVariable String userId)throws Exception{
	List<PostDto> postDto = postService.getPostsByUser(userId);
	return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);  
  }
  
  @GetMapping("search")
  public ResponseEntity<List<PostDto>> searchByPost(
		  @RequestParam String keyword){
	List<PostDto> postDtos = postService.searchPosts(keyword);
	return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity<PostResponse> getAllPosts(
		  @RequestParam(defaultValue = "0") int pageNumber,
		  @RequestParam(defaultValue = "2") int pageSize,
		  @RequestParam(defaultValue = "asc") String sortDir){
    PostResponse postResponse = postService.getAllPosts(pageNumber,pageSize,sortDir);
    return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
  }
}
