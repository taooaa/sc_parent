package com.tao.staservice.schedule;

import com.tao.staservice.service.StatisticsDailyService;
import com.tao.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //每天凌晨一点，把前一天的数据添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
