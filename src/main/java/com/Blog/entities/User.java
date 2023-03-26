package com.Blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class User implements UserDetails{
  @Id	
  private String id;
  private String name;
  private String email;
  private String password;
  private String about;

  @OneToMany(mappedBy = "user")
  List<Post> posts = new ArrayList<>();
  
  @OneToMany(mappedBy = "user")
  List<Comment> comments = new ArrayList<>();
  
  @ManyToMany
  private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	  List<SimpleGrantedAuthority> authorities = roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	  return authorities;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
