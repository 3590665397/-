package com.itheima.reggieboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggieboot.dto.DishDto;
import com.itheima.reggieboot.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
    public boolean updateDishStatusByStatus(int status, List<Long> ids);
}
