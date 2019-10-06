package com.example.BookShoop.Spring.services;

import com.example.BookShoop.Spring.models.Book;
import com.example.BookShoop.Spring.repositories.imp.BookRepositoryIMP;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepositoryIMP bookRepositoryIMP;

    public BookService(BookRepositoryIMP bookRepositoryIMP) {
        this.bookRepositoryIMP = bookRepositoryIMP;
    }

    public Book addBook(Book book) {
        return bookRepositoryIMP.addBook(book);
    }

    public List<Book> getBooks() {
        return bookRepositoryIMP.getBooks();
    }

    public Book getBook(int id) {
        return bookRepositoryIMP.getBook(id);
    }

    public void deleteBook(int id) {
        bookRepositoryIMP.deleteBook(id);
    }
}
