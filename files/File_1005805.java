package cn.crap.controller.user;

import cn.crap.adapter.BugAdapter;
import cn.crap.dto.BugDTO;
import cn.crap.enu.MyError;
import cn.crap.enu.ProjectPermissionEnum;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.base.BaseController;
import cn.crap.framework.interceptor.AuthPassport;
import cn.crap.model.BugLogPO;
import cn.crap.model.BugPO;
import cn.crap.model.Module;
import cn.crap.model.Project;
import cn.crap.query.BugQuery;
import cn.crap.service.BugLogService;
import cn.crap.service.BugService;
import cn.crap.service.CommentService;
import cn.crap.service.MenuService;
import cn.crap.utils.MyString;
import cn.crap.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user/bug")
public class BugController extends BaseController{
    // TODO �?��?目�?�?��?项目�?员�?��?��?作
    @Autowired
    private CommentService commentService;
    @Autowired
    private BugService bugService;
    @Autowired
    private MenuService customMenuService;
    @Autowired
    private BugLogService bugLogService;

    /**
     * bug管�?�系统下拉选择框
     * @param request
     * @param type
     * @param def
     * @param tag
     * @param pickParam pick�?�数：项目ID等
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "pick.do")
    @AuthPassport
    public String pickUp(HttpServletRequest request, String type, String def,
                          String tag, String pickParam) throws Exception {
        String pickContent = customMenuService.pick("true", type, pickParam, def, "true");
        request.setAttribute("tag", tag);
        request.setAttribute("pickContent", pickContent);
        request.setAttribute("type", type);
        return "WEB-INF/views/bugPick.jsp";
    }

    /**
     * 修改缺陷状�?（严�?程度�?优先级等）
     * @param type 待修改的状�?类型
     * @param value 新的状�?
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changeBug.do")
    @ResponseBody
    @AuthPassport
    public JsonResult changeBug(String id, @RequestParam(defaultValue = "") String type, String value) throws Exception{
        // TODO 修改日志
        // id为0，忽略
        if (MyString.isEmpty(id)){
            return new JsonResult(true);
        }
        BugPO dbBug = bugService.get(id);
        checkPermission(dbBug.getProjectId(), ProjectPermissionEnum.MOD_BUG);

        BugLogPO bugLogPO = new BugLogPO();
        BugPO bug = bugService.getChangeBugPO(id, type, value, bugLogPO, dbBug);
        boolean update = bugService.update(bug);

        dbBug = bugService.get(id);
        Module module = moduleCache.get(dbBug.getModuleId());
        Project project = projectCache.get(dbBug.getProjectId());

        bugLogPO.setBugId(id);
        bugLogPO.setProjectId(dbBug.getProjectId());
        bugLogService.insert(bugLogPO);

        BugDTO dto = BugAdapter.getDto(dbBug, module, project);
        return new JsonResult(update).data(dto);
    }

    @RequestMapping(value = "add.do")
    @ResponseBody
    @AuthPassport
    public JsonResult add(BugDTO dto) throws Exception{
        throwExceptionWhenIsNull(dto.getProjectId(), "projectId �?能为空");

        dto.setProjectId(getProjectId(dto.getProjectId(), dto.getModuleId()));
        checkPermission(dto.getProjectId(), ProjectPermissionEnum.ADD_BUG);

        bugService.insert(BugAdapter.getPO(dto));
        return new JsonResult(true);
    }

    /**
     * bug列表
     * @return
     * @throws MyException
     */
    @RequestMapping("/list.do")
    @ResponseBody
    @AuthPassport
    public JsonResult list(@ModelAttribute BugQuery query) throws Exception {
        Project project = getProject(query);
        checkPermission(project, ProjectPermissionEnum.READ);
        query.setPageSize(10);

        List<BugPO> bugPOList = bugService.select(query);
        Page page = new Page(query);
        page.setAllRow(bugService.count(query));
        List<BugDTO> dtoList = BugAdapter.getDto(bugPOList);

        return new JsonResult().data(dtoList).page(page);
    }

    @RequestMapping("/detail.do")
    @ResponseBody
    @AuthPassport
    public JsonResult detail(BugDTO dto) throws MyException {
        throwExceptionWhenIsNull(dto.getProjectId(), "projectId �?能为空");

        String id = dto.getId();
        dto.setProjectId(getProjectId(dto.getProjectId(), dto.getModuleId()));
        checkPermission(dto.getProjectId(), ProjectPermissionEnum.READ);

        Project project = projectCache.get(dto.getProjectId());
        Module module = moduleCache.get(dto.getModuleId());

        if (id != null) {
            BugPO bugPO = bugService.get(id);
            module = moduleCache.get(bugPO.getModuleId());
            return new JsonResult(1, BugAdapter.getDto(bugPO, module, project));
        }

        BugDTO bugDTO = BugAdapter.getDTO(project, module);
        return new JsonResult(1, bugDTO);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    @AuthPassport
    public JsonResult delete(String id) throws MyException {
        throwExceptionWhenIsNull(id, "id");

        BugPO bugPO = bugService.get(id);
        if (bugPO == null) {
            throw new MyException(MyError.E000063);
        }
        checkPermission(bugPO.getProjectId(), ProjectPermissionEnum.DEL_BUG);
        bugService.delete(id);
        return new JsonResult(1, null);
    }

}
