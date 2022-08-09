package com.tao.eduservice.client;

import com.tao.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    //定义调用的方法的路径
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAliVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    //删除多个视频
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList);
}
