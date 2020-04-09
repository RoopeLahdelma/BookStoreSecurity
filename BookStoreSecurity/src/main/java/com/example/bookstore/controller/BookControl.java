package com.example.bookstore.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;



@Controller
public class BookControl {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository Crepository;
	
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	
	@RequestMapping(value= {"/", "booklist"})
	public String Booklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	@RequestMapping(value= {"/add"})
	public String addbook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", Crepository.findAll());
		return "addbook";
	}
	
	
	
	@RequestMapping(value= "/save", method=RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value= "/delete/{id}", method=RequestMethod.GET)
	public String DeleteBook(@PathVariable("id")Long Bookid, Model model) {
		repository.deleteById(Bookid);
		return "redirect:../booklist";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editBook(@PathVariable("id") Long bookId, Model model) {
	    	
	    	model.addAttribute("book", repository.findById(bookId));
	    	model.addAttribute("categories", Crepository.findAll());
	        return "editbook";
	    } 
	
	

}
