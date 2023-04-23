package com.aptech.blog;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Blog;
import com.aptech.blog.model.Role;
import com.aptech.blog.model.UserRole;
import com.aptech.blog.repository.BlogRepository;
import com.aptech.blog.repository.RoleRepository;
import com.aptech.blog.repository.UserRepository;

@SpringBootApplication

public class BlogApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BlogApplication.class, args);

		// BlogRepository repository = context.getBean(BlogRepository.class);
		// Random random = new Random();

		// for (int i = 1; i <= 1000; i++) {
		// int rating = random.ints(1,5).findFirst().getAsInt();
		// Blog blog = new Blog();
		// blog.setBlogId(i).setTitle("Title " + i)
		// .setUrl("http://localhost:8080/blog/" + i)
		// .setRating(rating);
		// repository.save(blog);
		// }

	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {

			Role adminRole = roleRepository.findByRole(UserRole.ADMIN);
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setRole(UserRole.ADMIN);
				roleRepository.save(adminRole);
			}

			Role userRole = roleRepository.findByRole(UserRole.USER);
			if (userRole == null) {
				userRole = new Role();
				userRole.setRole(UserRole.USER);
				roleRepository.save(userRole);
			}

		};
	}

}
