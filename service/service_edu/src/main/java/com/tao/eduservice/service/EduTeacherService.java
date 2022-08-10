package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-07-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> listTeacher();
}
