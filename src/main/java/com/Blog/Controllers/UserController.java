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

import com.Blog.Dtos.UserDto;
import com.Blog.Service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
  @Autowired	
  private UserService userService;	
  
  @PostMapping 	
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
	UserDto savedUserDto = userService.createUser(userDto);
	return new ResponseEntity<UserDto>(savedUserDto,HttpStatus.CREATED);
  }
  
  @PutMapping
  public ResponseEntity<UserDto> updateUser(
		  @RequestParam String userId,
		  @RequestParam String about)throws Exception{
	UserDto updatedUserDto = userService.updateUser(userId, about);
	return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
  }
  
  @DeleteMapping
  public ResponseEntity<String> deleteUser(@RequestParam String userId)throws Exception{
	userService.deleteUser(userId);
	String message = "User deleted successfully with id : "+userId;
	return new ResponseEntity<String>(message,HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUser(){
	List<UserDto> userDtos = userService.getAllUser();
	return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
  }
  
  @GetMapping("{userId}")
  public ResponseEntity<UserDto> getUser(@PathVariable String userId) throws Exception{
	UserDto userDto = userService.getUserById(userId);
	return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
  }
}
