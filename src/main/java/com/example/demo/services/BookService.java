package com.example.demo.services;

import com.example.demo.entity.Book;

import com.example.demo.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IService<Book> {
    private final BookRepository repository;
    @Autowired
    public BookService(BookRepository repository)
    {
        this.repository = repository;
    }
    public List<Book> getEntities()
    {
        List<Book> result = new ArrayList<>();
         repository.findAll().forEach(result::add);
         for (var x:result)
             System.out.println(x);
         return result;
    }

    @Override
    public Book getEntityById(long id) {
        return repository.findById(id).get();
    }
    public void saveBook(String name, String author)
    {
        var book = new Book(author,name);
        repository.save(book);
    }
}
