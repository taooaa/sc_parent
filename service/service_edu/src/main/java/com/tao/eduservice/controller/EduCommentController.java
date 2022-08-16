package com.tao.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.commonutils.JwtUtils;
import com.tao.commonutils.R;
import com.tao.commonutils.vo.UcenterMemberVo;
import com.tao.eduservice.client.UcenterClient;
import com.tao.eduservice.pojo.EduComment;
import com.tao.eduservice.pojo.EduCourse;
import com.tao.eduservice.pojo.vo.CourseQuery;
import com.tao.eduservice.service.EduCommentService;
import com.tao.serviceBase.execeptionHandler.MyException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private EduCommentService commentService;
    //课程评论分页查询
    //根据课程id_分页查询课程评论的方法
    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @PostMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,@ApiParam(name = "limit", value = "每页记录数", required = true)
    @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        commentService.page(pageParam,wrapper);
        List<EduComment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }
    //添加评论
    //添加评论
    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(20001).message("请登录");
        }
        comment.setMemberId(memberId);
        UcenterMemberVo ucenterInfo = ucenterClient.getInfo(memberId);
        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());
        commentService.save(comment);
        return R.ok();
    }


}

