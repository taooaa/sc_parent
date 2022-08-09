package com.tao.eduservice.client;

import com.tao.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{

    //熔断后执行
    @Override
    public R removeVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("删除视频出错");
    }
}
