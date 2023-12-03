package com.itheima.reggieboot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggieboot.common.CustomException;
import com.itheima.reggieboot.entity.ShoppingCart;
import com.itheima.reggieboot.mapper.ShoppingCartMapper;
import com.itheima.reggieboot.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<ShoppingCart> LambdaQueryWrapper = new LambdaQueryWrapper<>();
        ShoppingCart cartItem = this.getOne(new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getSetmealId, id)
                .or()  // 使用 or() 方法添加 OR 条件
                .eq(ShoppingCart::getDishId, id));
        if (cartItem != null) {
            // 获取对应购物车记录中的 num 值
            Integer num = cartItem.getNumber();

            // 判断 num 是否大于 0
            if (num > 0) {
                cartItem.setNumber(num - 1);
                this.updateById(cartItem);

            } else {
                throw new CustomException("num已经为0，不能再减");
            }
        }
    }
//    public void remove(Long id) {
//        LambdaQueryWrapper<Dish> DishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        DishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
//        int count1=dishService.count(DishLambdaQueryWrapper);
//        if(count1>0){
//            throw new CustomException("当下分类关联了菜品，不能删除");
//        }
//        LambdaQueryWrapper<Setmeal> SetmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        SetmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
//        int count2=setmealService.count(SetmealLambdaQueryWrapper);
//        if(count2>0){
//            throw new CustomException("当下分类关联了套餐，不能删除");
//        }
//    }
}
