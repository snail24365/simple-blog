package com.simple.blog.controller;

import com.simple.blog.model.Post;
import com.simple.blog.repository.PostRepository;
import com.simple.blog.security.PrincipalDetail;
import com.simple.blog.util.SequenceIndexGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @GetMapping
  public String postsPage(@RequestParam @Nullable Integer pageId, Model model) {
    pageId = pageId == null ? 0 : pageId;
    final int maxNumPost = 10;
    final int windowSize = 10;
    Page<Post> postPage = postRepository.findAll(PageRequest.of(pageId, maxNumPost));

    List<Post> posts = postPage.getContent();

    int totalPage = postPage.getTotalPages();
    int currentPage = postPage.getNumber();
    int numPost = postPage.getNumberOfElements();

    List<Integer> pageIndexes = new SequenceIndexGenerator()
      .generateAdjacentRange(currentPage, totalPage, windowSize);

    model.addAttribute("posts", posts);
    model.addAttribute("pageSize", maxNumPost);
    model.addAttribute("pageIndexes", pageIndexes);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("numPost", numPost);

    return "posts";
  }

  @GetMapping("/{postId}")
  public String detailPostPage(@PathVariable("postId") Long postId, Model model) {
    Post post = postRepository.findById(postId).orElseThrow();
    model.addAttribute("post", post);
    return "post";
  }

  @GetMapping("/write")
  public String writePage() {
    return "write";
  }

  @PostMapping("/write")
  public String enrollPost(Post post, @AuthenticationPrincipal PrincipalDetail principalDetail) {
    post.setAuthor(principalDetail.getUser());
    postRepository.save(post);
    return String.format("redirect:/post/%d", post.getId());
  }
}
