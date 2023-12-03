package com.itheima.reggieboot.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.itheima.reggieboot.entity.ShoppingCart;

public interface ShoppingCartService extends IService<ShoppingCart> {
    public  void remove(Long id);
}
