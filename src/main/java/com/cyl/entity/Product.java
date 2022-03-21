package com.cyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @Author cyl
 * @create 2022/3/21
 */
@Data
@TableName("t_product11")
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标识乐观锁版本号字段
    private Integer version;
}
