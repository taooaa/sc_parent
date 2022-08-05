package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseIndo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

}
