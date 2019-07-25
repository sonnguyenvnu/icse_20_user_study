package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.YouBianCodeUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.dao.DepartAuthGroupDao;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSDepartExcelView;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
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
import com.alibaba.fastjson.JSONArray;


/**
 * 部门信�?�处�?�类
 * 
 * @author 张代浩
 * 
 */
//@Scope("prototype")
@Controller
@RequestMapping("/departController")
public class DepartController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DepartController.class);
	private UserService userService;
	private SystemService systemService;
	@Autowired
	private DepartAuthGroupDao departAuthGroupDao;

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
	 * 部门列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "depart")
	public ModelAndView depart() {
		return new ModelAndView("system/depart/departList");
	}

	/**
	 * easyuiAJAX请求数�?�
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

		@RequestMapping(params = "delUserOrg")
		@ResponseBody
		public AjaxJson delUserOrg(@RequestParam(required=true)String userid,@RequestParam(required=true)String departid) {
			AjaxJson ajaxJson = new AjaxJson();
			try {
				List<TSUserOrg> userOrgList = this.systemService.findByProperty(TSUserOrg.class, "tsUser.id", userid);
				if(userOrgList.size() == 1){
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg("当�?用户�?�包�?�有当�?组织机构关系，�?�?�删除，请切�?�用户的组织机构关系");
				}else{

					String sql = "delete from t_s_user_org where user_id = ? and org_id = ?";
					this.systemService.executeSql(sql,userid,departid);

					ajaxJson.setMsg("�?功删除用户对应的组织机构关系");
				}
			} catch (Exception e) {
				LogUtil.log("删除用户对应的组织机构关系失败", e.getMessage());
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(e.getMessage());
			}
			return ajaxJson;
		}

	/**
	 * 删除部门：
	 * <ul>
     *     组织机构下存在�?机构时
     *     <li>�?�?许删除 组织机构</li>
	 * </ul>
	 * <ul>
     *     组织机构下存在用户时
     *     <li>�?�?许删除 组织机构</li>
	 * </ul>
	 * <ul>
     *     组织机构下 �?存在�?机构 且 �?存在用户时
     *     <li>删除 组织机构-角色 信�?�</li>
     *     <li>删除 组织机构 信�?�</li>
	 * </ul>
	 * @return 删除的结果信�?�
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSDepart depart, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		depart = systemService.getEntity(TSDepart.class, depart.getId());
        message = MutiLangUtil.paramDelSuccess("common.department");
        if (depart.getTSDeparts().size() == 0) {

            Long userCount = systemService.getCountForJdbcParam("select count(1) from t_s_user_org where org_id= ?",depart.getId());

            if(userCount == 0) { // 组织机构下没有用户时，该组织机构�?�?许删除。
                systemService.executeSql("delete from t_s_role_org where org_id=?", depart.getId());
                systemService.delete(depart);

                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }else{

            	message = MutiLangUtil.getLang("common.department.hasuser");

            }
        } else {

            message = MutiLangUtil.paramDelFail("common.department,!");

        }

        j.setMsg(message);
		return j;
	}


	public void upEntity(TSDepart depart) {
		List<TSUser> users = systemService.findByProperty(TSUser.class, "TSDepart.id", depart.getId());
		if (users.size() > 0) {
			for (TSUser tsUser : users) {
				//tsUser.setTSDepart(null);
				//systemService.saveOrUpdate(tsUser);
				systemService.delete(tsUser);
			}
		}
	}

	/**
	 * 添加部门
	 * 
	 * @param depart
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSDepart depart, HttpServletRequest request) {
		String message = null;
		// 设置上级部门
		String pid = request.getParameter("TSPDepart.id");
		if (pid.equals("")) {
			depart.setTSPDepart(null);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(depart.getId())) {
            message = MutiLangUtil.paramUpdSuccess("common.department");
			userService.saveOrUpdate(depart);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
            message = MutiLangUtil.paramAddSuccess("common.department");
			userService.save(depart);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}

        j.setMsg(message);
		return j;
	}
	@RequestMapping(params = "add")
	public ModelAndView add(TSDepart depart, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
//        这个if代�?段没有用�?�，注释之
//		if (StringUtil.isNotEmpty(depart.getId())) {
//			TSDepart tspDepart = new TSDepart();
//			TSDepart tsDepart = new TSDepart();
//			depart = systemService.getEntity(TSDepart.class, depart.getId());
//			tspDepart.setId(depart.getId());
//			tspDepart.setDepartname(depart.getDepartname());
//			tsDepart.setTSPDepart(tspDepart);
//			req.setAttribute("depart", tsDepart);
//		}
        req.setAttribute("pid", depart.getId());
		return new ModelAndView("system/depart/depart");
	}
	/**
	 * 部门列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "update")
	public ModelAndView update(TSDepart depart, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(depart.getId())) {
			depart = systemService.getEntity(TSDepart.class, depart.getId());
			req.setAttribute("depart", depart);
		}
		return new ModelAndView("system/depart/depart");
	}
	
	/**
	 * 父级�?��?列表
	 * 
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setPFunction")
	@ResponseBody
	public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if(null != request.getParameter("selfId")){
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("TSPDepart.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSPDepart");
		}

		cq.addOrder("orgCode", SortDirection.asc);

		cq.add();
		List<TSDepart> departsList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");

		TSDepart defaultDepart = new TSDepart();
		defaultDepart.setId("");
		defaultDepart.setDepartname("请选择组织机构");
		departsList.add(0, defaultDepart);

		comboTrees = systemService.ComboTree(departsList, comboTreeModel, null, true);
		return comboTrees;

	}
	/**
	 * 部门列表，树形展示
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departgrid")
	@ResponseBody
	public Object departgrid(TSDepart tSDepart,HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if("yes".equals(request.getParameter("isSearch"))){
			treegrid.setId(null);
			tSDepart.setId(null);
		} 
		if(null != tSDepart.getDepartname()){
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSDepart);
		}
		if (treegrid.getId() != null) {
			cq.eq("TSPDepart.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSPDepart");
		}

		cq.addOrder("orgCode", SortDirection.asc);

		cq.add();
		List<TreeGrid> departList =null;
		departList=systemService.getListByCriteriaQuery(cq, false);
		if(departList.size()==0&&tSDepart.getDepartname()!=null){ 
			cq = new CriteriaQuery(TSDepart.class);
			TSDepart parDepart = new TSDepart();
			tSDepart.setTSPDepart(parDepart);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSDepart);
		    departList =systemService.getListByCriteriaQuery(cq, false);
		}
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("departname");
		treeGridModel.setParentText("TSPDepart_departname");
		treeGridModel.setParentId("TSPDepart_id");
		treeGridModel.setSrc("description");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSDeparts");
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("orgCode", "orgCode");
        fieldMap.put("orgType", "orgType");
		fieldMap.put("mobile", "mobile");
		fieldMap.put("fax", "fax");
		fieldMap.put("address", "address");
		fieldMap.put("order", "departOrder");
        treeGridModel.setFieldMap(fieldMap);
        treeGrids = systemService.treegrid(departList, treeGridModel);

        JSONArray jsonArray = new JSONArray();
        for (TreeGrid treeGrid : treeGrids) {
            jsonArray.add(JSON.parse(treeGrid.toJson()));
        }
        return jsonArray;
	}
	//----
	/**
	 * 方法�??述:  查看�?员列表
	 * 作    者： yiming.zhang
	 * 日    期： Dec 4, 2013-8:53:39 PM
	 * @param request
	 * @param departid
	 * @return 
	 * 返回类型： ModelAndView
	 */
	@RequestMapping(params = "userList")
	public ModelAndView userList(HttpServletRequest request, String departid) {
		request.setAttribute("departid", departid);
		return new ModelAndView("system/depart/departUserList");
	}
	
	/**
	 * 方法�??述:  �?员列表dataGrid
	 * 作    者： yiming.zhang
	 * 日    期： Dec 4, 2013-10:40:17 PM
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid 
	 * 返回类型： void
	 */
	@RequestMapping(params = "userDatagrid")
	public void userDatagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(user!=null&&user.getDepartid()!=null){
			user.setDepartid(null);//设置用户的所属部门的查询�?�件为空；
		}
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		String departid = oConvertUtils.getString(request.getParameter("departid"));
		if (!StringUtil.isEmpty(departid)) {
			DetachedCriteria dc = cq.getDetachedCriteria();
			DetachedCriteria dcDepart = dc.createCriteria("userOrgList");
			dcDepart.add(Restrictions.eq("tsDepart.id", departid));
            // 这�?方�?也是�?�以的
//            DetachedCriteria dcDepart = dc.createAlias("userOrgList", "userOrg");
//            dcDepart.add(Restrictions.eq("userOrg.tsDepart.id", departid));

		}
		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN };
		cq.in("status", userstate);

        //cq.eq("deleteFlag", Globals.Delete_Normal);//删除状�?，�?删除
        //cq.eq("userType",Globals.USER_TYPE_SYSTEM);//系统用户

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
//	/**
//	 * �?员列表dataGrid - minidao方�?（�?�以查看机构包括下级机构的用户），like 'A01%'
//	 * @param user
//	 * @param request
//	 * @param response
//	 * @param dataGrid
//	 */
//	public void userDatagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		String departid = oConvertUtils.getString(request.getParameter("departid"));
//		
//		//手工转�?�minidao查询规则
//		if(oConvertUtils.isNotEmpty(user.getUpdateName())){
//			user.setUserName(user.getUserName().replace("*","%"));
//		}
//		if(oConvertUtils.isNotEmpty(user.getRealName())){
//			user.setRealName(user.getRealName().replace("*","%"));
//		}
//		
//		if(oConvertUtils.isNotEmpty(departid)){
//			TSDepart tsdepart = this.systemService.get(TSDepart.class,departid);
//			MiniDaoPage<TSUser> list = departAuthGroupDao.getUserByDepartCode(dataGrid.getPage(), dataGrid.getRows(),tsdepart.getOrgCode(),user);
//			dataGrid.setTotal(list.getTotal());
//			dataGrid.setResults(list.getResults());
//		}
//		
//		TagUtil.datagrid(response, dataGrid);
//	}
	
    /**
     * 获�?�机构树-combotree
     * @param request
     * @return
     */
    @RequestMapping(params = "getOrgTree")
    @ResponseBody
    public List<ComboTree> getOrgTree(HttpServletRequest request) {
//        findHql�?能处�?�is null�?�件
//        List<TSDepart> departsList = systemService.findHql("from TSPDepart where TSPDepart.id is null");
        List<TSDepart> departsList = systemService.findByQueryString("from TSDepart where TSPDepart.id is null");
        List<ComboTree> comboTrees = new ArrayList<ComboTree>();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");
        comboTrees = systemService.ComboTree(departsList, comboTreeModel, null, true);
        return comboTrees;
    }


    /**
     * 添加 用户到组织机构 的页�?�  跳转
     * @param req request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "goAddUserToOrg")
    public ModelAndView goAddUserToOrg(HttpServletRequest req) {
        req.setAttribute("orgId", req.getParameter("orgId"));
        return new ModelAndView("system/depart/noCurDepartUserList");
    }
    /**
     * 获�?� 除当�? 组织之外的用户信�?�列表
     * @param request request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "addUserToOrgList")
    public void addUserToOrgList(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String orgId = request.getParameter("orgId");

        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);

        // 获�?� 当�?组织机构的用户信�?�
        CriteriaQuery subCq = new CriteriaQuery(TSUserOrg.class);
        subCq.setProjection(Property.forName("tsUser.id"));
        subCq.eq("tsDepart.id", orgId);
        subCq.add();

        cq.add(Property.forName("id").notIn(subCq.getDetachedCriteria()));

        cq.eq("deleteFlag", Globals.Delete_Normal);//删除状�?，�?删除
        cq.eq("userType",Globals.USER_TYPE_SYSTEM);//系统用户

        cq.add();

        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }
    /**
     * 添加 用户到组织机构
     * @param req request
     * @return 处�?�结果信�?�
     */
    @RequestMapping(params = "doAddUserToOrg")
    @ResponseBody
    public AjaxJson doAddUserToOrg(HttpServletRequest req) {
    	String message = null;
        AjaxJson j = new AjaxJson();
        TSDepart depart = systemService.getEntity(TSDepart.class, req.getParameter("orgId"));
        saveOrgUserList(req, depart);
        message =  MutiLangUtil.paramAddSuccess("common.user");
//      systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        j.setMsg(message);

        return j;
    }
    /**
     * �?存 组织机构-用户 关系信�?�
     * @param request request
     * @param depart depart
     */
    private void saveOrgUserList(HttpServletRequest request, TSDepart depart) {
        String orgIds = oConvertUtils.getString(request.getParameter("userIds"));

        List<TSUserOrg> userOrgList = new ArrayList<TSUserOrg>();
        List<String> userIdList = extractIdListByComma(orgIds);
        for (String userId : userIdList) {
            TSUser user = new TSUser();
            user.setId(userId);

            TSUserOrg userOrg = new TSUserOrg();
            userOrg.setTsUser(user);
            userOrg.setTsDepart(depart);

            userOrgList.add(userOrg);
        }
        if (!userOrgList.isEmpty()) {
            systemService.batchSave(userOrgList);
        }
    }

    /**
     * 用户选择机构列表跳转页�?�
     *
     * @return
     */
    @RequestMapping(params = "departSelect")
    public String departSelect(HttpServletRequest req) {
    	
    	req.setAttribute("orgIds", req.getParameter("orgIds"));
    	
        return "system/depart/departSelect";
    }
    /**
     * 角色显示列表
     *
     * @param response response
     * @param dataGrid dataGrid
     */
    @RequestMapping(params = "departSelectDataGrid")
    public void datagridRole(HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 用户选择机构列表跳转页�?�(树列表)
     *
     * @return
     */
    @RequestMapping(params = "orgSelect")
    public String orgSelect(HttpServletRequest req) {
    	
    	req.setAttribute("orgIds", req.getParameter("orgIds"));

        return "system/depart/orgSelect";
    }
    
	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","departController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "uploadDepart")
	public ModelAndView uploadDepart(HttpServletRequest req) {
		req.setAttribute("controller_name","departController");
		req.setAttribute("method_name", "importDepartExcel");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSDepart tsDepart, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsDepart, request.getParameterMap());
		cq.addOrder("orgCode", SortDirection.asc);
		List<TSDepart> tsDeparts = this.systemService.getListByCriteriaQuery(cq,false);
		/*List<TSDepart> finalTsDeparts = new ArrayList<TSDepart>();
		List<TSDepart> tsDeparts = systemService.getSession().createSQLQuery("select * from t_s_depart where length(org_code) = 3 order by org_code asc").addEntity(TSDepart.class).list();
		for(TSDepart tsDepart1:tsDeparts){
			finalTsDeparts.add(tsDepart1);
			String orgcode1 = tsDepart1.getOrgCode();
			List<TSDepart> tsDeparts1 = systemService.getSession().createSQLQuery("select * from t_s_depart where org_code like :orgcode and length(org_code)=6 order by org_code asc").addEntity(TSDepart.class).setString("orgcode",orgcode1+"%").list();
			for(TSDepart tsDepart2:tsDeparts1){
				finalTsDeparts.add(tsDepart2);
				String orgcode2 = tsDepart2.getOrgCode();
				List<TSDepart> tsDeparts2 = systemService.getSession().createSQLQuery("select * from t_s_depart where org_code like :orgcode and length(org_code)=9 order by org_code asc").addEntity(TSDepart.class).setString("orgcode",orgcode2+"%").list();
				for(TSDepart tsDepart3:tsDeparts2){
					finalTsDeparts.add(tsDepart3);
				}
			}
		}*/
		modelMap.put(NormalExcelConstants.FILE_NAME,"组织机构表");
		modelMap.put(NormalExcelConstants.CLASS,TSDepart.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("组织机构表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
				"导出信�?�"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tsDeparts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模�?�
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSDepart tsDepart,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME,"组织机构表");
		modelMap.put(NormalExcelConstants.CLASS,TSDepart.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("组织机构表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
				"导出信�?�"));
		modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	private synchronized String getMaxLocalCode(String parentCode){
		if(oConvertUtils.isEmpty(parentCode)){
			parentCode = "";
		}
		int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT org_code FROM t_s_depart");

		if(ResourceUtil.getJdbcUrl().indexOf(JdbcDao.DATABSE_TYPE_SQLSERVER)!=-1){
			sb.append(" where LEN(org_code) = ").append(localCodeLength);
		}else{
			sb.append(" where LENGTH(org_code) = ").append(localCodeLength);
		}

		if(oConvertUtils.isNotEmpty(parentCode)){
			sb.append(" and  org_code like '").append(parentCode).append("%'");
		}

		sb.append(" ORDER BY org_code DESC");
		List<Map<String, Object>> objMapList = systemService.findForJdbc(sb.toString(), 1, 1);
		String returnCode = null;
		if(objMapList!=null && objMapList.size()>0){
			returnCode = (String)objMapList.get(0).get("org_code");
		}

		return returnCode;
	}
	
	@RequestMapping(params = "importDepartExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importDepartExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获�?�上传文件对象
			ImportParams params = new ImportParams();
			try {
				List<TSDepartExcelView> list = ExcelImportUtil.importExcel(file.getInputStream(),TSDepartExcelView.class,params);
				Map<String,TSDepart> departMap=new HashMap<String, TSDepart>();
				saveDepartExcelView(list,departMap);
				if(departMap.isEmpty()){
					j.setMsg("必须有一个或一个以上的祖先节点�?");
					return j;
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
	
	private void saveDepartExcelView(List<TSDepartExcelView> list,Map<String,TSDepart> departMap){
		Iterator<TSDepartExcelView> iterator = list.iterator();
		while(iterator.hasNext()){
			TSDepartExcelView next = iterator.next();
			String parentId=next.getParentId();
			if(StringUtil.isEmpty(parentId)){
				TSDepart depart=generateDepart(next,null);
				departMap.put(next.getId(), depart);
				iterator.remove();
			}else if(departMap.containsKey(parentId)){
				TSDepart parentDepart=departMap.get(parentId);
				TSDepart depart=generateDepart(next,parentDepart);
				departMap.put(next.getId(), depart);
				iterator.remove();
			}
		}
		if(departMap.isEmpty()){
			return;
		}
		if(!list.isEmpty())saveDepartExcelView(list,departMap);
	}
	
	private TSDepart generateDepart(TSDepartExcelView next,TSDepart parentDepart){
		TSDepart depart=new TSDepart();
		depart.setDepartname(next.getDepartName());
		depart.setDescription(next.getDescription());
		depart.setOrgType(next.getOrgType());
		depart.setAddress(next.getAddress());
		depart.setMobile(next.getMobile());
		depart.setFax(next.getFax());
		if(parentDepart!=null){
			String localMaxCode  = getMaxLocalCode(parentDepart.getOrgCode());
			depart.setOrgCode(YouBianCodeUtil.getSubYouBianCode(parentDepart.getOrgCode(), localMaxCode));
			depart.setTSPDepart(parentDepart);
		}else{
			String localMaxCode  = getMaxLocalCode(null);
			depart.setOrgCode(YouBianCodeUtil.getNextYouBianCode(localMaxCode));
		}
		this.systemService.save(depart);
		return depart;
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
				List<TSDepart> tsDeparts = ExcelImportUtil.importExcel(file.getInputStream(),TSDepart.class,params);
				for (TSDepart tsDepart : tsDeparts) {
					String orgCode = tsDepart.getOrgCode();
					List<TSDepart> departs = systemService.findByProperty(TSDepart.class,"orgCode",orgCode);
					if(departs.size()!=0){
						TSDepart depart = departs.get(0);
						MyBeanUtils.copyBeanNotNull2Bean(tsDepart,depart);
						systemService.saveOrUpdate(depart);
					}else {

						if(oConvertUtils.isNotEmpty(tsDepart.getOrgType())){
							String orgType = tsDepart.getOrgType().substring(0,1);
							if("1".equals(orgType) || "2".equals(orgType) || "3".equals(orgType)){
								tsDepart.setOrgType(orgType);
							}else{
								j.setMsg("机构类型编�?错误");
								return j;
							}
						}else{
							j.setMsg("机构类型编�?�?能为空");
							return j;
						}

						//TSTypegroup ts = systemService.findByProperty(TSTypegroup.class,"typegroupcode","orgtype").get(0);
						//List<TSType> types = systemService.findByProperty(TSType.class,"id",ts.getId());
						//int len = 3;//�?级组织机构得长度
						/*for(int i=0;i<types.size();i++){
							String typecode = types.get(i).getTypecode();

						}*/
						String orgcode = tsDepart.getOrgCode();
						String parentOrgCode = orgcode.substring(0,orgcode.length()-3);

						List<TSDepart> parentList = systemService.getSession().createSQLQuery("select * from t_s_depart where ORG_CODE = :parentOrgCode")
								.addEntity(TSDepart.class)
								.setString("parentOrgCode",parentOrgCode)
								.list();
						if(parentList.size() > 0){
							TSDepart parentDept =  parentList.get(0);
							tsDepart.setTSPDepart(parentDept);
						}
						tsDepart.setDepartOrder("0");

						systemService.save(tsDepart);
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

	@RequestMapping(params = "getDepartInfo")
	@ResponseBody
	public AjaxJson getDepartInfo(HttpServletRequest request, HttpServletResponse response){
		
		AjaxJson j = new AjaxJson();
		
		String orgIds = request.getParameter("orgIds");
		
		String[] ids = new String[]{}; 
		if(StringUtils.isNotBlank(orgIds)){
			orgIds = orgIds.substring(0, orgIds.length()-1);
			ids = orgIds.split("\\,");
		}
		
		String parentid = request.getParameter("parentid");
		
		List<TSDepart> tSDeparts = new ArrayList<TSDepart>();
		
		StringBuffer hql = new StringBuffer(" from TSDepart t where 1=1 ");
		if(StringUtils.isNotBlank(parentid)){
			
			TSDepart dePart = this.systemService.getEntity(TSDepart.class, parentid);
			
			hql.append(" and TSPDepart = ?");
			tSDeparts = this.systemService.findHql(hql.toString(), dePart);
		} else {
			hql.append(" and t.orgType = ?");
			tSDeparts = this.systemService.findHql(hql.toString(), "1");
		}
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		if(tSDeparts.size()>0){
			Map<String,Object> map = null;
			String sql = null;
			 Object[] params = null;
			for(TSDepart depart:tSDeparts){
				map = new HashMap<String,Object>();
				map.put("id", depart.getId());
				map.put("name", depart.getDepartname());

				map.put("code",depart.getOrgCode());

				if(ids.length>0){
					for(String id:ids){
						if(id.equals(depart.getId())){
							map.put("checked", true);
						}
					}
				}
				
				if(StringUtils.isNotBlank(parentid)){
					map.put("pId", parentid);
				} else{
					map.put("pId", "1");
				}
				//根�?�id判断是�?�有�?节点
				sql = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				params = new Object[]{depart.getId()};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					map.put("isParent",true);
				}
				dateList.add(map);
			}
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dateList);
		j.setMsg(jsonArray.toString());
		return j;
	}

}
