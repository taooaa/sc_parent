package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.eduservice.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
