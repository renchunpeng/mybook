package com.rencp.mybook.controller;

import cn.hutool.http.HtmlUtil;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.pojo.vo.BookChapterVO;
import com.rencp.mybook.pojo.vo.ChapterVO;
import com.rencp.mybook.service.BookService;
import com.rencp.mybook.service.ChapterService;
import com.rencp.mybook.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private UtilService utilService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

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

    @RequestMapping("chapter/{bookId}")
    public String queryChapterList(@PathVariable int bookId , Model model) {
        List<BookChapterVO> chapters = chapterService.queryChapterList(bookId);
        model.addAttribute("data", chapters);
        return "chapter";
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
        String script = HtmlUtil.removeHtmlTag(chapterVO.getChapterContent(), "script");
        chapterVO.setChapterContent(script);
        model.addAttribute("chapterVO", chapterVO);
        return "page";
    }

    /**
     * 获取当前章节上下十章的信息
     * @return
     */
    @GetMapping("/getlatestchapter/{chapterId}")
    @ResponseBody
    public List<BookChapterVO> getlatestchapter(@PathVariable int chapterId) {
        int start = chapterId - 10;
        int end = chapterId + 10;
        List<BookChapterVO> getlatestchapter = bookService.getlatestchapter(start, end);
        return getlatestchapter;
    }
}
