package com.tencent.wxcloudrun.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import com.tencent.wxcloudrun.controller.utils.R;
import  com.tencent.wxcloudrun.domain.Outsign;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  com.tencent.wxcloudrun.service.impl.OutsignServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LUO
 * @since 2022-06-03
 */
import java.util.*;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LUO
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/outsign")
public class OutsignController {
    @Autowired
    private OutsignServiceImpl outsignService;
    @PostMapping
    public R save(@RequestHeader("openid") String openid,@RequestParam("region") String region, @RequestParam("date") String date)
    {
        Outsign outsign=new Outsign();
        int year=Integer.parseInt( date.substring(0,4));
        int month=Integer.parseInt(date.substring(5,7));
        int day=Integer.parseInt(date.substring(8,10));
        outsign.setOuttime(LocalDate.of(year,month, day));
        outsign.setRegion(region);
        outsign.setOpenid(openid);
        System.out.println(openid);
        boolean flag=outsignService.save(outsign);
        return new R(flag, flag? "添加成功":"添加失败");
    }
    @GetMapping
    public R get(@RequestHeader("openid") String openid)
    {
        int i=1;
        ArrayList<Outsign> outsigns=new ArrayList<>();
        while(true)
        {
            Outsign outsign=outsignService.getById(i);

            if(outsign==null) break;
            i++;
            boolean pan=false;
            for(int j=0;j<openid.length();j++)
            {
               if(openid.charAt(j)!=outsign.getOpenid().charAt(j))
               {
                   pan=true;
                   break;

               }
            }
            if(pan||openid.length()!=outsign.getOpenid().length()) continue;
            outsigns.add(outsign);

        }
        System.out.println(outsigns.size());
        boolean flag= outsigns.size()!=0 ? true : false;
        System.out.println(flag);
        return new R(flag, flag? outsigns: "没有成功");
    }
}

