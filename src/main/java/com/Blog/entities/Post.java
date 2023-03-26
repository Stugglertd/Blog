package com.Blog.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Post{
  @Id
  private String Id; 
  private String content;
  private LocalDate dateCreated;
  
//  @OneToMany(mappedBy = "post")
//  private Set<Comment> comments = new HashSet<>();
  
  @ManyToOne
  private Category category;
  
  @ManyToOne
  private User user;
}
