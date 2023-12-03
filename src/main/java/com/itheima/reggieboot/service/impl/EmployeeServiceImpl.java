package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.entity.Employee;
import com.itheima.reggieboot.mapper.EmployeeMapper;
import com.itheima.reggieboot.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
