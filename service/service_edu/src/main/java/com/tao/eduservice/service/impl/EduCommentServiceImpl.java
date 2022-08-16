package com.tao.eduservice.service.impl;

import com.tao.commonutils.R;
import com.tao.eduservice.client.UcenterClient;
import com.tao.eduservice.pojo.EduComment;
import com.tao.eduservice.mapper.EduCommentMapper;
import com.tao.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-14
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

}
