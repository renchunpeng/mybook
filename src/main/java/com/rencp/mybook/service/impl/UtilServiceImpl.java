package com.rencp.mybook.service.impl;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Book;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.service.UtilService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class UtilServiceImpl implements UtilService {
    private static final Logger logger = LoggerFactory.getLogger(UtilServiceImpl.class);

    private static final String url = "https://www.biqukan.com/paihangbang";
    private static final String webUrl = "https://www.biqukan.com";

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public Map<String, String> getChapterByBookUrl(String bookUrl) {

        Map<String, String> chapters = new LinkedHashMap<>(16);
        try {
            Document doc = Jsoup.connect(bookUrl).get();

            Elements links;
            links = doc.select(".listmain").select("dd:gt(13)");

            for (Element element : links) {
                String pageUrl = bookUrl.substring(0, 23)
                        + element.select("a").attr("href");
                String pageTitle = element.select("a").text();

                chapters.put(pageTitle, pageUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public Chapter getContentByChapterUrl(String chapterUrl) {
        Document doc = null;
        while (null == doc) {
            try {
                doc = Jsoup.connect(chapterUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文章主体
        assert doc != null;
        Elements links = doc.select("#content");
        String content = links.toString();

        // 章节名
        String pageName = doc.select(".content").select("h1").text();

        Chapter chapter = new Chapter();
        chapter.setChapterContent(content);
        chapter.setChapterName(pageName);
        return chapter;
    }

    @Override
    public List<Book> getPaiHangBangBook() {
        List<Book> books = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();

            Elements contents= doc.select(".wrap").get(1).select(".block");
            for (int i = 0; i < contents.size(); i++) {
                logger.info("榜单类型：" + contents.get(i).select("h2").text());
                Elements ul_li = contents.get(i).select("ul li");
                for (Element element : ul_li) {
                    String bookName = element.select("a").text();
                    String bookUrl = element.select("a").attr("href");
                    logger.info("获取到书籍，书名为：{},地址为：{}",bookName,webUrl + bookUrl);

                    Book book = new Book();
                    book.setBookName(bookName);
                    book.setBookUrl(webUrl + bookUrl);
                    book.setCreateTime(new Date());
                    book.setLastComparison(new Date());
                    book.setBookImg("未知");
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void downloadBook(String bookUrl) {
        int preChapterId = 0;
        Map<String, String> chapterByBookUrl = getChapterByBookUrl(bookUrl);
        for(Map.Entry<String, String> entry : chapterByBookUrl.entrySet()) {
            Chapter chapter = getContentByChapterUrl(entry.getValue());
            chapter.setBookId(1);
            chapter.setCreateTime(new Date());
            chapter.setPreChapterId(preChapterId);
            chapterMapper.insert(chapter);
            logger.info("{},保存成功", entry.getKey());
            preChapterId = chapter.getId();
        }
    }

}
