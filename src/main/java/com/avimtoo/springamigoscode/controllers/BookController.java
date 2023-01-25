package com.avimtoo.springamigoscode.controllers;

import com.avimtoo.springamigoscode.entities.Book;
import com.avimtoo.springamigoscode.entities.Student;
import com.avimtoo.springamigoscode.repositories.BookRepository;
import com.avimtoo.springamigoscode.repositories.StudentRepository;
import com.avimtoo.springamigoscode.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired private final BookService bookService;
    public  BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/books")
    public List<Book>  getAll(){
        return bookService.getAllBook();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getOne(@PathVariable("id") Long id){
        return bookService.getByOne(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return bookService.addSingleBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteSingleBook(@PathVariable("id") Long id)
    {
        bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book book, @PathVariable("id") Long id){
        return bookService.updateBook(book,id);
    }

    // ajouter un livre
    @PostMapping("/books/{id}")
    public Book addSingleBookInStudent(@PathVariable("id") Long id,@RequestBody Book book){
        Student stu = studentRepository.getOne(id);
        book.setStudent(stu);
      Book book1 =  bookRepository.save(book);
        stu.addBook(book1);
        studentRepository.save(stu);
        return book1;
    }


}
