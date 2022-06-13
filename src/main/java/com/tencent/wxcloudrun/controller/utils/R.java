package com.tencent.wxcloudrun.controller.utils;


import lombok.Data;

@Data
//控制层返回结果的模型类， 用于后端与前端进行数据格式统一
public class R {
    private Boolean flag;
    private Object data;
    private String msg;
    public R(){

    }
    public R(Boolean flag) {
        this.flag=flag;
    }
    public R(Boolean flag, Object data)
    {
        this.flag=flag;
        this.data=data;
    }
    public R(Boolean flag , String msg)
    {
        this.flag=flag;
        this.msg=msg;
    }
}
