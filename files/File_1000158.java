package com.lou.springboot.controller;

import com.lou.springboot.common.Result;
import com.lou.springboot.common.ResultGenerator;
import com.lou.springboot.dao.UserDao;
import com.lou.springboot.entity.Topic;
import com.lou.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @qq交�?群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    UserDao userDao;

    // 查询一�?�记录
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getOne(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("缺少�?�数");
        }
        User user = userDao.getUserById(id);
        if (user == null) {
            return ResultGenerator.genFailResult("无此数�?�");
        }
        return ResultGenerator.genSuccessResult(user);
    }

    // 查询所有记录
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> queryAll() {
        List<User> users = userDao.findAllUsers();
        return ResultGenerator.genSuccessResult(users);
    }

    // 新增一�?�记录
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert(@RequestBody User user) {
        // �?�数验�?
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genFailResult("缺少�?�数");
        }
        return ResultGenerator.genSuccessResult(userDao.insertUser(user) > 0);
    }

    // 修改一�?�记录
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody User tempUser) {
        //�?�数验�?
        if (tempUser.getId() == null || tempUser.getId() < 1 || StringUtils.isEmpty(tempUser.getName()) || StringUtils.isEmpty(tempUser.getPassword())) {
            return ResultGenerator.genFailResult("缺少�?�数");
        }
        //实体验�?，�?存在则�?继续修改�?作
        User user = userDao.getUserById(tempUser.getId());
        if (user == null) {
            return ResultGenerator.genFailResult("�?�数异常");
        }
        user.setName(tempUser.getName());
        user.setPassword(tempUser.getPassword());
        return ResultGenerator.genSuccessResult(userDao.updUser(user) > 0);
    }

    // 删除一�?�记录
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("缺少�?�数");
        }
        return ResultGenerator.genSuccessResult(userDao.delUser(id) > 0);
    }

}
