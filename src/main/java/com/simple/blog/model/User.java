package com.simple.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.simple.blog.model.RoleType.*;

@Entity
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true,length = 30)
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private RoleType role = USER;
}
