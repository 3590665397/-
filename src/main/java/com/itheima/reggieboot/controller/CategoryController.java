package com.itheima.reggieboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggieboot.common.R;
import com.itheima.reggieboot.entity.Category;
import com.itheima.reggieboot.entity.Employee;
import com.itheima.reggieboot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    @CrossOrigin
    public  R<Page> page(int page, int pageSize){
        log.info("page:{},pageSize:{},",page,pageSize);
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByDesc(Category::getSort);
        categoryService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);

    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
            categoryService.updateById(category);
            return R.success("修改成功");
    }
    @DeleteMapping
    public R<String> delete(Long id){
        categoryService.remove(id);
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<Category>> getList(Category category){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        categoryLambdaQueryWrapper.orderByDesc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);
        return R.success(list);
    }


}
