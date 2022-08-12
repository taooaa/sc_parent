package com.tao.eduservice.mapper;

import com.tao.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tao.eduservice.pojo.frontvo.CourseWebVo;
import com.tao.eduservice.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}

