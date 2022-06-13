package com.tencent.wxcloudrun.controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.tencent.wxcloudrun.controller.utils.R;
import com.tencent.wxcloudrun.domain.Myinfo;
import com.tencent.wxcloudrun.domain.Outsign;
import com.tencent.wxcloudrun.service.impl.MyinfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LUO
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/myinfo")
public class MyinfoController {
    @Autowired
    MyinfoServiceImpl myinfoService;
    @PostMapping
    public R save(@RequestParam("age") int age, @RequestParam("phonenumber") String phonenumber,@RequestParam("countryside") String countryside, @RequestParam("sex") String sex, @RequestParam("picturepath") String picturepath, @RequestParam("name") String name,@RequestHeader("openid") String openid)
    {
        Myinfo myinfo= new Myinfo();
        myinfo.setAge(age);
        myinfo.setCountryside(countryside);
        myinfo.setName(name);
        myinfo.setPhonenumber(phonenumber);
        myinfo.setSex(sex);
        myinfo.setPicturepath(picturepath);
        myinfo.setOpenid(openid);
        boolean flag=myinfoService.saveOrUpdate(myinfo);
        return new R(flag, flag? "添加成功":"添加失败");
    }
    @GetMapping
    public R get(@RequestHeader("openid") String openid)
    {
        Myinfo myinfo=myinfoService.getById(openid);
        boolean flag=myinfo!=null ;
        return new R(flag , flag? myinfo: "没有数据");
    }
}

