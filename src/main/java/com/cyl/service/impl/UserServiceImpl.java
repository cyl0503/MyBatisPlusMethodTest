package com.cyl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyl.entity.User;
import com.cyl.mapper.UserMapper;
import com.cyl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@DS("master") //指定所操作的数据源
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
