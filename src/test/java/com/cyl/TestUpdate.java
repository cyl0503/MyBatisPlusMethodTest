package com.cyl;

import com.cyl.entity.User;
import com.cyl.mapper.UserMapper;
import com.cyl.util.FunTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@SpringBootTest
public class TestUpdate {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testUpdateById(){
        System.out.println("----- updateById method test ------");
        User user = new User();
        user.setId(3L)
                .setName("cyl")
                .setEmail("aaa@123.com");
        // UPDATE user SET name='cyl', email='aaa@123.com' WHERE id=3
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

}
