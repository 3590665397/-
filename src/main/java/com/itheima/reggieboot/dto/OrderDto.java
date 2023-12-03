package com.itheima.reggieboot.dto;

import com.itheima.reggieboot.entity.OrderDetail;
import com.itheima.reggieboot.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Orders {
  private List<OrderDetail> orderDetails;



}
