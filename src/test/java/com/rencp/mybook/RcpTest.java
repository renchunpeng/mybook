package com.rencp.mybook;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.pojo.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RcpTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void get() {
        List<Book> books = bookMapper.selectAll();
        System.out.println(books.size());
    }
}
