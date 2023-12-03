package com.itheima.reggieboot.dto;

import com.itheima.reggieboot.entity.Setmeal;
import com.itheima.reggieboot.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
