package com.Blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table
@Data
public class Category{
  @Id	
  private String id;
  private String title;
  
  @OneToMany(mappedBy = "category")
  private List<Post> posts = new ArrayList<>();
}
