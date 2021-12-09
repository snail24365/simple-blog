package com.simple.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static com.simple.blog.model.RoleType.*;

@Entity
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true,length = 50)
  @Length(max = 100)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  @Enumerated(EnumType.STRING)
  private RoleType role = USER;

  public boolean isNicknameInvalid() {
    final int minSize = 2;
    return nickname != null && nickname.length() < minSize;
  }

  public boolean isPasswordInvalid() {
    final int minSize = 8;
    return password != null && password.length() < minSize;
  }

  public boolean isUsernameInvalid() {
    final int minSize = 5;
    return username != null && username.length() < minSize;
  }

  public boolean isSameUser(User other) {
    return other.getId().equals(id);
  }
}
