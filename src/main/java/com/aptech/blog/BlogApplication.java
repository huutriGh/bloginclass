package com.aptech.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
