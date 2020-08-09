package com.rencp.mybook.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Book {
    /**
     * 书籍编号
     */
    @Id
    private Integer id;

    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 书籍远程地址
     */
    @Column(name = "book_url")
    private String bookUrl;

    /**
     * 书籍图片地址
     */
    @Column(name = "book_img")
    private String bookImg;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 本记录创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 书籍最后更新时间
     */
    @Column(name = "last_update")
    private Date lastUpdate;

    /**
     * 和网站最后比对时间
     */
    @Column(name = "last_comparison")
    private Date lastComparison;

    /**
     * 获取书籍编号
     *
     * @return id - 书籍编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置书籍编号
     *
     * @param id 书籍编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书籍名称
     *
     * @return book_name - 书籍名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书籍名称
     *
     * @param bookName 书籍名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取书籍远程地址
     *
     * @return book_url - 书籍远程地址
     */
    public String getBookUrl() {
        return bookUrl;
    }

    /**
     * 设置书籍远程地址
     *
     * @param bookUrl 书籍远程地址
     */
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    /**
     * 获取书籍图片地址
     *
     * @return book_img - 书籍图片地址
     */
    public String getBookImg() {
        return bookImg;
    }

    /**
     * 设置书籍图片地址
     *
     * @param bookImg 书籍图片地址
     */
    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取简介
     *
     * @return introduction - 简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置简介
     *
     * @param introduction 简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取本记录创建时间
     *
     * @return create_time - 本记录创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置本记录创建时间
     *
     * @param createTime 本记录创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取书籍最后更新时间
     *
     * @return last_update - 书籍最后更新时间
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置书籍最后更新时间
     *
     * @param lastUpdate 书籍最后更新时间
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取和网站最后比对时间
     *
     * @return last_comparison - 和网站最后比对时间
     */
    public Date getLastComparison() {
        return lastComparison;
    }

    /**
     * 设置和网站最后比对时间
     *
     * @param lastComparison 和网站最后比对时间
     */
    public void setLastComparison(Date lastComparison) {
        this.lastComparison = lastComparison;
    }
}