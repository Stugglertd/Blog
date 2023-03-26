package com.Blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Dtos.CommentDto;
import com.Blog.Service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
  @Autowired	
  private CommentService commentService;	
  @PostMapping	
  public ResponseEntity<CommentDto> createComment(
		  @RequestBody CommentDto commentDto,
		  @RequestParam String userId,@RequestParam String postId) throws Exception{
	CommentDto savedCommentDto = commentService.createComment(commentDto, postId, userId);
	return new ResponseEntity<CommentDto>(savedCommentDto,HttpStatus.CREATED);
  }
  
  @PutMapping
  public ResponseEntity<CommentDto> updateComment(@RequestParam String commentId,@RequestParam String commentString) throws Exception{
	CommentDto updatedCommentDto = commentService.updateComment(commentId, commentString);
	return new ResponseEntity<CommentDto>(updatedCommentDto,HttpStatus.OK);
  }
  
  @DeleteMapping
  public ResponseEntity<String> deleteComment(@RequestParam String commentId) throws Exception{
	 commentService.deleteComment(commentId);
	 String msg = "comment deleted with id : "+commentId;
	 return new ResponseEntity<String>(msg,HttpStatus.OK);
  }
  
  @GetMapping("/postId")
  public ResponseEntity<List<CommentDto>> getByPostId(@RequestParam String postId) throws Exception{
	List<CommentDto> commentDtos = commentService.getCommentByPostId(postId);
	return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);
  }
  
  @GetMapping("/userId")
  public ResponseEntity<List<CommentDto>> getByUserId(@RequestParam String userId) throws Exception{
	List<CommentDto> commentDtos = commentService.getCommentByUserId(userId);
	return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);
  }
}
