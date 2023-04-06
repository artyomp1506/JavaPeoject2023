package com.example.demo.repositories;

import com.example.demo.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long> {

}
