package com.cyl;

import com.cyl.entity.User;
import com.cyl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@SpringBootTest
public class TestInsert {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert(){
        System.out.println("----- insert method test ------");
        User user = new User();
        user.setName("cyl")
                .setAge(23)
                .setEmail("aaa@123.com");
        // INSERT INTO user ( id, name, age, email ) VALUES ( 1504726979303559169, 'cyl', 23, 'aaa@123.com' )
        int result = userMapper.insert(user);
        System.out.println("result="+result);
        System.out.println("id="+user.getId());
    }
}
