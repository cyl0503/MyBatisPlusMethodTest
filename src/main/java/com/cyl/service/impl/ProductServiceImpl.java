package com.cyl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyl.entity.Product;
import com.cyl.entity.User;
import com.cyl.mapper.ProductMapper;
import com.cyl.mapper.UserMapper;
import com.cyl.service.ProductService;
import com.cyl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author cyl
 * @create 2022/3/21
 */
@DS("slave_1") //指定所操作的数据源
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
