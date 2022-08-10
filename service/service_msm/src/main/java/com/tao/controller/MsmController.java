package com.tao.controller;

import com.tao.commonutils.R;
import com.tao.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
@Slf4j
public class MsmController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        String code = RandomUtil.getFourBitRandom();
        log.info("code={}",code);
//        Map<String ,Object> param = new HashMap<>();
//        param.put("code",code);
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

        return R.ok();
    }
}
