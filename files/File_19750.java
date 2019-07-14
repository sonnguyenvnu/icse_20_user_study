package com.example.demo.controller;

import com.example.demo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{id:\\d+}")
    public User get(@PathVariable Long id) {
        log.info("获�?�用户id为 " + id + "的信�?�");
        return new User(id, "mrbird", "123456");
    }

    @GetMapping
    public List<User> get() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "mrbird", "123456"));
        list.add(new User(2L, "scott", "123456"));
        log.info("获�?�用户信�?� " + list);
        return list;
    }

    @PostMapping
    public void add(@RequestBody User user) {
        log.info("新增用户�?功 " + user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        log.info("更新用户�?功 " + user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        log.info("删除用户�?功 " + id);
    }

}
