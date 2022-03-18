package com.cyl;

import com.cyl.mapper.UserMapper;
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
public class TestDelete {

    @Autowired
    UserMapper userMapper;

    /**
     * 通过 id 删除
     */
    @Test
    public void testDeleteById(){
        System.out.println("----- deleteById method test ------");
        // DELETE FROM user WHERE id=1504726979303559169
        int result = userMapper.deleteById(1504726979303559169L);
        System.out.println("result="+result);
    }

    /**
     * 通过 map 设置条件删除
     * DELETE FROM user WHERE name = 'cyl' AND age = 22
     */
    @Test
    public void testDeleteByMap(){
        System.out.println("----- deleteByMap method test ------");
        Map<String,Object> map = new HashMap<>();
        map.put("name","cyl");
        map.put("age",22);
        int result = userMapper.deleteByMap(map);
        System.out.println("result="+result);
    }

    /**
     * 通过 in( ) 批量删除
     */
    @Test
    public void testDeleteBatchIds(){
        System.out.println("----- deleteBatchIds method test ------");
        List<Long> idList = Arrays.asList(1L, 2L);
        // DELETE FROM user WHERE id IN ( 1 , 2 )
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("result="+result);
    }
}
