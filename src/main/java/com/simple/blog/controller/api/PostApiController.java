package com.simple.blog.controller.api;

import com.simple.blog.model.Post;
import com.simple.blog.model.User;
import com.simple.blog.repository.PostRepository;
import com.simple.blog.security.PrincipalDetail;
import com.simple.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostApiController {

  @Autowired
  private PostService postService;

  @DeleteMapping("/post/{postId}")
  public ResponseEntity<String> deletePost(@AuthenticationPrincipal PrincipalDetail principalDetail,
                                           @PathVariable("postId") Long postId) {
    User user = principalDetail.getUser();
    postService.deleteById(postId, user);
    return new ResponseEntity<>("Delete completed", HttpStatus.OK);
  }

  @PutMapping("/post/{postId}")
  public ResponseEntity<String> updatePost(@AuthenticationPrincipal PrincipalDetail principalDetail,
                                           @PathVariable("postId") Long postId, Post newPost) {
    User user = principalDetail.getUser();
    postService.update(postId, newPost, user);
    return new ResponseEntity<>("Update completed", HttpStatus.OK);
  }
}
