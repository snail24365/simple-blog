package com.simple.blog.controller;

import com.simple.blog.model.Post;
import com.simple.blog.model.User;
import com.simple.blog.repository.PostRepository;
import com.simple.blog.security.PrincipalDetail;
import com.simple.blog.service.PostService;
import com.simple.blog.util.SequenceIndexGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping(value={"/", "post"})
  public String postsPage(@RequestParam @Nullable Integer pageId,
                          @AuthenticationPrincipal PrincipalDetail principalDetail,
                          Model model) {
    final int windowSize = 10;
    User author = principalDetail.getUser();
    Page<Post> postPage = postService.getPostPage(pageId, author, windowSize);
    int totalPage = postPage.getTotalPages();
    int currentPage = postPage.getNumber();
    int numPost = postPage.getNumberOfElements();

    List<Integer> pageIndexes = new SequenceIndexGenerator()
      .generateAdjacentRange(currentPage, totalPage, windowSize);

    model.addAttribute("posts", postPage.getContent());
    model.addAttribute("pageSize", windowSize);
    model.addAttribute("pageIndexes", pageIndexes);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("numPost", numPost);
    model.addAttribute("nickname", StringUtils.capitalize(author.getNickname()));
    model.addAttribute("hideListButton", true);

    return "posts";
  }

  @GetMapping("/post/{postId}")
  public String postViewPage(@PathVariable("postId") Long postId,
                             @AuthenticationPrincipal PrincipalDetail principalDetail,
                             Model model) {
    User author = principalDetail.getUser();
    Post post = postService.getPost(postId);
    model.addAttribute("nickname", StringUtils.capitalize(author.getNickname()));
    model.addAttribute("post", post);
    model.addAttribute("isPostViewPage", true);
    return "post";
  }

  @GetMapping("/post/write")
  public String writePage(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
    User author = principalDetail.getUser();
    model.addAttribute("nickname", StringUtils.capitalize(author.getNickname()));
    return "write";
  }

  @PostMapping("/post/write")
  public String enrollPost(Post post, @AuthenticationPrincipal PrincipalDetail principalDetail) {
    User author = principalDetail.getUser();
    post.setAuthor(author);
    postService.enroll(post);
    return "redirect:/";
  }

  @GetMapping("/post/modify")
  public String writePage(@RequestParam Long postId,
                          @AuthenticationPrincipal PrincipalDetail principalDetail,
                          Model model) {
    Post targetPost = postService.getPost(postId);
    User author = principalDetail.getUser();
    model.addAttribute("nickname", StringUtils.capitalize(author.getNickname()));
    model.addAttribute("title", targetPost.getTitle());
    model.addAttribute("contents", targetPost.getContents());
    return "post-edit";
  }
}
