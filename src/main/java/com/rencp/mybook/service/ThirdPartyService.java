package com.rencp.mybook.service;

import java.io.IOException;

/**
 * 第三方对接
 */
public interface ThirdPartyService {

    /**
     * 新暖才文学网书籍下载
     */
    void downloadFromXNCWXW(String bookurl) throws IOException;
}
