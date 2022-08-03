package com.tao.eduservice.controller;


import com.tao.commonutils.R;
import com.tao.eduservice.pojo.vo.CourseInfoVo;
import com.tao.eduservice.service.EduChapterService;
import com.tao.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }

}

