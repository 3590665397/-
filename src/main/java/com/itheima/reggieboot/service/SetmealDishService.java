package com.itheima.reggieboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggieboot.dto.SetmealDishDto;
import com.itheima.reggieboot.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService extends IService<SetmealDish> {
    List<SetmealDishDto> dishswithimg(Long id);
}
