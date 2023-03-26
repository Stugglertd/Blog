package com.Blog.Service;

import java.util.List;

import com.Blog.Dtos.CommentDto;

public interface CommentService {
  CommentDto createComment(CommentDto commentDto,String postId,String userId)throws Exception;
  void deleteComment(String commentId)throws Exception;
  CommentDto updateComment(String commentId,String comment)throws Exception;
  List<CommentDto> getCommentByPostId(String postId)throws Exception;
  List<CommentDto> getCommentByUserId(String userId)throws Exception;
}
