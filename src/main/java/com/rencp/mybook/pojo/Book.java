package com.rencp.mybook.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Book {
    /**
     * 图书ID
     */
    @Id
    @Column(name = "book_id")
    private String bookId;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 馆藏数量
     */
    @Column(name = "book_url")
    private String bookUrl;

    @Column(name = "book_img")
    private String bookImg;

    /**
     * 获取图书ID
     *
     * @return book_id - 图书ID
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置图书ID
     *
     * @param bookId 图书ID
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取图书名称
     *
     * @return name - 图书名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置图书名称
     *
     * @param name 图书名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取馆藏数量
     *
     * @return book_url - 馆藏数量
     */
    public String getBookUrl() {
        return bookUrl;
    }

    /**
     * 设置馆藏数量
     *
     * @param bookUrl 馆藏数量
     */
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    /**
     * @return book_img
     */
    public String getBookImg() {
        return bookImg;
    }

    /**
     * @param bookImg
     */
    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }
}