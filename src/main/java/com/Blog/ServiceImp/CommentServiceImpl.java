package com.Blog.ServiceImp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.TransactionScoped;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Dtos.CommentDto;
import com.Blog.Exception.ResourceNotFound;
import com.Blog.Repo.CommentRepo;
import com.Blog.Repo.PostRepo;
import com.Blog.Repo.UserRepo;
import com.Blog.Service.CommentService;
import com.Blog.entities.Comment;
import com.Blog.entities.Post;
import com.Blog.entities.User;

@Service
@TransactionScoped
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto,String postId,String userId) throws Exception {
	  Comment comment = modelMapper.map(commentDto,Comment.class);
	  
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with user id : "+userId));
	  Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post Not Found wiht post id : "+postId)); 
	  
	  String idString = UUID.randomUUID().toString();
	  
	  comment.setId(idString);
	  comment.setUser(user);
	  comment.setPost(post);
	  
	  Comment savedComment = commentRepo.save(comment);
	  
	  return modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(String commentId) throws Exception {
	  Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFound("Comment Not Found with id : "+commentId));	
	  commentRepo.delete(comment);	
	}

	@Override
	public CommentDto updateComment(String commentId, String commentStr) throws Exception {
	  Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFound("Comment Not Found with id : "+commentId));		
	  comment.setComment(commentStr);
	  
	  Comment savedComment = commentRepo.save(comment);
	  
	  return modelMapper.map(savedComment,CommentDto.class);
	}
	
	@Override
	public List<CommentDto> getCommentByPostId(String postId) throws Exception {
	  Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post Not Found wiht post id : "+postId)); 
	  List<Comment> comments = commentRepo.findByPost(post);
	  
	  List<CommentDto> commentDtos = comments.stream().map(c->modelMapper.map(c,CommentDto.class)).collect(Collectors.toList());
	  
	  return commentDtos;
	}

	@Override
	public List<CommentDto> getCommentByUserId(String userId) throws Exception {
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with user id : "+userId));
	  List<Comment> comments = commentRepo.findByUser(user);
	  
	  List<CommentDto> commentDtos = comments.stream().map(c->modelMapper.map(c,CommentDto.class)).collect(Collectors.toList());
	  
	  return commentDtos;
	}

}
