package com.example.demo.controllers;
import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private final BookService bookService;
    @Autowired
    public Controller(BookService bookService)
    {
        this.bookService = bookService;
    }
    @ResponseBody
    @GetMapping(value = "/books/")
    public List<Book> getAllBooks()
    {
        List<Book> result = new ArrayList<>();
        result.addAll(bookService.getEntities());
        return result;
    }
    @ResponseBody
    @GetMapping(value = "/books/save")
    public List<Book> saveBook(@RequestParam("name") String name, @RequestParam("author") String author)
    {
        bookService.saveBook(name,author);
        return getAllBooks();
    }
}
