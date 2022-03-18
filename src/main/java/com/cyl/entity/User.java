package com.cyl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@Data
@Accessors(chain = true)
//@TableName("t_user")
public class User {
    //将属性所对应的字段指定为主键 (主键不叫id时加)
    /**
     * value @TableId中的value属性用于指定主键字段
     * type 主键生成策略
     *      IdType.NONE 默认 雪花算法
     *      IdType.AUTO 自动递增 且数据库主键字段设为自增
     *      IdType.INPUT
     *      IdType.ASSIGN_ID
     *      IdType.ASSIGN_UUID
     */
    @TableId(value = "id",type = IdType.NONE)
    //@TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

