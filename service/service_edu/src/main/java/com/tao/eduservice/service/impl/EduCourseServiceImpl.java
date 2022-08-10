package com.tao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.eduservice.pojo.EduCourse;
import com.tao.eduservice.mapper.EduCourseMapper;
import com.tao.eduservice.pojo.EduCourseDescription;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.pojo.vo.CoursePublishVo;
import com.tao.eduservice.service.EduChapterService;
import com.tao.eduservice.service.EduCourseDescriptionService;
import com.tao.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.eduservice.service.EduVideoService;
import com.tao.serviceBase.execeptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            //添加失败
            throw new MyException(20001,"添加课程信息失败");
        }
        //课程id
        String cid = eduCourse.getId();

        //2.向简介表添加数据
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //2.查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseIndo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0){
            throw new MyException(20001,"修改课程信息失败");

        }

        //修改描述信息
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,description);
        eduCourseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    @Transactional
    public void removeCourse(String courseId) {
        //1.根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //2.根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //3.根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);
        //4.根据课程id删除课程本身
        int i = baseMapper.deleteById(courseId);
        if(i == 0) {
            throw new MyException(20001,"删除失败");
        }
    }

    @Override
    @Cacheable(key = "'selectCourse'",value = "course")
    public List<EduCourse> listCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> list = eduCourseService.list(wrapper);
        return list;
    }

}
