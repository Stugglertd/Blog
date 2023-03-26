package com.Blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.Exception.ResourceNotFound;
import com.Blog.Repo.UserRepo;
import com.Blog.entities.User;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  User user=null;
		try {
			user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFound("User Not Found with email id : "+username));
		} catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return user;
	}

}
