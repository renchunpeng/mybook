package com.rencp.mybook.controller;

import com.rencp.mybook.pojo.vo.SearchBook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController extends BaseController {

    @GetMapping("tosearch")
    public String toSearch() {
        return "search";
    }

    /**
     * 书籍搜索
     * @param bookname
     * @return
     */
    @RequestMapping(value = "search")
    @ResponseBody
    public List<SearchBook> search(String bookname) throws UnsupportedEncodingException {
        List<SearchBook> lists = new ArrayList<>();
        Document doc = null;
        try {
            String url = SEARCH_BASEURL  + URLEncoder.encode(bookname,"GBK");
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //查询到的书籍
        Elements links = doc.select("li");

        if (links.size() == 1) {
            // 说明只有title标题,没有查询结果
            return lists;
        }

        // 去掉title
        links.remove(0);
        for (Element element : links) {
            String bookName = element.select(".s2").select("a").text();
            String bookUrl = element.select(".s2").select("a").attr("href");
            String auth = element.select(".s4").text();

            SearchBook sBook = new SearchBook();
            sBook.setBookName(bookName);
            sBook.setBookUrl(bookUrl);
            // 新版搜索结果页面没有图片地址了,先默认,后面添加到书架的时候会更新
            sBook.setBookPicUrl(BASE_PIC_URL);
            sBook.setAuth(auth);

            lists.add(sBook);
        }
        return lists;
    }
}
