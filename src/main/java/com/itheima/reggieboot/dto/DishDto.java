package com.itheima.reggieboot.dto;

import com.itheima.reggieboot.entity.Dish;
import com.itheima.reggieboot.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
