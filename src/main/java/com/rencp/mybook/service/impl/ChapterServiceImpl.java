package com.rencp.mybook.service.impl;

import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.pojo.vo.BookChapterVO;
import com.rencp.mybook.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<BookChapterVO> queryChapterList(int bookId) {
        Chapter item = new Chapter();
        item.setBookId(bookId);
        List<BookChapterVO> select = chapterMapper.getAllChapterName(bookId);
        return select;
    }
}
