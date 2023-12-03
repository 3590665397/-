package com.itheima.reggieboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggieboot.entity.Orders;


public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
    public boolean updateOrderStatus(Long orderId, Integer newStatus);
}
