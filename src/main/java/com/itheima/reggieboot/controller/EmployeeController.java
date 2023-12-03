package com.itheima.reggieboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggieboot.common.R;
import com.itheima.reggieboot.entity.Employee;
import com.itheima.reggieboot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*
    *
    *
    * */
    @PostMapping ("/login")
    @CrossOrigin
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //获取前端返回的密码
        String password=employee.getPassword();
        //将密码md5加密
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp= employeeService.getOne(employeeLambdaQueryWrapper);
        //如果没有查询到数据返回错误信息
        if(emp==null){
            return R.error("登录失败");
        }
        //密码比对，如果不一致就返回错误信息
        if (!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if (emp.getStatus()==0){
            return R.error("账号已封禁，请联系管理员");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    @PostMapping("/logout")
    @CrossOrigin
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    @CrossOrigin
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

       // 获得当前登录用户的id
        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    @CrossOrigin
    public R<Page> page(int page,int pageSize,String name){
        log.info("page:{},pageSize:{},name:{}",page,pageSize,name);
        Page  pageInfo= new Page(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    @CrossOrigin
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);
//        Long empid = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empid);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    @GetMapping("{id}")
    @CrossOrigin
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息");
        Employee employeeServiceById = employeeService.getById(id);
        return R.success(employeeServiceById);
    }

}
