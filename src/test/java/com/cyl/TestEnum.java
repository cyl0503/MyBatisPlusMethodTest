package com.cyl;

import com.cyl.enums.SexEnum;
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
public class TestEnum {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setName("carrot")
                .setAge(29)
                .setSex(SexEnum.FEMALE);
        //1. INSERT INTO t_user ( id, name, age, sex ) VALUES ( 1505790776323710977, 'carrot', 29, 'FEMALE' )
        /**
         * 1.枚举类加注解@EnumValue
         * 2.配置文件中扫描通用枚举
         */
        int result = userMapper.insert(user);
        System.out.println("result="+result);
    }
}
