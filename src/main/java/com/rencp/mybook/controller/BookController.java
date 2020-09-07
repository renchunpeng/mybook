package com.rencp.mybook.controller;

import cn.hutool.http.HtmlUtil;
import com.rencp.mybook.mapper.BookMapper;
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
import org.springframework.util.StringUtils;
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
     * 将书籍添加到书库
     * @param url 被添加书籍到url地址
     * @return
     */
    @GetMapping("addbook")
    @ResponseBody
    public String addBook(@RequestParam String url) {
        return bookService.addBook(url);
    }

    /**
     * 下载书籍
     * @param bookId 书籍id
     * @return
     */
    @GetMapping("downloadBook/{bookId}")
    @ResponseBody
    public String query(@PathVariable int bookId) {
        utilService.downloadBook(bookId);
        return "下载成功";
    }

    /**
     * 跳转到书籍列表
     * @param model
     * @return
     */
    @RequestMapping("book")
    public String queryBookList(Model model) {
        List<Book> books = bookService.queryBook();
        model.addAttribute("data", books);
        return "book";
    }

    /**
     * 跳转到章节列表
     * @param bookId
     * @param model
     * @return
     */
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
