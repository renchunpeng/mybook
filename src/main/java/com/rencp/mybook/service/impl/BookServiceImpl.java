package com.rencp.mybook.service.impl;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.pojo.vo.BookChapterVO;
import com.rencp.mybook.pojo.vo.ChapterVO;
import com.rencp.mybook.service.BookService;
import com.rencp.mybook.service.UtilService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UtilService utilService;

    @Override
    public List<Book> queryBook() {
        List<Book> books = bookMapper.selectAll();
        return books;
    }

    @Override
    public List<BookChapterVO> getlatestchapter(int start, int end) {
        if(start < 0) {
            start = 0;
        }
        List<BookChapterVO> chapters = chapterMapper.getlatestchapter(start, end);
        return chapters;
    }

    @Override
    public String addBook(String url) {
        Book book = new Book();
        book.setBookUrl(url);
        List<Book> books = bookMapper.select(book);
        if (null == book || books.size()<1){
            Book bookInfo = utilService.getBookInfo(url);
            bookMapper.insert(bookInfo);
            return "新增成功";
        }else {
            return "该书籍已存在";
        }
    }

    @Override
    public ChapterVO getChapter(String chapterId) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
        Example example = new Example(Chapter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("preChapterId", chapterId);
        List<Chapter> chapters = chapterMapper.selectByExample(example);
        ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties(chapter, chapterVO);
        if(chapters.size() > 0){
            chapterVO.setNextChapterId(chapters.get(0).getId());
        }else {
            chapterVO.setNextChapterId(0);
        }

        return chapterVO;
    }
}
