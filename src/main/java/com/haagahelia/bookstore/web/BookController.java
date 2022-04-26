package com.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haagahelia.bookstore.domain.Book;
import com.haagahelia.bookstore.domain.BookRepository;
import com.haagahelia.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository catRepo;

	// Show all students
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "booklist";
	}

	// RESTful service to get all students
	@RequestMapping(value = "/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepo.findAll();
	}

	// RESTful service to get student by id
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return bookRepo.findById(bookId);
	}

	// Add new student
	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catRepo.findAll());
		return "addbook";
	}

	// Save new student
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepo.save(book);
		return "redirect:booklist";
	}

	// Delete student
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepo.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//edit student
	@RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("books", bookRepo.findById(bookId));
		model.addAttribute("categories", catRepo.findAll());
		return "editbook";
	}
}
