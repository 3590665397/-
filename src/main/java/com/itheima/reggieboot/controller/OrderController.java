package com.itheima.reggieboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggieboot.common.R;
import com.itheima.reggieboot.dto.OrderDto;
import com.itheima.reggieboot.entity.OrderDetail;
import com.itheima.reggieboot.entity.Orders;
import com.itheima.reggieboot.service.OrderDetailService;
import com.itheima.reggieboot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;
    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    @CrossOrigin
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    @GetMapping("/page")
    public R<Page<Orders>> getOrderDetailPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String beginTime,
            @RequestParam(required = false) String endTime
    ) {
        Page<Orders> pageObj = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(number != null, Orders::getNumber, number)
                .ge(beginTime != null, Orders::getOrderTime, beginTime)
                .le(endTime != null, Orders::getCheckoutTime, endTime);
        queryWrapper.orderByDesc(Orders::getCheckoutTime);

        Page<Orders> result = orderService.page(pageObj, queryWrapper);
        return R.success(result);
    }


    @PutMapping
    public R<String> editOrderDetail(@RequestBody Map<String, Object> params) {
        // Extract status and ID from the params
        int status = Integer.parseInt(params.get("status").toString());
        long id = Long.parseLong(params.get("id").toString());
        orderService.updateOrderStatus(id,status);
        // Your logic here to update the order status in the database using the ID and status received

        // Return a success message or appropriate response
        return R.success("Order status updated successfully!");
    }
    @GetMapping("userPage")

    public R<Page<OrderDto>> orderPagingApi(@RequestParam int page, @RequestParam int pageSize) {
        try {
            // 构造分页对象
            Page<Orders> pageRequest = new Page<>(page, pageSize);

            // 创建查询条件构造器
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

            // 调用 MyBatis Plus 的分页查询方法
            Page<Orders> orderPage = orderService.page(pageRequest, queryWrapper);

            // 将 Order 对象转换为 OrderDTO，包含 OrderDetail 信息
            List<OrderDto> orderDTOList = orderPage.getRecords().stream()
                    .map(order -> {
                        OrderDto orderDTO = new OrderDto();
                        BeanUtils.copyProperties(order, orderDTO);

                        // 查询 OrderDetail 信息
                        LambdaQueryWrapper<OrderDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
                        detailQueryWrapper.eq(OrderDetail::getOrderId, order.getId());
                        List<OrderDetail> orderDetails = orderDetailService.list(detailQueryWrapper);

                        // 将 OrderDetail 列表添加到 OrderDTO 中
                        orderDTO.setOrderDetails(orderDetails);

                        return orderDTO;
                    })
                    .collect(Collectors.toList());


            // 将 OrderDTO 列表设置回分页对象
            Page<OrderDto> orderDTOPage = new Page<>();
            BeanUtils.copyProperties(orderPage, orderDTOPage, "records");
            orderDTOPage.setRecords(orderDTOList);
            return R.success(orderDTOPage);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("分页查询订单出错：" + e.getMessage());
        }
    }

    @PostMapping("/again")
    public R<String> orderAgain(@RequestBody Map<String, Long> requestData) {
        return R.success("ok");
    }

}