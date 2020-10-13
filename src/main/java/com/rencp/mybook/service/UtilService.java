package com.rencp.mybook.service;

import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;

import java.util.List;
import java.util.Map;

public interface UtilService {

    /**
     * 根据url获取该书籍的所有章节地址（数据url必须是全地址的,<章节名称，章节地址>）
     */
    Map<String, String> getChapterByBookUrl(String bookUrl);

    /**
     * 根据章节url返回该章节的内容(章节url必须是全地址的)
     */
    Chapter getContentByChapterUrl(String chapterUrl);

    /**
     * 获取排行榜的书籍信息
     *
     * @return
     */
    List<Book> getPaiHangBangBook();

    /**
     * 下载书籍
     */
    void downloadBook(int bookId);

    /**
     * 根据书籍url获取书籍到信息
     */
    Book getBookInfo(String url);
}
