package com.cyl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
        // SELECT id,name,age,email FROM user
        // 设置逻辑删除之后：
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0
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
        // select id,name,age,email from user where id = 3
        Map<String, Object> map = userMapper.selectMapById(3L);
        System.out.println(map);
    }

    /**
     * 通过条件构造器QueryWrapper查询
     */
    @Test
    public void testSelectList(){
        System.out.println("----- selectList method test ------");
        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询用户名包含c 年龄在20-30 邮箱信息不为null的用户信息
        queryWrapper.like("name","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 通过条件构造器QueryWrapper排序
     */
    @Test
    public void testSelectList2(){
        System.out.println("----- selectList method test ------");
        //  SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询用户名信息按年龄降序 id升序
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 通过条件构造器QueryWrapper查询指定字段
     */
    @Test
    public void testSelectMaps(){
        System.out.println("----- selectMaps method test ------");
        // SELECT name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询用户名 年龄 邮箱
        queryWrapper.select("name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 通过条件构造器QueryWrapper实现子查询
     */
    @Test
    public void testSelectList3(){
        System.out.println("----- selectList method test ------");
        // SELECT name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询用id<=5的用户信息
        queryWrapper.inSql("id","select id from t_user where id <= 5");
        //  SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (id IN (select id from t_user where id <= 5))
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 通过条件构造器QueryWrapper组装
     */
    @Test
    public void testSelectList4(){
        System.out.println("----- selectList method test ------");
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            //1 不为空字符串 2 不为null 3.不为空白符
            queryWrapper.like("name",username);
        }
        if (ageBegin!=null){
            queryWrapper.ge("age",ageBegin);
        }
        if (ageBegin!=null){
            queryWrapper.le("age",ageEnd);
        }
        //  SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= 20 AND age <= 30)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 通过条件构造器QueryWrapper组装
     */
    @Test
    public void testSelectList5(){
        System.out.println("----- selectList method test ------");
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"name",username)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= 20 AND age <= 30)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 通过条件构造器LambdaQueryWrapper
     */
    @Test
    public void testSelectList6(){
        System.out.println("----- selectList method test ------");
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= 20 AND age <= 30)
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

}
