package com.Blog.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;
  private String name;
}
