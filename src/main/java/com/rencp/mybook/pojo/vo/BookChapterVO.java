package com.rencp.mybook.pojo.vo;

import javax.persistence.Column;

public class BookChapterVO {

    private int id;

    @Column(name = "chapter_name")
    private String chapterName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
