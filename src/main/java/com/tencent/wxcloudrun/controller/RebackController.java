package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.controller.utils.R;
import com.tencent.wxcloudrun.domain.Outsign;
import com.tencent.wxcloudrun.domain.Reback;
import com.tencent.wxcloudrun.domain.Resign;
import com.tencent.wxcloudrun.service.impl.OutsignServiceImpl;
import com.tencent.wxcloudrun.service.impl.RebackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/reback")
public class RebackController {
    @Autowired
    private RebackServiceImpl rebackService;
    @PostMapping
    public R save(@RequestHeader("openid") String openid,@RequestParam("fromplace") String fromplace, @RequestParam("backtime") String backtime, @RequestParam("flag") String flag, @RequestParam("placethrough") String placethrough)
    {
        Reback reback=new Reback();
        int year=Integer.parseInt( backtime.substring(0,4));
        int month=Integer.parseInt(backtime.substring(5,7));
        int day=Integer.parseInt(backtime.substring(8,10));
        reback.setBacktime(LocalDate.of(year,month, day));
        reback.setFlag(flag);
        reback.setPlacethrough(placethrough);
        reback.setFromplace(fromplace);
        reback.setOpenid(openid);
        System.out.println(reback);
        boolean f=rebackService.save(reback);
        return new R(f, f? "添加成功":"添加失败");
    }
    @GetMapping
    public R get(@RequestHeader("openid") String openid)
    {
        int i=1;
        ArrayList<Reback> rebacks=new ArrayList<>();
        while(true)
        {
            Reback reback=rebackService.getById(i);
            System.out.println(i);
            if(reback==null) break;
            i++;
            boolean pan=false;
            for(int j=0;j<openid.length();j++)
            {
                if(openid.charAt(j)!=reback.getOpenid().charAt(j))
                {
                    pan=true;
                    break;

                }
            }
            if(pan||openid.length()!=reback.getOpenid().length()) continue;
            rebacks.add(reback);

        }
        boolean flag= rebacks.size()!=0 ? true : false;
        return new R(flag, flag? rebacks: "没有成功");
    }

}

