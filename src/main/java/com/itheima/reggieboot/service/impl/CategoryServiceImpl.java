package com.itheima.reggieboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.common.CustomException;
import com.itheima.reggieboot.entity.Category;
import com.itheima.reggieboot.entity.Dish;
import com.itheima.reggieboot.entity.Setmeal;
import com.itheima.reggieboot.mapper.CategoryMapper;
import com.itheima.reggieboot.service.CategoryService;
import com.itheima.reggieboot.service.DishService;
import com.itheima.reggieboot.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> DishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        DishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1=dishService.count(DishLambdaQueryWrapper);
        if(count1>0){
                throw new CustomException("当下分类关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> SetmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        SetmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2=setmealService.count(SetmealLambdaQueryWrapper);
        if(count2>0){
            throw new CustomException("当下分类关联了套餐，不能删除");
        }
    }
}
