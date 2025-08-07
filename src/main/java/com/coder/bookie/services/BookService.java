package com.coder.bookie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.bookie.exceptions.BookNotFoundException;
import com.coder.bookie.models.Book;
import com.coder.bookie.repos.BookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private final BookRepo bookRepo;

    public List<Book> all() {
        return bookRepo.findAll();
    }
     public Book get(Long id){
       return bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("No books with that id"));

    }
    public void add(Book book){
        bookRepo.save(book);
    }

    public void update(Book book){
        bookRepo.save(book);
    }
    public void drop(Long id){
        Book book=get(id);
        bookRepo.deleteById(book.getId());
    }
   
}
