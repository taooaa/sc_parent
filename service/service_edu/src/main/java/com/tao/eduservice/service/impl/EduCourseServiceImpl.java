package com.tao.eduservice.service.impl;

import com.tao.eduservice.pojo.EduCourse;
import com.tao.eduservice.mapper.EduCourseMapper;
import com.tao.eduservice.pojo.EduCourseDescription;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.service.EduCourseDescriptionService;
import com.tao.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.serviceBase.execeptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
