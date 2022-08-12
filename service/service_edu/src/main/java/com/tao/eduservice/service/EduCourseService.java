package com.tao.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.eduservice.pojo.frontvo.CourseFrontVo;
import com.tao.eduservice.pojo.frontvo.CourseWebVo;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.pojo.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

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

    //条件查询课程分页
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);

}
