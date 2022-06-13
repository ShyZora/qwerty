package com.tencent.wxcloudrun.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LUO
 * @since 2022-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Myinfo implements Serializable {

private static final long serialVersionUID=1L;

    private Integer age;

    private String phonenumber;

    private String countryside;

    private String sex;

    private String picturepath;

    private String name;


    private Integer id;
    @TableId(value = "openid", type = IdType.AUTO)
    private String openid;


}
