package com.cyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

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

}

