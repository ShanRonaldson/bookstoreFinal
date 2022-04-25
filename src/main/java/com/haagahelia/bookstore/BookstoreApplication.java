package com.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.haagahelia.bookstore.domain.Book;
import com.haagahelia.bookstore.domain.BookRepository;
import com.haagahelia.bookstore.domain.Category;
import com.haagahelia.bookstore.domain.CategoryRepository;
import com.haagahelia.bookstore.domain.User;
import com.haagahelia.bookstore.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository bookRepo, CategoryRepository catRepo, UserRepository urepo) {
		return (args) -> {
			log.info("save a couple of books");

			catRepo.save(new Category("Fantasy"));
			catRepo.save(new Category("Fiction"));
			catRepo.save(new Category("Biography"));

			bookRepo.save(new Book("The Hobbit", "JRR Tolkien", "12345ASW", "1937", "12.99",
					catRepo.findByName("Fantasy").get(0)));
			bookRepo.save(new Book("Priory of the Orange Tree", "Samantha Shannon", "1234545YUY", "2018", "16.00",
					catRepo.findByName("Fantasy").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$12$jiH8pWYx0ale0DKo04T8tey8kOgmfmFkpu8T5KHaWLOV8EnN.SQGC", "USER");
			User user2 = new User("admin", "$2a$12$8jP7WNhAYjFpA/2JDZoqluCGxQtj3O1oi.VvMO1NZ0gwYcApYWLv2", "ADMIN");
			urepo.save(user1);
			urepo.save(user2);

			log.info("fetch all students");
			for (Book book : bookRepo.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
