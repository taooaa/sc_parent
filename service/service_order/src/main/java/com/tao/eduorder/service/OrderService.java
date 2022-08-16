package com.tao.eduorder.service;

import com.tao.eduorder.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-14
 */
public interface OrderService extends IService<Order> {

    //生成订单
    String createOrders(String courseId, String memberIdByJwtToken);
}
