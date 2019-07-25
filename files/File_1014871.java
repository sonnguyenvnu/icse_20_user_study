package com.hope.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysRole;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserRoleService;
import com.hope.service.SysUserService;
import com.hope.shiro.realm.HopeShiroRealm;
import com.hope.utils.ResultHopeUtil;
import com.hope.utils.UsingAesHopeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调�?熊猫(https://aodeng.cc)
 * @微信公众�?�:低调�?熊猫
 * @create:2018-12-10 20:13
 **/
@Api(value = "用户", description = "用户管�?�api", position = 30, produces = "http")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;
    private final SysUserRoleService sysUserRoleService;
    private final HopeShiroRealm hopeShiroRealm;

    public UserController(SysUserService sysUserService,SysRoleService sysRoleService,SysUserRoleService sysUserRoleService,HopeShiroRealm hopeShiroRealm){
        this.sysUserService=sysUserService;
        this.sysRoleService=sysRoleService;
        this.sysUserRoleService=sysUserRoleService;
        this.hopeShiroRealm=hopeShiroRealm;
    }

    /** 
    * @Description: 用户列表
    * @Param: [vo]
    * @return: [vo]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "用户列表", notes = "用户列表，传入�?�数�?�需�?pageNum和pageSize",produces="application/json, application/xml", consumes="application/json, application/xml",response = SysUser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "数�?��?�数", required = true, dataType = "String", paramType = "query")
    })
    @RequiresPermissions("user:list")
    @PostMapping("/list")
    public PageResultVo list(UserConditionVo vo) {
        PageInfo<SysUser> pageInfo = sysUserService.findPageBreakByCondition(vo);
        return ResultHopeUtil.tablePage(pageInfo);
    }

    /** 
    * @Description: �?存添加用户
    * @Param: [sysUserFrom, password2]
    * @return: [sysUserFrom, password2]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "�?存添加用户", notes = "�?存添加用户",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password2", value = "第二次密�?", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "�?作�?功"),
            @ApiResponse(code = 500, message = "�?作失败，返回错误原因"),
    })
    @RequiresPermissions("user:add")
    @PostMapping("/add")
    public ResponseVo add(SysUser sysUserFrom, String password2) {
        SysUser user = sysUserService.getByUserName(sysUserFrom.getUsername());
        if (ObjectUtil.isNotNull(user)) {
            return ResultHopeUtil.error("该用户�??[" + user.getUsername() + "]已存在�?请更改用户�??");
        }
        if (!sysUserFrom.getPassword().equals(password2)) {
            return ResultHopeUtil.error("两次密�?�?相�?��?");
        }
        try {
            sysUserFrom.setPassword(UsingAesHopeUtil.encrypt(sysUserFrom.getPassword(), sysUserFrom.getUsername()));
            sysUserFrom.setCreatetime(DateUtil.date());
            sysUserFrom.setUpdatetime(DateUtil.date());
            sysUserFrom.setUserId(RandomUtil.randomUUID().substring(0, 7).toString());
            if (ObjectUtil.isNull(sysUserFrom.getStatus())) {
                sysUserFrom.setStatus(SysUserStatusEnum.NORMAL.getCode());
            }
            if (!sysUserService.insert(sysUserFrom)) {
                return ResultHopeUtil.error("用户添加失败�?");
            }
            return ResultHopeUtil.success("用户添加�?功�?");
        } catch (Exception e) {
            log.info("[用户添加失败]-[{}]", e.getMessage());
            return ResultHopeUtil.error("用户添加失败�?");
        }
    }

    /***
     * 打开用户分�?角色
     */
    @ApiOperation(value = "用户分�?角色", notes = "用户分�?角色")
    @PostMapping("/rolesWithSelected")
    public ResponseVo<List<SysRole>> rolesWithSelected(Integer userId) {
        return ResultHopeUtil.success(null, sysRoleService.RoleListWithSelected(userId));
    }


    /** 
    * @Description: �?存用户分�?角色
    * @Param: [userId, roleIds]
    * @return: [userId, roleIds]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "�?存用户分�?角色", notes = "�?存用户分�?角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "roleIds", value = "角色id字符串，用，分割", required = true, dataType = "Integer", paramType = "query")
    })
    @RequiresPermissions("user:saveUserRoles")
    @PostMapping("/saveUserRoles")
    public ResponseVo<List<SysRole>> saveUserRoles(Integer userId, String roleIds) {
        log.info("[用户id-[{}]，角色id-[{}]", userId, roleIds);
        //添加
        sysUserRoleService.addUserRole(userId, roleIds);
        //更新当�?登录的用户的�?��?缓存
        List<String> userIds = new ArrayList<>();
        userIds.add(Convert.convert(String.class, userId));
        hopeShiroRealm.clearAuthorizationByUserId(userIds);
        return ResultHopeUtil.success("�?作�?功");
    }

    /***
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "�?作�?功"),
            @ApiResponse(code = 500, message = "�?作失败，返回错误原因"),
    })
    @RequiresPermissions("user:delete")
    @PostMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable("id") Integer id) {
        if (sysUserService.deleteById(id)) {
            return ResultHopeUtil.success("用户删除�?功�?");
        } else {
            return ResultHopeUtil.error("用户删除失败�?");
        }
    }

    /***
     * 打开编辑用户
     */
    @ApiOperation(value = "打开编辑用户", notes = "打开编辑用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "用户主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap map) {
        map.addAttribute("user", sysUserService.selectById(id));
        return ResultHopeUtil.view("admin/user/edit");
    }

    /** 
    * @Description: �?存编辑用户
    * @Param: [sysUserFrom]
    * @return: [sysUserFrom]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "�?存编辑用户", notes = "�?存编辑用户",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiResponses({
            @ApiResponse(code = 200, message = "�?作�?功"),
            @ApiResponse(code = 500, message = "�?作失败，返回错误原因"),
    })
    @RequiresPermissions("user:edit")
    @PostMapping("/edit")
    public ResponseVo edit(SysUser sysUserFrom) {
        int a = sysUserService.updateByUserId(sysUserFrom);
        if (a > 0) {
            return ResultHopeUtil.success("用户修改�?功�?");
        } else {
            return ResultHopeUtil.error("用户修改失败�?");
        }
    }
}
