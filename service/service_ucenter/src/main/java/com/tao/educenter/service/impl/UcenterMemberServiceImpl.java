package com.tao.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.commonutils.JwtUtils;
import com.tao.commonutils.MD5;
import com.tao.educenter.pojo.UcenterMember;
import com.tao.educenter.mapper.UcenterMemberMapper;
import com.tao.educenter.pojo.vo.RegisterVo;
import com.tao.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.serviceBase.execeptionHandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //登录方法
    @Override
    public String login(UcenterMember member) {

        //获取登录手机号i和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //判断非空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new MyException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if(mobileMember == null){
            throw new MyException(20001,"手机号不存在");
        }
        //判断密码
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new MyException(20001,"密码错误");
        }
        //判断用户是否被禁用
        if (mobileMember.getIsDisabled()){
            throw new MyException(20001,"用户已被禁用");
        }

        //登录成功
        //生成token字符串
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());


        return jwtToken;
    }

    //注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //判断非空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)){
            throw new MyException(20001,"注册失败");
        }
        //判断验证码
        //获取redis中的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new MyException(20001,"验证码不正确");
        }
        //判断手机号是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new MyException(20001,"手机号已存在");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://tao-files.oss-cn-hangzhou.aliyuncs.com/2022/08/02/72577c68ba884f8885449a58f79dd28bfile.png");
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }
}
