package com.cyl;

import com.cyl.entity.User;
import com.cyl.mapper.UserMapper;
import com.cyl.util.FunTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@SpringBootTest
public class TestSelect {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectAll(){
        System.out.println("----- selectAll method test ------");
        //  SELECT id,name,age,email FROM user
        List<User> userList = userMapper.selectList(null);
        System.out.println("----- 测试自定义函数式接口使用 :: 引用方法输出 ------");
        userList.forEach(new FunTest()::getaaa);
        System.out.println();
        System.out.println("----- 测试函数式接口使用 :: 引用方法输出 ------");
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectById(){
        System.out.println("----- selectById method test ------");
        //  SELECT id,name,age,email FROM user WHERE id=1
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        System.out.println("----- selectBatchIds method test ------");
        //   SELECT id,name,age,email FROM user WHERE id IN ( 4 , 5 )
        List<Long> idList = Arrays.asList(4L, 5L);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap(){
        System.out.println("----- selectByMap method test ------");
        Map<String,Object> map = new HashMap<>();
        map.put("name","cyl");
        map.put("age",23);
        //  SELECT id,name,age,email FROM user WHERE name = 'cyl' AND age = 23
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    /**
     * 自定义方法
     */
    @Test
    public void testSelectMapById(){
        System.out.println("----- selectMapById method test ------");
        //   select id,name,age,email from user where id = 3
        Map<String, Object> map = userMapper.selectMapById(3L);
        System.out.println(map);
    }
}
