package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleOrg;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.web.system.util.OrgConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 角色处�?�类
 * 
 * @author 张代浩
 * 
 */
//@Scope("prototype")
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleController.class);
	private UserService userService;
	private SystemService systemService;
	@Autowired
	private CacheServiceI cacheService;


	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 角色列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	public ModelAndView role() {
		return new ModelAndView("system/role/roleList");
	}

	/**
	 * easyuiAJAX请求数�?�
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "roleGrid")
	public void roleGrid(TSRole role, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,role);

		cq.eq("roleType", OrgConstants.SYSTEM_ROLE_TYPE);//默认�?�查询系统角色

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		;
	}

	@RequestMapping(params = "delUserRole")
	@ResponseBody
	public AjaxJson delUserRole(@RequestParam(required=true)String userid,@RequestParam(required=true)String roleid) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			List<TSRoleUser> roleUserList = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", userid);
			if(roleUserList.size() == 1){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("�?�?�删除用户的角色关系，请使用修订用户角色关系");
			}else{
				String sql = "delete from t_s_role_user where userid = ? and roleid = ?";
				this.systemService.executeSql(sql, userid,roleid);
				ajaxJson.setMsg("�?功删除用户对应的角色关系");
			}
		} catch (Exception e) {
			LogUtil.log("删除用户对应的角色关系失败", e.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}

	
	/**
	 * 清空登录用户�?��?缓存
	 */
	@RequestMapping(params = "refresh")
	@ResponseBody
	public AjaxJson refresh(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			cacheService.clean("sysAuthCache");
			logger.info("-----清空登录用户�?��?缓存�?功--------[sysAuthCache]-----");
			ajaxJson.setMsg("�?置用户�?��?�?功");
		} catch (Exception e) {
			ajaxJson.setMsg("�?置用户�?��?失败");
			e.printStackTrace();
		}
		return ajaxJson;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delRole")
	@ResponseBody
	public AjaxJson delRole(TSRole role, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		int count = userService.getUsersOfThisRole(role.getId());
		if (count == 0) {
			// 删除角色之�?先删除角色�?��?关系
			delRoleFunction(role);

            systemService.executeSql("delete from t_s_role_org where role_id=?", role.getId()); // 删除 角色-机构 关系信�?�

            role = systemService.getEntity(TSRole.class, role.getId());
			userService.delete(role);
			message = "角色: " + role.getRoleName() + "被删除�?功";
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} else {
			message = "角色: �?被用户使用，请先删除关�?�关系";
		}
		j.setMsg(message);
		logger.info(message);
		return j;
	}

	/**
	 * 检查角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "checkRole")
	@ResponseBody
	public ValidForm checkRole(TSRole role, HttpServletRequest request,
			HttpServletResponse response) {
		ValidForm v = new ValidForm();
		String roleCode = oConvertUtils
				.getString(request.getParameter("param"));
		String code = oConvertUtils.getString(request.getParameter("code"));
		List<TSRole> roles = systemService.findByProperty(TSRole.class,
				"roleCode", roleCode);
		if (roles.size() > 0 && !code.equals(roleCode)) {
			v.setInfo("角色编�?已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 删除角色�?��?
	 * 
	 * @param role
	 */
	protected void delRoleFunction(TSRole role) {
		List<TSRoleFunction> roleFunctions = systemService.findByProperty(
				TSRoleFunction.class, "TSRole.id", role.getId());
		if (roleFunctions.size() > 0) {
			for (TSRoleFunction tsRoleFunction : roleFunctions) {
				systemService.delete(tsRoleFunction);
			}
		}
		List<TSRoleUser> roleUsers = systemService.findByProperty(
				TSRoleUser.class, "TSRole.id", role.getId());
		for (TSRoleUser tsRoleUser : roleUsers) {
			systemService.delete(tsRoleUser);
		}
	}

	/**
	 * 角色录入
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "saveRole")
	@ResponseBody
	public AjaxJson saveRole(TSRole role, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(role.getId())) {
			message = "角色: " + role.getRoleName() + "被更新�?功";

			role.setRoleType(OrgConstants.SYSTEM_ROLE_TYPE);

			userService.saveOrUpdate(role);
			systemService.addLog(message, Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
		} else {
			message = "角色: " + role.getRoleName() + "被添加�?功";

			role.setRoleType(OrgConstants.SYSTEM_ROLE_TYPE);//默认系统角色

			userService.save(role);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		logger.info(message);
		return j;
	}

	/**
	 * 角色列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fun")
	public ModelAndView fun(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		return new ModelAndView("system/role/roleSet");
	}

	/**

	 * 角色所有用户信�?�列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "userList")
	public ModelAndView userList(HttpServletRequest request) {

		request.setAttribute("roleId", request.getParameter("roleId"));

		return new ModelAndView("system/role/roleUserList");
	}
	
	/**
	 * 用户列表查询 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "roleUserDatagrid")
	public void roleUserDatagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);

		//查询�?�件组装器
        String roleId = request.getParameter("roleId");
        List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSRole.id", roleId);
        /*
        // zhanggm：这个查询逻辑也�?�以使用这�? �?查询的方�?进行查询
        CriteriaQuery subCq = new CriteriaQuery(TSRoleUser.class);
        subCq.setProjection(Property.forName("TSUser.id"));
        subCq.eq("TSRole.id", roleId);
        subCq.add();
        cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
        cq.add();
        */

		Criterion cc = null;
		if (roleUser.size() > 0) {
			for(int i = 0; i < roleUser.size(); i++){
				if(i == 0){
					cc = Restrictions.eq("id", roleUser.get(i).getTSUser().getId());
				}else{
					cc = cq.getor(cc, Restrictions.eq("id", roleUser.get(i).getTSUser().getId()));
				}
			}
		}else {
			cc =Restrictions.eq("id", "-1");
		}
		cq.add(cc);
        cq.eq("deleteFlag", Globals.Delete_Normal);
		cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 获�?�用户列表
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 */
	@RequestMapping(params = "getUserList")
	@ResponseBody
	public List<ComboTree> getUserList(TSUser user, HttpServletRequest request,
			ComboTree comboTree) {
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		String roleId = request.getParameter("roleId");
		List<TSUser> loginActionlist = new ArrayList<TSUser>();
		if (user != null) {

			List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSRole.id", roleId);
			if (roleUser.size() > 0) {
				for (TSRoleUser ru : roleUser) {
					loginActionlist.add(ru.getTSUser());
				}
			}
		}
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "userName", "TSUser");
		comboTrees = systemService.ComboTree(loginActionlist,comboTreeModel,loginActionlist, false);
		return comboTrees;
	}

	/**
	 * 角色树列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "roleTree")
	public ModelAndView roleTree(HttpServletRequest request) {
		request.setAttribute("orgId", request.getParameter("orgId"));
		return new ModelAndView("system/role/roleTree");
	}

	/**
	 * 获�?� 组织机构的角色树
	 * 
	 * @param request
	 *            request
	 * @return 组织机构的角色树
	 */
	@RequestMapping(params = "getRoleTree")
	@ResponseBody
	public List<ComboTree> getRoleTree(HttpServletRequest request) {
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "roleName", "");
		String orgId = request.getParameter("orgId");
		List<TSRole[]> orgRoleArrList = systemService
				.findHql(
						"from TSRole r, TSRoleOrg ro, TSDepart o WHERE r.id=ro.tsRole.id AND ro.tsDepart.id=o.id AND o.id=?",
						orgId);
		List<TSRole> orgRoleList = new ArrayList<TSRole>();
		for (Object[] roleArr : orgRoleArrList) {
			orgRoleList.add((TSRole) roleArr[0]);
		}

		List<Object> allRoleList = this.systemService.getList(TSRole.class);
		List<ComboTree> comboTrees = systemService.ComboTree(allRoleList,
				comboTreeModel, orgRoleList, false);

		return comboTrees;
	}

	/**
	 * 更新 组织机构的角色列表
	 * 
	 * @param request
	 *            request
	 * @return �?作结果
	 */
	@RequestMapping(params = "updateOrgRole")
	@ResponseBody
	public AjaxJson updateOrgRole(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String orgId = request.getParameter("orgId");
			String roleIds = request.getParameter("roleIds");
			List<String> roleIdList = extractIdListByComma(roleIds);
			systemService.executeSql("delete from t_s_role_org where org_id=?",
					orgId);
			if (!roleIdList.isEmpty()) {
				List<TSRoleOrg> roleOrgList = new ArrayList<TSRoleOrg>();
				TSDepart depart = new TSDepart();
				depart.setId(orgId);
				for (String roleId : roleIdList) {
					TSRole role = new TSRole();
					role.setId(roleId);

					TSRoleOrg roleOrg = new TSRoleOrg();
					roleOrg.setTsRole(role);
					roleOrg.setTsDepart(depart);
					roleOrgList.add(roleOrg);
				}
				systemService.batchSave(roleOrgList);
			}
			j.setMsg("角色更新�?功");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("角色更新失败");
		}
		return j;
	}


	/**
	 * 设置�?��?
	 * 
	 * @param role
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setAuthority")
	@ResponseBody
	public List<ComboTree> setAuthority(TSRole role,
			HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (comboTree.getId() != null) {
			cq.eq("TSFunction.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.notEq("functionLevel", Short.parseShort("-1"));
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(
				cq, false);
		Collections.sort(functionList, new NumberComparator());
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		String roleId = request.getParameter("roleId");
		List<TSFunction> loginActionlist = new ArrayList<TSFunction>();// 已有�?��?�?��?�
		role = this.systemService.get(TSRole.class, roleId);
		if (role != null) {
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					loginActionlist.add(function);
				}
			}
			roleFunctionList.clear();
		}
		ComboTreeModel comboTreeModel = new ComboTreeModel("id","functionName", "TSFunctions");

		comboTrees = comboTree(functionList, comboTreeModel,loginActionlist, true);
		MutiLangUtil.setMutiComboTree(comboTrees);


		functionList.clear();
		functionList = null;
		loginActionlist.clear();
		loginActionlist = null;

		//System.out.println(JSON.toJSONString(comboTrees,true));		
		return comboTrees;
	}

	private List<ComboTree> comboTree(List<TSFunction> all, ComboTreeModel comboTreeModel, List<TSFunction> in, boolean recursive) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TSFunction obj : all) {
			trees.add(comboTree(obj, comboTreeModel, in, recursive));
		}
		all.clear();
		return trees;

	}
	
	/**
     * 构建ComboTree
     * @param obj
     * @param comboTreeModel ComboTreeModel comboTreeModel = new ComboTreeModel("id","functionName", "TSFunctions");
     * @param in
     * @param recursive 是�?�递归�?节点
     * @return
     */
	@SuppressWarnings("unchecked")
	private ComboTree comboTree(TSFunction obj, ComboTreeModel comboTreeModel, List<TSFunction> in, boolean recursive) {
		ComboTree tree = new ComboTree();
		String id = oConvertUtils.getString(obj.getId());
		tree.setId(id);
		tree.setText(oConvertUtils.getString(obj.getFunctionName()));
		
		
		
		if (in == null) {
		} else {
			if (in.size() > 0) {
				for (TSFunction inobj : in) {
					String inId = oConvertUtils.getString(inobj.getId());
                    if (inId.equals(id)) {
						tree.setChecked(true);
					}
				}
			}
		}

		List<TSFunction> curChildList = obj.getTSFunctions();

		Collections.sort(curChildList, new Comparator<Object>(){
			@Override
	        public int compare(Object o1, Object o2) {
	        	TSFunction tsFunction1=(TSFunction)o1;  
	        	TSFunction tsFunction2=(TSFunction)o2;  
	        	int flag=tsFunction1.getFunctionOrder().compareTo(tsFunction2.getFunctionOrder());
	        	  if(flag==0){
	        	   return tsFunction1.getFunctionName().compareTo(tsFunction2.getFunctionName());
	        	  }else{
	        	   return flag;
	        	  }  
	        }             
	    });

		if (curChildList != null && curChildList.size() > 0) {
			tree.setState("closed");
			//tree.setChecked(false);

            if (recursive) { // 递归查询�?节点
                List<ComboTree> children = new ArrayList<ComboTree>();
                for (TSFunction childObj : curChildList) {
                    ComboTree t = comboTree(childObj, comboTreeModel, in, recursive);
                    children.add(t);
                }
                tree.setChildren(children);
            }
        }

		if(obj.getFunctionType() == 1){
			if(curChildList != null && curChildList.size() > 0){
				tree.setIconCls("icon-user-set-o");
			}else{
				tree.setIconCls("icon-user-set");
			}
		}

		if(curChildList!=null){
			curChildList.clear();
		}

		return tree;
	}

	/**
	 * 更新�?��?
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateAuthority")
	@ResponseBody
	public AjaxJson updateAuthority(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String roleId = request.getParameter("roleId");
			String rolefunction = request.getParameter("rolefunctions");
			TSRole role = this.systemService.get(TSRole.class, roleId);
			List<TSRoleFunction> roleFunctionList = systemService
					.findByProperty(TSRoleFunction.class, "TSRole.id",
							role.getId());
			Map<String, TSRoleFunction> map = new HashMap<String, TSRoleFunction>();
			for (TSRoleFunction functionOfRole : roleFunctionList) {
				map.put(functionOfRole.getTSFunction().getId(), functionOfRole);
			}

			Set<String> set = new HashSet<String>();
			if(StringUtil.isNotEmpty(rolefunction)){
				String[] roleFunctions = rolefunction.split(",");
				for (String s : roleFunctions) {
					set.add(s);
				}
			}

			updateCompare(set, role, map);
			j.setMsg("�?��?更新�?功");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("�?��?更新失败");
		}
		return j;
	}

	/**
	 * �?��?比较
	 * 
	 * @param set
	 *            最新的�?��?列表
	 * @param role
	 *            当�?角色
	 * @param map
	 *            旧的�?��?列表
	 */
	private void updateCompare(Set<String> set, TSRole role,
			Map<String, TSRoleFunction> map) {
		List<TSRoleFunction> entitys = new ArrayList<TSRoleFunction>();
		List<TSRoleFunction> deleteEntitys = new ArrayList<TSRoleFunction>();
		for (String s : set) {
			if (map.containsKey(s)) {
				map.remove(s);
			} else {
				TSRoleFunction rf = new TSRoleFunction();
				TSFunction f = this.systemService.get(TSFunction.class, s);
				rf.setTSFunction(f);
				rf.setTSRole(role);
				entitys.add(rf);
			}
		}
		Collection<TSRoleFunction> collection = map.values();
		Iterator<TSRoleFunction> it = collection.iterator();
		for (; it.hasNext();) {
			deleteEntitys.add(it.next());
		}
		systemService.batchSave(entitys);
		systemService.deleteAllEntitie(deleteEntitys);

	}

	/**
	 * 角色页�?�跳转
	 * 
	 * @param role
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSRole role, HttpServletRequest req) {
		if (role.getId() != null) {
			role = systemService.getEntity(TSRole.class, role.getId());
			req.setAttribute("role", role);
		}
		return new ModelAndView("system/role/role");
	}

	/**
	 * �?��?�?作列表
	 * 
	 * @param treegrid
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "setOperate")
	@ResponseBody
	public List<TreeGrid> setOperate(HttpServletRequest request,
			TreeGrid treegrid) {
		String roleId = request.getParameter("roleId");
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (treegrid.getId() != null) {
			cq.eq("TSFunction.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(
				cq, false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		Collections.sort(functionList, new SetListSort());
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setRoleid(roleId);
		treeGrids = systemService.treegrid(functionList, treeGridModel);
		return treeGrids;

	}

	/**
	 * �?作录入
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveOperate")
	@ResponseBody
	public AjaxJson saveOperate(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String fop = request.getParameter("fp");
		String roleId = request.getParameter("roleId");
		// 录入�?作�?清空上一次的�?作数�?�
		clearp(roleId);
		String[] fun_op = fop.split(",");
		String aa = "";
		String bb = "";
		// �?�有一个被选中
		if (fun_op.length == 1) {
			bb = fun_op[0].split("_")[1];
			aa = fun_op[0].split("_")[0];
			savep(roleId, bb, aa);
		} else {
			// 至少2个被选中
			for (int i = 0; i < fun_op.length; i++) {
				String cc = fun_op[i].split("_")[0]; // �?作id
				if (i > 0 && bb.equals(fun_op[i].split("_")[1])) {
					aa += "," + cc;
					if (i == (fun_op.length - 1)) {
						savep(roleId, bb, aa);
					}
				} else if (i > 0) {
					savep(roleId, bb, aa);
					aa = fun_op[i].split("_")[0]; // �?作ID
					if (i == (fun_op.length - 1)) {
						bb = fun_op[i].split("_")[1]; // �?��?id
						savep(roleId, bb, aa);
					}

				} else {
					aa = fun_op[i].split("_")[0]; // �?作ID
				}
				bb = fun_op[i].split("_")[1]; // �?��?id

			}
		}

		return j;
	}

	/**
	 * 更新�?作
	 * 
	 * @param roleId
	 * @param functionid
	 * @param ids
	 */
	public void savep(String roleId, String functionid, String ids) {
		// String hql = "from TSRoleFunction t where" + " t.TSRole.id=" +
		// oConvertUtils.getInt(roleId,0)
		// + " " + "and t.TSFunction.id=" + oConvertUtils.getInt(functionid,0);
		CriteriaQuery cq = new CriteriaQuery(TSRoleFunction.class);
		cq.eq("TSRole.id", roleId);
		cq.eq("TSFunction.id", functionid);
		cq.add();
		List<TSRoleFunction> rFunctions = systemService.getListByCriteriaQuery(
				cq, false);
		if (rFunctions.size() > 0) {
			TSRoleFunction roleFunction = rFunctions.get(0);
			roleFunction.setOperation(ids);
			systemService.saveOrUpdate(roleFunction);
		}
	}

	/**
	 * 清空�?作
	 * 
	 * @param roleId
	 */
	public void clearp(String roleId) {
		List<TSRoleFunction> rFunctions = systemService.findByProperty(
				TSRoleFunction.class, "TSRole.id", roleId);
		if (rFunctions.size() > 0) {
			for (TSRoleFunction tRoleFunction : rFunctions) {
				tRoleFunction.setOperation(null);
				systemService.saveOrUpdate(tRoleFunction);
			}
		}
	}

	/**
	 * 按钮�?��?展示
	 * 
	 * @param request
	 * @param functionId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(params = "operationListForFunction")
	public ModelAndView operationListForFunction(HttpServletRequest request,
			String functionId, String roleId) {
		CriteriaQuery cq = new CriteriaQuery(TSOperation.class);
		cq.eq("TSFunction.id", functionId);
		cq.eq("status", Short.valueOf("0"));
		cq.add();
		List<TSOperation> operationList = this.systemService
				.getListByCriteriaQuery(cq, false);
		Set<String> operationCodes = systemService.getOperationCodesByRoleIdAndFunctionId(roleId, functionId);
		request.setAttribute("operationList", operationList);
		request.setAttribute("operationcodes", operationCodes);
		request.setAttribute("functionId", functionId);
		return new ModelAndView("system/role/operationListForFunction");
	}

	/**
	 * 更新按钮�?��?
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateOperation")
	@ResponseBody
	public AjaxJson updateOperation(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String roleId = request.getParameter("roleId");
		String functionId = request.getParameter("functionId");

		String operationcodes = null;
		try {
			operationcodes = URLDecoder.decode(
					request.getParameter("operationcodes"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
		cq1.eq("TSRole.id", roleId);
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TSRoleFunction> rFunctions = systemService.getListByCriteriaQuery(
				cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TSRoleFunction tsRoleFunction = rFunctions.get(0);
			tsRoleFunction.setOperation(operationcodes);
			systemService.saveOrUpdate(tsRoleFunction);
		}
		j.setMsg("按钮�?��?更新�?功");
		return j;
	}
	
//chengchuan数�?�规则过滤代�?
	
	/**
	 * 按钮�?��?展示
	 * 
	 * @param request
	 * @param functionId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(params = "dataRuleListForFunction")
	public ModelAndView dataRuleListForFunction(HttpServletRequest request,
			String functionId, String roleId) {
		CriteriaQuery cq = new CriteriaQuery(TSDataRule.class);
		cq.eq("TSFunction.id", functionId);
		cq.add();
		List<TSDataRule> dataRuleList = this.systemService
				.getListByCriteriaQuery(cq, false);
		Set<String> dataRulecodes = systemService.getDataRuleIdsByRoleIdAndFunctionId(roleId, functionId);
		request.setAttribute("dataRuleList", dataRuleList);
		request.setAttribute("dataRulecodes", dataRulecodes);
		request.setAttribute("functionId", functionId);
		return new ModelAndView("system/role/dataRuleListForFunction");
	}
	
	
	/**
	 * 更新按钮�?��?
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateDataRule")
	@ResponseBody
	public AjaxJson updateDataRule(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String roleId = request.getParameter("roleId");
		String functionId = request.getParameter("functionId");

		String dataRulecodes = null;
		try {
			dataRulecodes = URLDecoder.decode(
					request.getParameter("dataRulecodes"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
		cq1.eq("TSRole.id", roleId);
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TSRoleFunction> rFunctions = systemService.getListByCriteriaQuery(
				cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TSRoleFunction tsRoleFunction = rFunctions.get(0);
			tsRoleFunction.setDataRule(dataRulecodes);
			systemService.saveOrUpdate(tsRoleFunction);
		}
		j.setMsg("数�?��?��?更新�?功");
		return j;
	}
	
	
	
	/**
     * 添加 用户到角色 的页�?�  跳转
     * @param req request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "goAddUserToRole")
    public ModelAndView goAddUserToOrg(HttpServletRequest req) {
        return new ModelAndView("system/role/noCurRoleUserList");
    }
    
    /**
     * 获�?� 除当�? 角色之外的用户信�?�列表
     * @param request request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "addUserToRoleList")
    public void addUserToOrgList(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String roleId = request.getParameter("roleId");

        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);

        // 获�?� 当�?组织机构的用户信�?�
        CriteriaQuery subCq = new CriteriaQuery(TSRoleUser.class);
        subCq.setProjection(Property.forName("TSUser.id"));
        subCq.eq("TSRole.id", roleId);
        subCq.add();
        
        cq.add(Property.forName("id").notIn(subCq.getDetachedCriteria()));

        cq.eq("deleteFlag", Globals.Delete_Normal);//删除状�?，�?删除
        cq.eq("userType",Globals.USER_TYPE_SYSTEM);//系统用户

        cq.add();

        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }
    /**
     * 添加 用户到角色
     * @param req request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "doAddUserToRole")
    @ResponseBody
    public AjaxJson doAddUserToOrg(HttpServletRequest req) {
    	String message = null;
        AjaxJson j = new AjaxJson();
        TSRole role = systemService.getEntity(TSRole.class, req.getParameter("roleId"));
        saveRoleUserList(req, role);
        message =  MutiLangUtil.paramAddSuccess("common.user");
//      systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        j.setMsg(message);

        return j;
    }
    /**
     * �?存 角色-用户 关系信�?�
     * @param request request
     * @param depart depart
     */
    private void saveRoleUserList(HttpServletRequest request, TSRole role) {
        String userIds = oConvertUtils.getString(request.getParameter("userIds"));

        List<TSRoleUser> roleUserList = new ArrayList<TSRoleUser>();
        List<String> userIdList = extractIdListByComma(userIds);
        for (String userId : userIdList) {
            TSUser user = new TSUser();
            user.setId(userId);

            TSRoleUser roleUser = new TSRoleUser();
            roleUser.setTSUser(user);
            roleUser.setTSRole(role);

            roleUserList.add(roleUser);
        }
        if (!roleUserList.isEmpty()) {
            systemService.batchSave(roleUserList);
        }
    }

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","roleController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSRole tsRole,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		tsRole.setRoleName(null);
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsRole, request.getParameterMap());
		List<TSRole> tsRoles = systemService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"角色表");
		modelMap.put(NormalExcelConstants.CLASS,TSRole.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("角色表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
				"导出信�?�"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tsRoles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模�?�
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSRole tsRole,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户表");
		modelMap.put(NormalExcelConstants.CLASS,TSRole.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
				"导出信�?�"));
		modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获�?�上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TSRole> tsRoles = ExcelImportUtil.importExcel(file.getInputStream(),TSRole.class,params);
				for (TSRole tsRole : tsRoles) {
					String roleCode = tsRole.getRoleCode();
					List<TSRole> roles = systemService.findByProperty(TSRole.class,"roleCode",roleCode);
					if(roles.size()!=0){
						TSRole role = roles.get(0);
						MyBeanUtils.copyBeanNotNull2Bean(tsRole,role);
						systemService.saveOrUpdate(role);
					}else {
						systemService.save(tsRole);
					}
				}
				j.setMsg("文件导入�?功�?");
			} catch (Exception e) {
				j.setMsg("文件导入失败�?");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}
