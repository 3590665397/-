package com.itheima.reggieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggieboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
