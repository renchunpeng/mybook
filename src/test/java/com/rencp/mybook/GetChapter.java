package com.rencp.mybook;

import com.rencp.mybook.mapper.ChapterMapper;
import com.rencp.mybook.pojo.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class GetChapter {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 获取该书籍的所有章节内容
     */
    @Test
    void getAllPages() {
        String url = "https://www.biqukan.com/0_178/";
        try {
            Document doc = Jsoup.connect(url).get();

            Elements links;
            links = doc.select(".listmain").select("dd:gt(13)");

            boolean flag = true;
            for (Element element : links) {
                String pageUrl = url.substring(0, 23)
                        + element.select("a").attr("href");
                String pageTitle = element.select("a").text();

                if (flag) {
                    System.out.println(pageTitle + "开始下载");
                    getDetails(pageUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getDetails(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 文章主体
        assert doc != null;
        Elements links = doc.select("#content");
        String content = links.toString();

        // 章节名
        String pageName = doc.select(".content").select("h1").text();

        Chapter chapter = new Chapter();
        chapter.setBookId(1);
        chapter.setChapterContent(content);
        chapter.setChapterName(pageName);
        chapter.setCreateTime(new Date());
        chapterMapper.insert(chapter);
    }

}
