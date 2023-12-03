package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.dto.DishDto;
import com.itheima.reggieboot.entity.DishFlavor;
import com.itheima.reggieboot.mapper.DishFlavorMapper;
import com.itheima.reggieboot.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
