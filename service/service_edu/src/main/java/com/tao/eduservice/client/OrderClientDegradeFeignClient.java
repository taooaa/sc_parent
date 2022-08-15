package com.tao.eduservice.client;

import org.springframework.stereotype.Component;

@Component
public class OrderClientDegradeFeignClient implements OrderClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
