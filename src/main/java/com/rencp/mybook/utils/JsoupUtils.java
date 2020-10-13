package com.rencp.mybook.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtils {

    /**
     * 根据url获取html，默认重试10次
     *
     * @param url
     * @return
     */
    public static Document getDocument(String url) throws IOException {
        Document document = null;
        int flag = 0;
        while (null == document && flag < 10) {
            flag++;
            document = Jsoup.connect(url).get();
        }

        return document;
    }
}
