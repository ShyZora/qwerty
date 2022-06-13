package com.tencent.wxcloudrun.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
public class Reback implements Serializable {

private static final long serialVersionUID=1L;

    private LocalDate backtime;

    private String fromplace;

    private String placethrough;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String flag;

    private String openid;


}
