package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import com.example.demo.repository.PersonRepository;
import com.example.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

    // 使用 spring-boot-data-jpa 连接数据库
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "test")
    public String test() {
        return "This is spring boot";
    }

    @PostMapping(value = "showResult")
    public Result testResult() {
        return ResultUtil.success(ExceptionEnum.SUCCESS);
    }

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    // 使用springframework 中Environment 获取当前环境配置
    @Autowired
    Environment env;



    /**
     * 区分@RequestBody @RequestParam @PathVariable 三个参数注解的使用
     *
     * @param person
     * @return
     * @RequestBody ：传json参数使用
     * @RequestParam : url参数或者form表单数据时使用(格式：？name=tom&age=11)
     * @PathVariable : 将URL中的占位符参数绑定到控制器处理方法的入参(格式：/page/123  将路径某一部分转化为参数)
     */
    @PostMapping(value = "getPerson")
    public Person getPerson(@RequestBody Person person) {
        Person p = new Person();
        p.setAge(person.getAge());
        p.setName(person.getName());
        p.setNation("China");
        p.setTel("13321091368");
        return p;
    }

    @PostMapping(value = "getById")
    @ResponseBody
    public Result queryPersonById(@RequestParam Integer id) {

        String[] profiles = env.getActiveProfiles();
        String dbUrl = env.getProperty("spring.datasource.url");
        logger.info("profiles:"+profiles[0]+",database url:"+dbUrl);
        return ResultUtil.success(ExceptionEnum.SUCCESS,personRepository.findOne(id));
    }

    @PostMapping(value = "addPerson")
    public Result addPerson(@RequestBody Person person){
        Person p = personRepository.save(person);
        return ResultUtil.success(ExceptionEnum.SUCCESS,p);
    }


}
