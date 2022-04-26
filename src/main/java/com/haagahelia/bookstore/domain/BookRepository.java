package com.haagahelia.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitleIgnoreCase(String title);

	List<Book> findByAuthorIgnoreCase(String author);
}
