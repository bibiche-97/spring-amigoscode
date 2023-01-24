package com.avimtoo.springamigoscode.services;

import com.avimtoo.springamigoscode.entities.Book;
import com.avimtoo.springamigoscode.exception.ResourceNotFoundException;
import com.avimtoo.springamigoscode.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

   @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getAllBook(){
       return bookRepository.findAll();
    }

    public Book addSingleBook(Book book) {

       return bookRepository.save(book);
    }

    public Optional<Book> getByOne(Long id){
       return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
       bookRepository.deleteById(id);
    }

    public Book updateBook(Book book,Long id){
       Book book1 = bookRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("Oups! Impossible de modifier ce book car il  n'existe pas."));
       book1.setBookName(book.getBookName());
       book1.setCreatedAt(book.getCreatedAt());

       return bookRepository.saveAndFlush(book1);
    }
}
