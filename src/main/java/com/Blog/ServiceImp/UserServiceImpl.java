package com.Blog.ServiceImp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Dtos.UserDto;
import com.Blog.Exception.ResourceNotFound;
import com.Blog.Repo.UserRepo;
import com.Blog.Service.UserService;
import com.Blog.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
	  User user = this.modelMapper.map(userDto,User.class);
	  String idString = UUID.randomUUID().toString();
	  user.setId(idString);
	  User savedUser = userRepo.save(user);
	  return this.modelMapper.map(savedUser,UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, String about) throws Exception{
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with id: "+userId));
	  user.setAbout(about);
	  User updatedUser = userRepo.save(user);
	  return modelMapper.map(updatedUser,UserDto.class);
	}

	@Override
	public void deleteUser(String userId) throws Exception{
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with id: "+userId));	
	  userRepo.delete(user);
	}

	@Override
	public List<UserDto> getAllUser() {
	 List<User> user = userRepo.findAll();
	 return user.stream().map(u -> modelMapper.map(u,UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(String userId)throws Exception {
	  User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User Not Found with id: "+userId));
	  return modelMapper.map(user,UserDto.class);
	}

}
