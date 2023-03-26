package com.Blog.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Comment{
  @Id	
  private String id;
  private String comment;
  
  @ManyToOne
  private User user;
  
  @ManyToOne
  private Post post;
}
