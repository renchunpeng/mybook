package com.rencp.mybook.service.impl;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.service.ThirdPartyService;
import com.rencp.mybook.utils.JsoupUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    @Transactional
    public void downloadFromXNCWXW(String bookurl) throws IOException {
        Book book = saveBook(bookurl);
        saveChapter(bookurl, book.getId());
    }

    /**
     * 根据书籍url获取书籍信息并保存
     *
     * @param bookurl
     * @return
     * @throws IOException
     */
    private Book saveBook(String bookurl) throws IOException {
        Book book = new Book();
        Document document = JsoupUtils.getDocument(bookurl);
        String bookName = document.select("#info > h1").text();
        String tempAuthor = document.select("#info > p:nth-child(2)").text();
        String author = tempAuthor.substring(tempAuthor.indexOf("：") + 1);
        String bookImg = document.select("#fmimg > img").attr("src");
        String introduction = document.select("#intro > p").text();
        book.setBookName(bookName);
        book.setBookImg(bookImg);
        book.setAuthor(author);
        book.setIntroduction(introduction);
        book.setBookUrl(bookurl);
        book.setCreateTime(new Date());
        book.setLastComparison(new Date());
        bookMapper.insert(book);
        return book;
    }

    /**
     * 根据书籍url获取所有章节内容并保存
     *
     * @param bookUrl
     * @param bookId
     * @throws IOException
     */
    private void saveChapter(String bookUrl, int bookId) throws IOException {
        int preChapterId = 0;
        Document document = JsoupUtils.getDocument(bookUrl);
        Elements elements = document.select("#list > ul > li > a");
        for (Element element : elements) {
            String chapterUrl = bookUrl + element.attr("href");
            Document chapterContent = JsoupUtils.getDocument(chapterUrl);
            String content = chapterContent.select("#content").toString();
            String tempChapterName = chapterContent.select("#wrapper > div.content_read > div > div.bookname > h1").text();
            String chapterName = tempChapterName.replace("章节目录 ", "");
            Chapter chapter = new Chapter();
            chapter.setBookId(bookId);
            chapter.setPreChapterId(preChapterId);
            chapter.setChapterName(chapterName);
            chapter.setChapterContent(content);
            chapter.setCreateTime(new Date());
            chapterMapper.insert(chapter);
            System.out.println(chapterName + "保存成功");
            preChapterId = chapter.getId();
        }
    }
}
