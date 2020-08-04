package com.rencp.mybook.controller;

import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("book")
    public List<Book> query() {
        return bookService.query();
    }
}
