package com.cyl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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

    /**
     * 通过条件构造器QueryWrapper修改
     * 将（年龄大于20并且用户名包含a）或邮箱为null的用户信息修改
     */
    @Test
    public void testUpdate(){
        System.out.println("----- update method test ------");
        //第一个参数
        User user = new User();
        user.setName("spongebob")
                .setEmail("spongebob@123.com");
        //第二个参数
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","a")
                .or()
                .isNull("email");

        // 第一个参数 修改内容 第二个参数修改条件
        //  UPDATE t_user SET name='spongebob', email='spongebob@123.com' WHERE is_deleted=0 AND (age > 20 AND name LIKE %a% OR email IS NULL)
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result="+result);
    }

    /**
     * 通过条件构造器QueryWrapper修改 优先级更改
     * 将 用户名包含a并且（年龄大于20并且或邮箱为null）的用户信息修改
     */
    @Test
    public void testUpdate2(){
        System.out.println("----- update method test ------");
        //第一个参数
        User user = new User();
        user.setName("Patrick")
                .setEmail("Patrick@123.com");
        //第二个参数
        //lamada中的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                .and(i->{
                   i.gt("age",20)
                           .or()
                           .isNull("email");
                });

        // 第一个参数 修改内容 第二个参数修改条件
        // UPDATE t_user SET name='Patrick', email='Patrick@123.com' WHERE is_deleted=0 AND (name LIKE %a% AND (age > 20 OR email IS NULL))
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result="+result);
    }

    /**
     * 通过条件构造器UpdateWrapper修改 优先级更改
     * 将 用户名包含a并且（年龄大于20并且或邮箱为null）的用户信息修改
     */
    @Test
    public void testUpdate3(){
        System.out.println("----- update method test ------");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        // 修改条件
        updateWrapper.like("name","a")
                .and(i->{
                    i.gt("age",20)
                            .or()
                            .isNull("email");
                });
        // 修改内容
        updateWrapper.set("name","spongebob!!")
                .set("email","ahahah@123.com");
        // UPDATE t_user SET name='spongebob!!',email='ahahah@123.com' WHERE is_deleted=0 AND (name LIKE %a% AND (age > 20 OR email IS NULL))
        int result = userMapper.update(null,updateWrapper);
        System.out.println("result="+result);
    }

    /**
     * 通过条件构造器LambdaQueryWrapper
     */
    @Test
    public void testUpdate4(){
        System.out.println("----- update method test ------");
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.like(User::getName,"a")
                .and(i->{
                    i.gt(User::getAge,20)
                            .or()
                            .isNull(User::getEmail);
                });
        lambdaUpdateWrapper.set(User::getName,"spongebob!!")
                .set(User::getEmail,"ahahah@123.com");
        // UPDATE t_user SET name='spongebob!!',email='ahahah@123.com' WHERE is_deleted=0 AND (name LIKE %a% AND (age > 20 OR email IS NULL))
        int result = userMapper.update(null,lambdaUpdateWrapper);
        System.out.println("result="+result);
    }
}
