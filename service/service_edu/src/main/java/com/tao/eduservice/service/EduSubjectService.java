package com.tao.eduservice.service;

import com.tao.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.eduservice.pojo.subject.OneSubject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-02
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();

}
