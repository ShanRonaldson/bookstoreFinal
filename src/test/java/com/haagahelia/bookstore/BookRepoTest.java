package com.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haagahelia.bookstore.domain.Book;
import com.haagahelia.bookstore.domain.BookRepository;
import com.haagahelia.bookstore.domain.Category;

import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepoTest {

	@Autowired
	private BookRepository repo;

	@Test
	public void findByAuthorShouldReturnAuthor() {
		List<Book> books = repo.findByAuthor("JRR Tolkien");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualToIgnoringCase("The Hobbit");
	}

	@Test
	public void findByTitleShouldReturnTitle() {
		List<Book> books = repo.findByTitle("The Hobbit");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualToIgnoringCase("JRR Tolkien");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Hacking Growth", "Sean Ellis", "123124asd", "2010", "24.95", new Category("Non-Fiction"));
		repo.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repo.findByAuthor("JRR Tolkien");
		Book book = books.get(0);
		repo.delete(book);
		List<Book> newBooks = repo.findByAuthor("JRR Tolkien");
		assertThat(newBooks).hasSize(0);
	}
}
