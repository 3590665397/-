package com.itheima.reggieboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@ServletComponentScan
//开启事务
@EnableTransactionManagement
public class ReggiebootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggiebootApplication.class, args);
        log.info("启动成功");
    }

}
