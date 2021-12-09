package com.simple.blog.service;

import com.simple.blog.exception.NotAuthorizedException;
import com.simple.blog.model.Post;
import com.simple.blog.model.User;
import com.simple.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Transactional
  public void deleteById(Long postId, User user) {
    Post post = postRepository.findById(postId).orElseThrow();
    if(!post.getAuthor().isSameUser(user)) {
      throw new NotAuthorizedException();
    }
    postRepository.deleteById(postId);
  }

  @Transactional
  public void update(Long postId, Post newPost, User user) {
    Post oldPost = postRepository.findById(postId).orElseThrow();
    if(!oldPost.getAuthor().isSameUser(user)) {
      throw new NotAuthorizedException();
    }
    oldPost.setTitle(newPost.getTitle());
    oldPost.setContents(newPost.getContents());
  }

  public Page<Post> getPostPage(Integer pageId, User author, int windowSize) {
    pageId = pageId == null ? 0 : pageId;
    PageRequest pageRequest = PageRequest.of(pageId, windowSize, Sort.by(Sort.Order.desc("createDate")));
    return postRepository.findByAuthor(author, pageRequest);
  }

  public Post getPost(Long postId) {
    return postRepository.findById(postId).orElseThrow();
  }

  public void enroll(Post post) {
    postRepository.save(post);
  }
}
