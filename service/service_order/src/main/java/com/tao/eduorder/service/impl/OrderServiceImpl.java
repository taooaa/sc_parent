package com.tao.eduorder.service.impl;

import com.tao.commonutils.vo.CourseWebVoOrder;
import com.tao.commonutils.vo.UcenterMemberVo;
import com.tao.eduorder.client.EduClient;
import com.tao.eduorder.client.UcenterClient;
import com.tao.eduorder.pojo.Order;
import com.tao.eduorder.mapper.OrderMapper;
import com.tao.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    //生成订单
    @Override
    public String createOrders(String courseId, String memberId) {
        //获取用户信息
        UcenterMemberVo userInfo = ucenterClient.getInfo(memberId);
        //获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
