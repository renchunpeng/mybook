package com.rencp.mybook.mapper;

import com.rencp.mybook.my.mapper.MyMapper;
import com.rencp.mybook.pojo.Chapter;
import com.rencp.mybook.pojo.vo.BookChapterVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChapterMapper extends MyMapper<Chapter> {

    /**
     * 获取书籍全部章节名称
     * @param bookId
     * @return
     */
    @Select("select id,chapter_name from chapter where book_id = #{bookId}")
    List<BookChapterVO> getAllChapterName(int bookId);

    @Select("select id,chapter_name from chapter where id between #{start} and #{end}")
    List<BookChapterVO> getlatestchapter(int start, int end);
}