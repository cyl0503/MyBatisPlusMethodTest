package com.cyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author cyl
 * @create 2022/3/21
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
