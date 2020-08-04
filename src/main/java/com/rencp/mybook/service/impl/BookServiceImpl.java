package com.rencp.mybook.service.impl;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> query() {
        return bookMapper.selectAll();
    }
}
