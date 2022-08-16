package com.tao.staservice.service;

import com.tao.staservice.pojo.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-16
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    //图表显示，返回两部分数据，日期，数量
    Map<String, Object> getShowData(String type, String begin, String end);
}
