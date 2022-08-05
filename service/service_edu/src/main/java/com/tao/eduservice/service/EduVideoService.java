package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-03
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String courseId);
}

