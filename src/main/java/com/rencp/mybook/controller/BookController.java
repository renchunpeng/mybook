package com.rencp.mybook.controller;

import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterMapper chapterMapper;

    @RequestMapping("book")
    public List<Book> query(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return bookService.query();
    }

    @RequestMapping("getchapter/{bookid}")
    public void query(@PathVariable String bookid,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse response) throws IOException {
        Chapter chapter = new Chapter();
        chapter.setId(1);
        Chapter chapter1 = chapterMapper.selectOne(chapter);
        // todo 这里应该在外面套一层布局，设定该页面的编码方式，然后response设定为相同的编码就可以应对不同浏览器不同的解码方式了
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(chapter1.getChapterContent());
    }
}
