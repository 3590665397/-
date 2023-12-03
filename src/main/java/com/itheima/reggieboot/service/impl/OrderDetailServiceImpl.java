package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.reggieboot.entity.OrderDetail;
import com.itheima.reggieboot.mapper.OrderDetailMapper;
import com.itheima.reggieboot.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}