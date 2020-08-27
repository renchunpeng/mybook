package com.rencp.mybook;

import com.rencp.mybook.mapper.BookMapper;
import com.rencp.mybook.pojo.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
public class GetBook {

    @Autowired
    private BookMapper bookMapper;

    private static final String webUrl = "https://www.biqukan.com";

    /**
     * 批量抽取排行榜单书籍信息
     */
    @Test
    public void get() {
        String url = "https://www.biqukan.com/paihangbang";
        try {
            Document doc = Jsoup.connect(url).get();

            Elements contents= doc.select(".wrap").get(1).select(".block");
            for (int i = 0; i < contents.size(); i++) {
                System.out.println(contents.get(i).select("h2").text());
                Elements ul_li = contents.get(i).select("ul li");
                for (Element element : ul_li) {
                    String bookName = element.select("a").text();
                    String bookUrl = element.select("a").attr("href");
                    System.out.println(bookName);
                    System.out.println(bookUrl);

                    Book book = new Book();
                    book.setBookName(bookName);
                    book.setBookUrl(webUrl + bookUrl);
                    book.setCreateTime(new Date());
                    book.setLastComparison(new Date());
                    book.setBookImg("未知");
                    bookMapper.insert(book);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
