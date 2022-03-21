package com.cyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyl.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     *
     * @param id
     * @return
     */
    Map<String,Object> selectMapById( Long id);

    /**
     * 通过年龄查询用户信息并分页
     * @param page MyBatis-plus提供的分页对象 必须放在第一个位置
     * @param age
     * @return
     */
    Page<User> selectUserVo(@Param("page")Page<User> page,@Param("age") Integer age);
}

