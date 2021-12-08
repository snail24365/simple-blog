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

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(PostRepository postRepository, UserService userService) {
		return args -> {
			User u1 = new User();
			u1.setUsername("a");
			u1.setPassword("a");
			userService.register(u1);
			Post p1 = new Post();
			p1.setAuthor(u1);
			p1.setTitle("title");
			p1.setContents("content");
			postRepository.save(p1);

			Post p2 = new Post();
			p1.setAuthor(u1);
			p1.setTitle("title2");
			p1.setContents("content2");
			postRepository.save(p2);

		};
	}

}
