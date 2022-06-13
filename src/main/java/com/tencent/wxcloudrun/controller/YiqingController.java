
package com.tencent.wxcloudrun.controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tencent.wxcloudrun.controller.utils.R;
import com.tencent.wxcloudrun.domain.Outsign;
import com.tencent.wxcloudrun.domain.Reback;
import com.tencent.wxcloudrun.domain.Resign;
import com.tencent.wxcloudrun.service.impl.RebackServiceImpl;
import com.tencent.wxcloudrun.service.impl.ResignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LUO
 * @since 2022-06-10
 */
@RestController
@RequestMapping("/Yiqing")
public class YiqingController {
    @GetMapping("/middle")
    public static ArrayList<Mes> spiderHighRisk()throws IOException
    {
        String url = "http://m.bj.bendibao.com/news/gelizhengce/fengxianmingdan.php?src=baidu#bj";//爬取的链接
        Document doc = Jsoup.connect(url).get();//网页解析
        Elements selectMiddle = doc.select("div[class='middle info-item']>div[class='info-list']");//根据子标签一直下去，标签匹配
        int sum=selectMiddle.size();
        ArrayList<Mes>  cityArray=new ArrayList<>();
        for(int a=0;a<sum;a++) {
            ArrayList<String> region=new ArrayList<>();
            Element s = selectMiddle.get(a);
            String city="";
            Elements ss = s.select("div[class='flex-between']>p>span");
            String sss=s.select("ul>li>span").text().toString();
            sss=sss.replaceAll("<span>","");
            sss=sss.replaceAll("</span>","");
            String filse[]=sss.split(" ");
            for(int i=0;i<ss.size();i++)
            {
                String temp=ss.get(i).text();
                temp=temp.replace("<span style=\"margin-right: .08rem\">","");
                temp=temp.replace("</span>","");
                temp=temp.replace("<span>","");
                city+=temp;
            }




            for (String i: filse)
            {
                region.add(i);
            }
            cityArray.add(new Mes(city,region));
        }
        return cityArray;
    }
    @GetMapping("/high")
    public static ArrayList<Mes> spiderMiddleRisk()throws IOException
    {
        String url = "http://m.bj.bendibao.com/news/gelizhengce/fengxianmingdan.php?src=baidu#bj";//爬取的链接
        Document doc = Jsoup.connect(url).get();//网页解析
        Elements selectMiddle = doc.select("div[class='height info-item']>div[class='info-list']");//根据子标签一直下去，标签匹配
        int sum=selectMiddle.size();
        ArrayList<Mes>  cityArray=new ArrayList<>();
        for(int a=0;a<sum;a++) {
            ArrayList<String> region=new ArrayList<>();
            Element s = selectMiddle.get(a);
            String city="";
            Elements ss = s.select("div[class='flex-between']>p>span");
            String sss=s.select("ul>li>span").text().toString();
            sss=sss.replaceAll("<span>","");
            sss=sss.replaceAll("</span>","");
            String filse[]=sss.split(" ");
            for(int i=0;i<ss.size();i++)
            {
                String temp=ss.get(i).text();
                temp=temp.replace("<span style=\"margin-right: .08rem\">","");
                temp=temp.replace("</span>","");
                temp=temp.replace("<span>","");
                city+=temp;
            }
            System.out.println(city);
            for (String i: filse)
            {
                region.add(i);
            }
            cityArray.add(new Mes(city,region));
        }
        return cityArray;
    }








}

