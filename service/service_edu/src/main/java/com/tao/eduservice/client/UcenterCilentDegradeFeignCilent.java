package com.tao.eduservice.client;

import com.tao.commonutils.vo.UcenterMemberVo;
import org.springframework.stereotype.Component;


@Component
public class UcenterCilentDegradeFeignCilent implements UcenterClient{


    @Override
    public UcenterMemberVo getInfo(String id) {
        return null;
    }
}
