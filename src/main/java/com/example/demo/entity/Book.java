package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;



    @Column(nullable = false)
    private final String author;
    @Column(nullable = false)
    private final String name;
    public Book()
    {
        name="";
        author="";
    }
@Autowired
    public Book(String author, String name) {
        this.author = author;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }
}
