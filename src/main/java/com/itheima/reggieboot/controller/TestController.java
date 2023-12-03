package com.itheima.reggieboot.controller;

import com.itheima.reggieboot.common.R;
import com.itheima.reggieboot.entity.Test;
import com.itheima.reggieboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
    @Autowired
    private TestService testService;
    @PostMapping
    public R<Test> insert(@RequestBody Test test){
        testService.save(test);
        return R.success(test);
    }
    @PostMapping("/{id}")
    public R<String> reader(@PathVariable Long id){
        Test test = testService.getById(id);
        String s = readFile(test.getContent());
        System.out.println(s);
        return R.success(s);
    }
}

