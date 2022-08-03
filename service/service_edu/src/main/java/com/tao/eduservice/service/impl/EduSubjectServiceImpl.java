package com.tao.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.eduservice.listener.SubjectExcelListener;
import com.tao.eduservice.pojo.EduSubject;
import com.tao.eduservice.mapper.EduSubjectMapper;
import com.tao.eduservice.pojo.excel.SubjectData;
import com.tao.eduservice.pojo.subject.OneSubject;
import com.tao.eduservice.pojo.subject.TwoSubject;
import com.tao.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectService eduSubjectService;

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        }catch (Exception e){

        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1.查询出所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = eduSubjectService.list(wrapperOne);

        //2.查询出所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id",0);
        List<EduSubject> twoSubjectList = eduSubjectService.list(wrapperTwo);

        //创建list集合，用于封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        //查询出来所有的一级分类list集合遍历
        //封装到要求的list集合对象里面
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);

            //放到oneSubject对象
            //再放到finalSubjectList中
            OneSubject oneSubject = new OneSubject();

            BeanUtils.copyProperties(eduSubject,oneSubject);

            finalSubjectList.add(oneSubject);

            //在一级分类循环中查询所有二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject tSubject = twoSubjectList.get(m);

                //判断二级分类的parentid和以及分类的id
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //若一样，封装数据
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }

            //把一级下面的所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);

        }

        //4.封装二级分类



        return finalSubjectList;
    }
}
