package com.tao.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.commonutils.R;
import com.tao.educms.pojo.CrmBanner;
import com.tao.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author gaohongtao
 * @since 2022-08-09
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    //分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

    //修改banner
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    //删除banner
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }

    //获取banner
    @GetMapping("get/{id}")
    public R get(@PathVariable String id){
        CrmBanner crmBanner = bannerService.getById(id);
        return R.ok().data("item",crmBanner);
    }

}

