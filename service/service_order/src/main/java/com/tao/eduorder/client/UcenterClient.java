package com.tao.eduorder.client;

import com.tao.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "service-ucenter")
public interface UcenterClient {
    //根据用户id查询用户信息
    @PostMapping ("/educenter/member/getInfoUc/{id}")
    public UcenterMemberVo getInfo(@PathVariable("id") String id);
}
