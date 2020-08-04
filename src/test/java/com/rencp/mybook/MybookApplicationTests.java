package com.rencp.mybook;

import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class MybookApplicationTests {

    private static final String basePath = "/Users/renchunpeng/Documents/";

    @Test
    void getAllPages() {
        String url = "https://www.biqukan.com/3_3050/";
        try {
            Document doc = Jsoup.connect(url).get();

            Elements links;
            links = doc.select(".listmain").select("dd:gt(13)");

            String bookName = doc.select(".info h2").text();
            String picUrl = doc.select(".cover").select("img").attr("src");

            File file = new File(basePath + bookName);
            // 假如文件夹存在，删除之后重新创建
            if(file.exists()) {
                FileUtil.del(file);
            }
            FileUtil.mkdir(file);

            boolean flag = true;
            for (Element element : links) {
                String pageUrl = url.substring(0, 23)
                        + String.valueOf(element.select("a").attr("href"));
//                System.out.println(pageUrl);
                String pageTitle = element.select("a").text();
//                System.out.println(pageTitle);
                // 断点之后继续下载
//                if(pageTitle.equals("第一百五十八章 收取轻灵之水")) {
//                    flag = true;
//                }
                if(flag) {
                    System.out.println(pageTitle + "开始下载");
                    getDetails(pageUrl, bookName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getDetails(String url, String bookName) throws IOException {
//        String url = "https://www.biqukan.com/4_4316/2051059.html";

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
//        System.out.println(content);

        // 章节名
        String pageName = doc.select(".content").select("h1").text();
        Elements chapters = doc.select(".page_chapter").select("a");

        File file = new File(basePath + bookName +"/"+ pageName +".html");
        file.createNewFile();
        FileUtil.appendString(content, file, "utf-8");

        // 上一章
        String pre = url.substring(0, 22) + chapters.get(0).attr("href");

        // 下一章
        String next = url.substring(0, 22) + chapters.get(2).attr("href");

    }

}
