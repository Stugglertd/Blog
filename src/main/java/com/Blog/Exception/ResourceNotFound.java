package com.Blog.Exception;

public class ResourceNotFound extends Exception{
  private static final long serialVersionUID = 1L;
  
  public ResourceNotFound(String msg){
	super(msg); 
  }
}
