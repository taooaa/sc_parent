package com.tao.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.tao.commonutils.R;
import com.tao.service.VodService;
import com.tao.serviceBase.execeptionHandler.MyException;
import com.tao.utils.ConstantVodUtils;
import com.tao.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliVideo")
    public R uploadAliVideo(MultipartFile file){
        String videoId = vodService.uploadVideoAli(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAliVideo/{id}")
    public R removeVideo(@PathVariable String id){
        try{
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法来实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }

    }
}
