package com.rencp.mybook.service;

import com.rencp.mybook.pojo.vo.BookChapterVO;

import java.util.List;

public interface ChapterService {

    /**
     * 获取书籍全部章节信息
     */
    List<BookChapterVO> queryChapterList(int bookId);
}
