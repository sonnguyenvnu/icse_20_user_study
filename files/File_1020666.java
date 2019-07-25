package com.github.vole.portal.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vole.common.utils.R;
import com.github.vole.portal.controller.BaseController;
import com.github.vole.portal.model.entity.SysRole;
import com.github.vole.portal.model.entity.SysRoleMenu;
import com.github.vole.portal.model.entity.SysUser;
import com.github.vole.portal.model.entity.SysUserRole;
import com.github.vole.portal.model.vo.TreeMenuAllowAccess;
import com.github.vole.portal.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
/**
 * 角色控制器
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	/**
	 * 角色�?务
	 */
	@Autowired
	private ISysRoleService sysRoleService;
	/**
	 * 角色用户�?务
	 */
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	/**
	 * 用户�?务
	 */
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * �?��?��?务
	 */
	@Autowired
	private ISysMenuService sysMenuService;
	/**
	 * 角色�?��?�?务
	 */
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	
	/**
	 * 分页查询角色
	 */
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
    	
		Page<SysRole> page = getPage(pageNumber,pageSize);
		page.setDesc("create_time");
		// 查询分页
		QueryWrapper<SysRole> ew = new QueryWrapper<SysRole>();
		if(StringUtils.isNotBlank(search)){
			ew.like("role_name",search);
			model.addAttribute("search",search);
		}
		Page<SysRole> pageData = (Page<SysRole>) sysRoleService.page(page, ew);
		model.addAttribute("pageData", pageData);
		return "ftl/admin/role/list";
    } 
    
    /**
     * 新增角色
     */
    @RequestMapping("/add")
    public  String add(Model model){
		return "ftl/admin/role/add";
    } 
    
    /**
     * 执行新增角色
     */
    @RequestMapping("/doAdd")
	@ResponseBody
    public R<Boolean> doAdd(SysRole role){
    	role.setCreateTime(new Date());
    	return R.rest(sysRoleService.save(role));

    }  
    
    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @ResponseBody
    public  R<Boolean> delete(String id){
		return R.rest(sysRoleService.removeById(Integer.valueOf(id)));
    }

    /**
     * 批�?删除角色
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public R<Boolean> deleteBatch(@RequestParam("id[]") List<String> ids){
		return R.rest(sysRoleService.removeByIds(ids));
    }
    
    /**
     * 编辑角色
     */
    @RequestMapping("/edit/{id}")
    public  String edit(@PathVariable String id,Model model){
    	SysRole sysRole = sysRoleService.getById(id);
    	model.addAttribute(sysRole);
    	return "ftl/admin/role/edit";
    } 
    
    /**
     * 执行编辑角色
     */
    @RequestMapping("/doEdit")
	@ResponseBody
    public  R<Boolean> doEdit(SysRole sysRole,Model model){
    	return R.rest(sysRoleService.updateById(sysRole));
    }
    
    /**
     * �?��?
     */
    @RequestMapping("/auth/{id}")
    public  String auth(@PathVariable String id,Model model){
    	
    	SysRole sysRole = sysRoleService.getById(id);
    	
    	if(sysRole == null){
    		throw new RuntimeException("该角色�?存在");
    	}
    	
    	List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
    	List<String> menuIds = Lists.transform(sysRoleMenus,input -> input.getMenuId().toString());
    	
    	List<TreeMenuAllowAccess> treeMenuAllowAccesses = sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, "0");

    	model.addAttribute("sysRole", sysRole);
    	model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);
    	
    	return "ftl/admin/role/auth";
    } 
    
    /**
     * �?��?
     */
    @RequestMapping("/doAuth")
	@ResponseBody
    public  R<Boolean> doAuth(String roleId,@RequestParam(value="mid[]",required=false) String[] mid){
    	sysRoleMenuService.addAuth(roleId,mid);
		R<Boolean> r = R.rest(true);
		r.setMsg("OK,授�?��?功,1分钟�?�生效  ~");
    	return r;
    } 
	
	/**
	 * 获�?�角色下的所有用户
	 */
	@RequestMapping("/getUsers")  
	public String getUsers(String roleId,Model model){
		
		List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
		
		List<String> userIds = Lists.transform(sysUserRoles,input -> input.getUserId().toString());
		
		List<SysUser> users  = new ArrayList<SysUser>();
		
		if(userIds.size() > 0){
			QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
			ew.in("user_id", userIds);
			users= sysUserService.list(ew);
		}
		
		model.addAttribute("users",users);
		return "ftl/admin/role/users";
	}
	
	/**
	 * 获�?�指定角色的用户数�?
	 */
	@RequestMapping("/getCount")  
	@ResponseBody
	public String getCount(String roleId){
		
		int count =  sysUserRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id",roleId));
		return String.valueOf(count);
	}

	/**
	 * 验�?用户�??是�?�已存在
	 */
	@RequestMapping("/checkRole")
	@ResponseBody
	public R<Boolean> checkRole(String roleCode){
		List<SysRole> list = sysRoleService.list(new QueryWrapper<SysRole>().eq("role_code", roleCode));
		R r = new R<Boolean>(true);
		if(list.size() > 0){
			r.setData(false);
			r.setCode(R.FAIL);
			r.setMsg("�?��?编�?已存在");
		}
		return r;
	}
	
}
