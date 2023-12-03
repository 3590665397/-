package com.itheima.reggieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggieboot.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
