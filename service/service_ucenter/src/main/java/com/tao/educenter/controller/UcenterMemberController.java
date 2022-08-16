package com.tao.educenter.controller;


import com.tao.commonutils.JwtUtils;
import com.tao.commonutils.R;
import com.tao.commonutils.vo.UcenterMemberVo;
import com.tao.educenter.pojo.UcenterMember;
import com.tao.educenter.pojo.vo.RegisterVo;
import com.tao.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-11
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用jwt根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id得到用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

    //根据用户id查询用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberVo getInfo(@PathVariable String id) {
//根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberVo member = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,member);
        return member;
    }

}

