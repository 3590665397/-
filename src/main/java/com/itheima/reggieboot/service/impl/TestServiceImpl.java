package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.entity.Test;
import com.itheima.reggieboot.mapper.TestMapper;
import com.itheima.reggieboot.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
}
