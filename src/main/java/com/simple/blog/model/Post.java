package com.simple.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(optional = false)
  private User author;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String contents;

  @CreationTimestamp
  private Timestamp createDate;

  public String beautifyDate() {
    return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createDate);
  }
}
