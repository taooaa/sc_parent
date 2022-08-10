package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.pojo.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseIndo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);

    List<EduCourse> listCourse();

}
