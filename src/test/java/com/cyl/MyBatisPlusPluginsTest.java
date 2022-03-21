package com.cyl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyl.entity.User;
import com.cyl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cyl
 * @create 2022/3/21
 */
@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage(){
        Page<User> page = new Page<>(1,3);
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT 3
        userMapper.selectPage(page,null);
        System.out.println(page);
        System.out.println(page.getRecords());
        System.out.println(page.getCurrent());
    }

    @Test
    public void testPageVo(){
        Page<User> page = new Page<>(1,3);
        // select id,name,age,email from t_user where age > 20 LIMIT 3
        Page<User> page1 = userMapper.selectUserVo(page, 20);
        System.out.println(page1);
    }
}
