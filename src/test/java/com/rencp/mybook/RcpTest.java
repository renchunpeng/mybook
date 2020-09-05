package com.rencp.mybook;

import cn.hutool.http.HtmlUtil;
import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.service.UtilService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RcpTest {
    @Autowired
    private UtilService utilService;

    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void test() {
        List<Book> paiHangBangBook = utilService.getPaiHangBangBook();
        paiHangBangBook.forEach(item -> {
            String bookName = item.getBookName();
            System.out.println(bookName);
        });
    }

    @Test
    public void er() {
        Chapter chapter = new Chapter();
        chapter.setCreateTime(new Date());
        chapter.setChapterName("zhangjiem");
        chapter.setBookId(123);
        chapter.setChapterContent("hahahha");
        chapterMapper.insert(chapter);
        System.out.println(chapter.getId());


    }
}
