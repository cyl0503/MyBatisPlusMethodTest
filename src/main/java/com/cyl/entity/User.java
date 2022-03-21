package com.cyl.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cyl.enums.SexEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@Data
@Accessors(chain = true)
//@TableName("t_user") 可在配置文件统一配置
public class User {
    //将属性所对应的字段指定为主键 (主键不叫id时加)
    /**
     * value @TableId中的value属性用于指定主键字段
     * type 主键生成策略
     *      IdType.NONE
     *      IdType.AUTO 使用数据库的自动递增策略 且确保数据库主键字段设为自增 否则无效
     *      IdType.INPUT
     *      IdType.ASSIGN_ID 默认 雪花算法 与数据库是否设置自增无关
     *      IdType.ASSIGN_UUID
     *
     *      @TableId(value = "id",type = IdType.ASSIGN_ID) 可在配置文件中全局配置
     */
    private Long id;
    /** @TableField("user_name") 指定属性所对应的字段名 设置其他字段的属性 */
    private String name;
    private Integer age;
    private String email;
    private SexEnum sex;
    /** @TableField("is_deleted") */
    @TableLogic
    private Boolean isDeleted;
}

