package com.rencp.mybook.controller;

import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.pojo.vo.ChapterVO;
import com.rencp.mybook.service.BookService;
import com.rencp.mybook.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private UtilService utilService;

    @Autowired
    private BookService bookService;

    /**
     * 下载书籍
     * @param bookUrl 书籍url
     * @return
     */
    @RequestMapping("downloadBook")
    @ResponseBody
    public String query(@RequestBody String bookUrl) {
//        bookurl = "https://www.biqukan.com/1_1680/";
        utilService.downloadBook(bookUrl);
        return "下载成功";
    }

    @RequestMapping("book")
    public String queryBookList(Model model) {
        List<Book> books = bookService.queryBook();
        model.addAttribute("data", books);
        return "book";
    }

    /**
     * 查看章节内容
     * @param chapterId 章节id
     * @param model 视图
     * @return
     */
    @RequestMapping("getchapter/{chapterId}")
    public String query(@PathVariable String chapterId, Model model) {
        ChapterVO chapterVO = bookService.getChapter(chapterId);
        model.addAttribute("chapterVO", chapterVO);
        return "page";
    }
}
