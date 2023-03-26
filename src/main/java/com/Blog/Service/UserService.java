package com.Blog.Service;

import java.util.List;

import com.Blog.Dtos.UserDto;

public interface UserService {
  UserDto createUser(UserDto userDto);
  UserDto updateUser(String userId,String about)throws Exception;
  void deleteUser(String userId)throws Exception;
  List<UserDto> getAllUser();
  UserDto getUserById(String userId)throws Exception;
}
