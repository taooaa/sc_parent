package com.tao.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.commonutils.R;
import com.tao.eduservice.pojo.EduCourse;
import com.tao.eduservice.pojo.EduTeacher;
import com.tao.eduservice.service.EduCourseService;
import com.tao.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    EduCourseService courseService;
    @Autowired
    EduTeacherService teacherService;

    //查询前八条热门课程，前四条名师
    @GetMapping("index")
    public R index(){
        List<EduCourse> eduList = courseService.listCourse();

        List<EduTeacher> teacherList = teacherService.listTeacher();

        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
