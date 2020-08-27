package com.rencp.mybook.pojo.vo;

import com.rencp.mybook.pojo.Chapter;

public class ChapterVO extends Chapter {
    int nextChapterId;

    public int getNextChapterId() {
        return nextChapterId;
    }

    public void setNextChapterId(int nextChapterId) {
        this.nextChapterId = nextChapterId;
    }
}
