package com.rencp.mybook.service;

import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.vo.BookChapterVO;
import com.rencp.mybook.pojo.vo.ChapterVO;

import java.util.List;

public interface BookService {

    /**
     * 获取章节信息
     */
    ChapterVO getChapter(String chapterId);

    /**
     * 查询所有书籍信息
     */
    List<Book> queryBook();

    /**
     * 获取一定范围内的章节信息
     */
    List<BookChapterVO> getlatestchapter(int start, int end);
}
