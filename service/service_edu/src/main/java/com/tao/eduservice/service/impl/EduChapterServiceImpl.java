package com.tao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.eduservice.pojo.EduChapter;
import com.tao.eduservice.mapper.EduChapterMapper;
import com.tao.eduservice.pojo.EduVideo;
import com.tao.eduservice.pojo.chapter.ChapterVo;
import com.tao.eduservice.pojo.chapter.VideoVo;
import com.tao.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.eduservice.service.EduVideoService;
import com.tao.serviceBase.execeptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1.根据课程id查询课程里面的章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapper);

        //2.查询小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合。用于最终数据封装
        List<ChapterVo> finalList = new ArrayList<>();

        //遍历章节list封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);


            List<VideoVo> videoList = new ArrayList<>();
            // 遍历小节list封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                EduVideo eduVideo = eduVideoList.get(m);
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            //放入章节对象
            chapterVo.setChildren(videoList);
        }


        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0) {
            throw new MyException(20001, "不能删除");

        } else {
            boolean result = eduChapterService.removeById(chapterId);
            return result;
        }
    }
}
