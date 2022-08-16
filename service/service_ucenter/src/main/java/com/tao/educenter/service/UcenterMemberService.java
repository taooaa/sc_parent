package com.tao.educenter.service;

import com.tao.educenter.pojo.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.educenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

}
