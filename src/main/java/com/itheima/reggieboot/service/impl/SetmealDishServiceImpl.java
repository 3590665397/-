package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.dto.SetmealDishDto;
import com.itheima.reggieboot.entity.Dish;
import com.itheima.reggieboot.entity.SetmealDish;
import com.itheima.reggieboot.mapper.SetmealDishMapper;
import com.itheima.reggieboot.service.DishService;
import com.itheima.reggieboot.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {

    @Autowired
    private DishService dishService;

    @Override
    public List<SetmealDishDto> dishswithimg(Long id) {
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId,id);
        List<SetmealDish> setmealDishes = this.list(lambdaQueryWrapper);
        List<SetmealDishDto> collect = setmealDishes.stream().map((item) -> {
            SetmealDishDto setmealDishDto = new SetmealDishDto();
            BeanUtils.copyProperties(item, setmealDishDto);
            Long dishId = item.getDishId();
            Dish dish = dishService.getById(dishId);
            if (dish != null) {
                String img = dish.getImage();
                setmealDishDto.setImage(img);
            }
            return setmealDishDto;
        }).collect(Collectors.toList());
        return collect;
    }
}
