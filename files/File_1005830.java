package cn.crap.controller.visitor;

import cn.crap.adapter.ModuleAdapter;
import cn.crap.adapter.ProjectAdapter;
import cn.crap.dto.LoginInfoDto;
import cn.crap.dto.ModuleDto;
import cn.crap.enu.MyError;
import cn.crap.enu.ProjectType;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.base.BaseController;
import cn.crap.model.Project;
import cn.crap.model.ProjectUserPO;
import cn.crap.query.ModuleQuery;
import cn.crap.query.ProjectUserQuery;
import cn.crap.service.ModuleService;
import cn.crap.service.ProjectUserService;
import cn.crap.utils.LoginUserHelper;
import cn.crap.utils.Page;
import cn.crap.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("visitorModuleController")
@RequestMapping("/visitor/module")
public class ModuleController extends BaseController{

    @Autowired
    private ModuleService moduleService;
	@Autowired
	private ProjectUserService projectUserService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(@ModelAttribute ModuleQuery query, String password, String visitCode) throws MyException{
        throwExceptionWhenIsNull(query.getProjectId(), "projectId");

        // 如果是�?有项目，必须登录�?能访问，公开项目需�?查看是�?�需�?密�?
		Project project = projectCache.get(query.getProjectId());
		checkFrontPermission(password, visitCode, project);

        Page page= new Page(query);
		page.setAllRow(moduleService.count(query));

		List<ModuleDto> moduleDtoList = ModuleAdapter.getDto( moduleService.query(query), null);

		return new JsonResult().data(moduleDtoList).page(page).others(
				Tools.getMap("crumbs", Tools.getCrumbs( project.getName(), "void"),
						"project", ProjectAdapter.getDto(project, null)) );
	}

	@RequestMapping("/menu.do")
	@ResponseBody
	public JsonResult menu(@RequestParam String projectId) throws MyException{
		throwExceptionWhenIsNull(projectId, "projectId");

		// 如果是�?有项目，必须登录�?能访问，公开项目需�?查看是�?�需�?密�?
		Project project = projectCache.get(projectId);
		if(project.getType() == ProjectType.PRIVATE.getType()){
			LoginInfoDto user = LoginUserHelper.getUser(MyError.E000041);

			// 最高管�?�员修改项目
			// 自己的项目
			// 项目�?员

			if (!Tools.isSuperAdmin(user.getAuthStr()) && !user.getId().equals(project.getUserId())) {
				List<ProjectUserPO> projectUserPOList =projectUserService.select(new ProjectUserQuery().setProjectId(project.getId()).setUserId(user.getId()));
				if (CollectionUtils.isEmpty(projectUserPOList)) {
					throw new MyException(MyError.E000042);
				}
			}
		}

		List<ModuleDto> moduleDtoList = ModuleAdapter.getDto(moduleService.query(new ModuleQuery().setProjectId(projectId).setPageSize(10)), null);
		return new JsonResult(1, moduleDtoList, null, Tools.getMap("project",  ProjectAdapter.getDto(project, null)) );
	}	
}
