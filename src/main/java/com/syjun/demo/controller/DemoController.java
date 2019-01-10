package com.syjun.demo.controller;

import com.syjun.demo.model.common.TestModel;
import com.syjun.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/health")
    public String healthCheck(){
        TestModel tm = new TestModel();
        tm.setCheckDate(new Date());
        testRepository.save(tm);
        return "OK";
    }

    @GetMapping("/health/list")
    public List<TestModel> getList(){
        return testRepository.findAll();
    }
}
