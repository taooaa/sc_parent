package com.tao.eduservice.client;

import com.tao.commonutils.R;
import com.tao.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(name = "service-ucenter" ,fallback = UcenterCilentDegradeFeignCilent.class)
public interface UcenterClient {

    //根据用户id查询用户信息
    @PostMapping("/educenter/member/getInfoUc/{id}")
    public UcenterMemberVo getInfo(@PathVariable String id);
}
