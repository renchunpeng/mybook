package com.rencp.mybook.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Chapter {
    /**
     * 章节编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 上一章节编号
     */
    @Column(name = "pre_chapter_id")
    private Integer preChapterId;

    /**
     * 书籍编号
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 章节名称
     */
    @Column(name = "chapter_name")
    private String chapterName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 章节内容
     */
    @Column(name = "chapter_content")
    private String chapterContent;

    public Integer getPreChapterId() {
        return preChapterId;
    }

    public void setPreChapterId(Integer preChapterId) {
        this.preChapterId = preChapterId;
    }

    /**
     * 获取章节编号
     *
     * @return id - 章节编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置章节编号
     *
     * @param id 章节编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书籍编号
     *
     * @return book_id - 书籍编号
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置书籍编号
     *
     * @param bookId 书籍编号
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取章节名称
     *
     * @return chapter_name - 章节名称
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * 设置章节名称
     *
     * @param chapterName 章节名称
     */
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取章节内容
     *
     * @return chapter_content - 章节内容
     */
    public String getChapterContent() {
        return chapterContent;
    }

    /**
     * 设置章节内容
     *
     * @param chapterContent 章节内容
     */
    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }
}