package com.tencent.wxcloudrun.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import com.tencent.wxcloudrun.controller.utils.R;
import com.tencent.wxcloudrun.domain.Outsign;
import com.tencent.wxcloudrun.domain.Reback;
import com.tencent.wxcloudrun.domain.Resign;
import com.tencent.wxcloudrun.service.impl.RebackServiceImpl;
import com.tencent.wxcloudrun.service.impl.ResignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LUO
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/resign")
public class ResignController {
    @Autowired
    private ResignServiceImpl resignService;
    @PostMapping
    public R save(@RequestHeader("openid") String openid,@RequestParam("picturepath") String picturepath, @RequestParam("date") String date, @RequestParam("radioitems") String radioitems, @RequestParam("region") String region)
    {
        Resign resign=new Resign();
        int year=Integer.parseInt( date.substring(0,4));
        int month=Integer.parseInt(date.substring(5,7));
        int day=Integer.parseInt(date.substring(8,10));
        System.out.println(region);
        resign.setDate(LocalDate.of(year,month, day));
        resign.setRegion(region);
        resign.setPicturepath(picturepath);
        resign.setRadioitems(radioitems);
        resign.setOpenid(openid);
        System.out.println(openid+"sdasda");
        boolean f=resignService.save(resign);
        return new R(f, f? "添加成功":"添加失败");
    }
    @GetMapping
    public R get(@RequestHeader("openid") String openid)
    {
        int i=1;
        ArrayList<Resign> resigns=new ArrayList<>();
        while(true)
        {
            Resign resign=resignService.getById(i);
            System.out.println(i);
            if(resign==null) break;
            i++;
            boolean pan=false;
            for(int j=0;j<openid.length();j++)
            {
                if(openid.charAt(j)!=resign.getOpenid().charAt(j))
                {
                    pan=true;
                    break;

                }
            }
            if(pan||openid.length()!=resign.getOpenid().length()) continue;
            resigns.add(resign);

        }
        boolean flag=  resigns.size()!=0 ? true : false;
        return new R(flag, flag? resigns: "没有成功");
    }
}

