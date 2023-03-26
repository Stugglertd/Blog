package com.Blog.ServiceImp;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Blog.Dtos.PostDto;
import com.Blog.Dtos.PostResponse;
import com.Blog.Exception.ResourceNotFound;
import com.Blog.Repo.CategoryRepo;
import com.Blog.Repo.PostRepo;
import com.Blog.Repo.UserRepo;
import com.Blog.Service.PostService;
import com.Blog.entities.Category;
import com.Blog.entities.Post;
import com.Blog.entities.User;

@Service
@Transactional
public class PostServiceImpl implements PostService{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, String userId, String categoryId) throws Exception {
	  Post post = new Post();
	  String postId = UUID.randomUUID().toString();
	  post.setId(postId);
	  
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with user id : "+userId));
	  Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category Not Found with category id : "+categoryId));
	  LocalDate date = LocalDate.now();
	  
	  post.setUser(user);
	  post.setCategory(category);
	  post.setContent(postDto.getContent());
	  post.setDateCreated(date);//current date
	  
	  
	  Post savedPost = postRepo.save(post);
	  
	  return modelMapper.map(savedPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto)throws Exception {
	  Post post = postRepo.findById(postDto.getPostId()).orElseThrow(()->new ResourceNotFound("Post not found with id : "+postDto.getPostId()));
	  post.setContent(postDto.getContent());
	  
	  postRepo.save(post);
	  
	  return modelMapper.map(post,PostDto.class);
	}

	@Override
	public void deletePost(String postId)throws Exception {
	  Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post not found with id : "+postId));
	  postRepo.delete(post);	
	}

	@Override
	public PostDto getPostById(String postId)throws Exception {
	  Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post not found with id : "+postId));
	  return modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(String categoryId) throws Exception {
	  Category category 
	   = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category Not Found with id : "+categoryId));	
	  List<Post> posts = postRepo.findByCategory(category); 
	  
	  List<PostDto> postDtos 
	   = posts.stream().map(p->modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
	  
	  return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(String userId)throws Exception{
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with user id : "+userId));	
	  List<Post> posts = postRepo.findByUser(user); 
		  
	  List<PostDto> postDtos 
		   = posts.stream().map(p->modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		  
	  return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	  List<Post> posts = postRepo.findByContentContaining(keyword);	
	  
	  List<PostDto> postDtos 
	   = posts.stream().map(p->modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
	
	  return postDtos;
	}

	@Override
	public PostResponse getAllPosts(int currPage,int pageSize,String sortDir) {
	  Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by("content").ascending():Sort.by("content").descending();
	  
	  Pageable pageable = PageRequest.of(currPage,pageSize,sort);	
	  Page<Post> pagePost = postRepo.findAll(pageable);
	  
	  List<Post> posts = pagePost.getContent();
	  
	  List<PostDto> postDtos = posts.stream().map(p->modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
	  
	  PostResponse postResponse = new PostResponse();
	  postResponse.setCurrPage(currPage);
	  postResponse.setPageSize(pageSize);
	  postResponse.setPostDtos(postDtos);
	  postResponse.setTotalPages(pagePost.getTotalPages());
	  
	  return postResponse;
	}

}
