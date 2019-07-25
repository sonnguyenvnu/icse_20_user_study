package info.xiaomo.website.controller;

import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.Md5Util;
import info.xiaomo.core.untils.RandomUtil;
import info.xiaomo.website.model.AdminModel;
import info.xiaomo.website.service.AdminUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * │＼＿＿╭╭╭╭╭＿＿�?│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　�?　　　　　　�?│
 * │≡　　　　�?　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　�?　　｜
 * ｜╭�?�?�?╮把今天最好的表现当作明天最新的起点．．～
 * �?��?� 最高�?�表�?� �?��?��?� 明日最新�?�始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 12:47
 * Description: �?��?�用户控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/adminUser")
@Api(value = "�?��?�用户相关api", description = "�?��?�用户相关api")
public class AdminUserController extends BaseController {

    private final AdminUserService service;

    @Autowired
    public AdminUserController(AdminUserService service) {
        this.service = service;
    }

    /**
     * �?��?�账户登录
     *
     * @return Result
     */
    @RequestMapping(value = "login/{userName}/{password}", method = RequestMethod.POST)
    @ApiOperation(value = "获�?�用户信�?�", notes = "根�?�用户�?�?�和密�?登录�?��?�", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户�??", required = true, dataType = "Result", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "用户�??", required = true, dataType = "Result", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        if (!Md5Util.encode(password, adminModel.getSalt()).equals(adminModel.getPassword())) {
            return new Result(CodeConst.AUTH_FAILED.getResultCode(), CodeConst.AUTH_FAILED.getMessage());
        }
        return new Result<>(adminModel);
    }


    /**
     * 添加用户
     *
     * @return Result
     */
    @ApiOperation(value = "添加�?��?�用户", notes = "传一个管�?�员用户模型过�?�然�?��?存到数�?�库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result add(@RequestBody AdminModel model) {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel != null) {
            return new Result(CodeConst.ADMIN_USER_REPEAT.getResultCode(), CodeConst.ADMIN_USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        model.setSalt(salt);
        model.setPassword(Md5Util.encode(model.getPassword(), salt));
        AdminModel saveModel = service.addAdminUser(model);
        return new Result<>(saveModel);
    }

    /**
     * 根�?�id查找
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "查找用户", notes = "根�?�传�?�的id查找用户并返回", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "�?��?�用户唯一id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result findUserById(@PathVariable("id") Long id) {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 查找所有(�?带分页)
     *
     * @return result
     */
    @Override
    public Result<List> findAll() {
        return null;
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页�?数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 根�?�id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根�?��??字查找
     *
     * @param userName userName
     * @return Result
     */
    @Override
    @ApiOperation(value = "查找用户", notes = "根�?�传�?�的用户�??查找用户并返回", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findByName/{userName}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户�??", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result findByName(@PathVariable("userName") String userName) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 根�?��??字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根�?�id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批�?删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }

    /**
     * 修改密�?
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户密�?", notes = "传�?�模型验�?并修改密�?", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result changePassword(@RequestBody AdminModel model) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        String salt = RandomUtil.createSalt();
        adminModel.setSalt(salt);
        adminModel.setPassword(Md5Util.encode(model.getPassword(), salt));
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }


    /**
     * 返回所有
     *
     * @return �?分页
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "返回所有用户信�?�", notes = "�?分页", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result getAll() {
        List<AdminModel> pages = service.getAdminUsers();
        if (pages == null || pages.size() <= 0) {
            return new Result<>(pages);
        }
        return new Result<>(pages);
    }

    /**
     * 根�?�id删除数�?�
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "删除用户", notes = "根�?�传入的id删除对应的用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        service.deleteAdminUserById(id);
        return new Result<>(adminModel);
    }

    /**
     * 更新
     *
     * @param userName userName
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update/{userName}", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信�?�", notes = "根�?�传入的模型更新用户信�?�", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户�??", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result update(@PathVariable("userName") String userName) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        adminModel.setUserName(userName);
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }

    /**
     * �?�?�
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "forbid/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "�?�?�", notes = "根�?�传入的id对修改对应�?�?�状�?", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "�?��?�用户唯一id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "No Name Provided"),
    })
    public Result forbid(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        model = service.forbidAdminUserById(id);
        return new Result<>(model);
    }
}

