package com.simple.blog;

import com.simple.blog.model.Post;
import com.simple.blog.model.User;
import com.simple.blog.repository.PostRepository;
import com.simple.blog.repository.UserRepository;
import com.simple.blog.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolationException;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner test(PostRepository postRepository, UserService userService) {
		return args -> {
			User u1 = new User();
			u1.setUsername("a");
			u1.setPassword("a");
			u1.setNickname("Choi");
			try {
				userService.register(u1);
			} catch (ConstraintViolationException e) {
				System.out.println(e.getLocalizedMessage());
			} catch (Exception e) {
				System.out.println(e);
			}

			for (int i = 0 ; i<35; i++) {
				Post p1 = new Post();
				p1.setAuthor(u1);
				p1.setTitle("제목은 이렇네요~" +i);
				p1.setContents("내용은 이렇네요~" +i);
				postRepository.save(p1);
			}
		};
	}
	 */

}
