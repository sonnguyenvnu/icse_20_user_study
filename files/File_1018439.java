package com.xiaolyuh.controller;

import com.github.pagehelper.Page;
import com.xiaolyuh.domain.model.Person;
import com.xiaolyuh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    // 1 Spring Data JPA已自动为你注册bean，所以�?�自动注入
    @Autowired
    PersonService personService;

    @RequestMapping("/save")
    public Person save(@RequestBody Person person) {

        personService.insert(person);

        return person;

    }

    @RequestMapping("/all")
    public List<Person> sort() {

        List<Person> people = personService.findAll();

        return people;

    }

    @RequestMapping("/page")
    public Page<Person> page(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {

        Page<Person> pagePeople = personService.findByPage(pageNo, pageSize);

        return pagePeople;
    }

}
