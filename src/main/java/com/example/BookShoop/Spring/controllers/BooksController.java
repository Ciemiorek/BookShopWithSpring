package com.example.BookShoop.Spring.controllers;

import com.example.BookShoop.Spring.models.Book;
import com.example.BookShoop.Spring.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createdBook(@RequestBody  Book book){
        return bookService.addBook(book);
    }

    @GetMapping("")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id){
        return bookService.getBook(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id ){
        bookService.deleteBook(id);
        return "OK";
    }


}
