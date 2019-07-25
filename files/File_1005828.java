package cn.crap.controller.visitor;

import cn.crap.adapter.InterfaceAdapter;
import cn.crap.dto.InterfaceDto;
import cn.crap.dto.InterfacePDFDto;
import cn.crap.enu.MyError;
import cn.crap.enu.SettingEnum;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.ThreadContext;
import cn.crap.framework.base.BaseController;
import cn.crap.model.InterfaceWithBLOBs;
import cn.crap.model.Module;
import cn.crap.model.Project;
import cn.crap.query.InterfaceQuery;
import cn.crap.service.InterfaceService;
import cn.crap.service.ModuleService;
import cn.crap.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("visitorInterfaceController")
@RequestMapping("/visitor/interface")
public class InterfaceController extends BaseController {

    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private ModuleService moduleService;

    private final static String ERROR_INTERFACE_ID = "æŽ¥å?£idæœ‰è¯¯ï¼Œç”Ÿæˆ?PDFå¤±è´¥ã€‚è¯·ç¡®è®¤é…?ç½®æ–‡ä»¶config.propertiesä¸­çš„ç½‘ç«™åŸŸå??é…?ç½®æ˜¯å?¦æ­£ç¡®ï¼?";
    private final static String ERROR_MODULE_ID = "æ¨¡å?—idæœ‰è¯¯ï¼Œç”Ÿæˆ?PDFå¤±è´¥ã€‚è¯·ç¡®è®¤é…?ç½®æ–‡ä»¶config.propertiesä¸­çš„ç½‘ç«™åŸŸå??é…?ç½®æ˜¯å?¦æ­£ç¡®ï¼?";

    /***
     *  ä¸‹è½½æ—¶è°ƒç”¨è¯¥æŽ¥å?£ç”Ÿæˆ?htmlï¼Œç„¶å?Žè½¬æ?¢æˆ?pdfä¸‹è½½
     * @param id
     * @param moduleId
     * @param secretKey
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail/pdf.do")
    public String pdf(String id, String moduleId, @RequestParam String secretKey) throws Exception {
        HttpServletRequest request = ThreadContext.request();
        try {
            if (MyString.isEmpty(id) && MyString.isEmpty(moduleId)){
                request.setAttribute("result", "æŽ¥å?£ID&æ¨¡å?—IDä¸?èƒ½å?Œæ—¶ä¸ºç©ºï¼?");
                return ERROR_VIEW;
            }
            if (!secretKey.equals(settingCache.get(S_SECRETKEY).getValue())) {
                request.setAttribute("result", "ç§˜é’¥ä¸?æ­£ç¡®ï¼Œé?žæ³•è¯·æ±‚ï¼?");
                return ERROR_VIEW;
            }

            if (MyString.isEmpty(id) && MyString.isEmpty(moduleId)) {
                request.setAttribute("result", "å?‚æ•°ä¸?èƒ½ä¸ºç©ºï¼Œç”Ÿæˆ?PDFå¤±è´¥ï¼?");
                return ERROR_VIEW;
            }

            List<InterfacePDFDto> interfacePDFDtos = new ArrayList<>();
            request.setAttribute("MAIN_COLOR", settingCache.get("MAIN_COLOR").getValue());
            request.setAttribute("ADORN_COLOR", settingCache.get("ADORN_COLOR").getValue());
            request.setAttribute("title", settingCache.get("TITLE").getValue());

            /**
             * å?•ä¸ªç”Ÿæˆ?pdfæŽ¥å?£
             */
            if (!MyString.isEmpty(id)) {
                InterfaceWithBLOBs interFace = interfaceService.getById(id);
                if (interFace == null) {
                    request.setAttribute("result", ERROR_INTERFACE_ID);
                    return ERROR_VIEW;
                }
                Module module = moduleCache.get(interFace.getModuleId());

                InterfacePDFDto interDto = interfaceService.getInterPDFDto(interFace, module, true, true);

                interfacePDFDtos.add(interDto);
                request.setAttribute("interfaces", interfacePDFDtos);
                request.setAttribute("moduleName", module.getName());
                return "/WEB-INF/views/interFacePdf.jsp";
            }

            /**
             * æŒ‰æ¨¡å?—æ‰¹é‡?ç”Ÿæˆ?pdfæŽ¥å?£
             */
            Module module = moduleService.getById(moduleId);
            if (module == null) {
                request.setAttribute("result", ERROR_MODULE_ID);
                return ERROR_VIEW;
            }
            for (InterfaceWithBLOBs interFace : interfaceService.queryAll(new InterfaceQuery().setModuleId(moduleId))) {
                InterfacePDFDto interDto = interfaceService.getInterPDFDto(interFace, module, true, true);
                interfacePDFDtos.add(interDto);
            }

            request.setAttribute("interfaces", interfacePDFDtos);
            request.setAttribute("moduleName", module.getName());
            return "/WEB-INF/views/interFacePdf.jsp";
        } catch (Exception e) {
            log.error("æŽ¥å?£ä¸‹è½½æ•°æ?®æœ‰è¯¯,id:" + id + ",moduleId:" + moduleId, e);
            request.setAttribute("result", "æŽ¥å?£æ•°æ?®æœ‰è¯¯ï¼Œè¯·ä¿®æ”¹æŽ¥å?£å?Žå†?è¯•ï¼Œé”™è¯¯ä¿¡æ?¯ï¼š" + e.getMessage());
            return ERROR_VIEW;
        }
    }

    @RequestMapping("/download/pdf.do")
    @ResponseBody
    public void download(String id, String moduleId, @RequestParam(defaultValue = "true") boolean pdf,
                         HttpServletRequest req, HttpServletResponse response) throws Exception {
        Assert.isTrue(id != null || moduleId != null, MyError.E000029.getMessage());

        InterfaceWithBLOBs interFace = null;
        Module module = moduleCache.get(moduleId);
        if (id != null) {
            interFace = interfaceService.getById(id);
            module = moduleCache.get(interFace.getModuleId());
        }

        Project project = getProject(interFace == null ? null : interFace.getProjectId(), module.getId());
        String downloadName = (interFace == null) ? module.getName() : interFace.getInterfaceName();

        // å¦‚æžœæ˜¯ç§?æœ‰é¡¹ç›®ï¼Œå¿…é¡»ç™»å½•æ‰?èƒ½è®¿é—®ï¼Œå…¬å¼€é¡¹ç›®éœ€è¦?æŸ¥çœ‹æ˜¯å?¦éœ€è¦?å¯†ç ?:ä½¿ç”¨ç¼“å­˜çš„å¯†ç ?ï¼Œä¸?éœ€è¦?éªŒè¯?ç ?
        checkFrontPermission("", "", project);
        if (pdf) {
            String secretKey = settingCache.get(S_SECRETKEY).getValue();
            String fileName = Html2Pdf.createPdf(req, settingCache.getDomain(), id, moduleId, secretKey);
            DownloadUtils.downloadWord(response, new File(fileName), downloadName, true);
        }else{
            Map<String, Object> map = new HashMap<>();
            List<InterfacePDFDto> interfacePDFDtos = new ArrayList<>();
            if (interFace == null){
                for (InterfaceWithBLOBs interfaceWithBLOBs : interfaceService.queryAll(new InterfaceQuery().setModuleId(moduleId))) {
                    interfacePDFDtos.add(interfaceService.getInterPDFDto(interfaceWithBLOBs, module, true, false));
                }
            }else {
                interfacePDFDtos.add(interfaceService.getInterPDFDto(interFace, module, true, false));
            }

            map.put("interfacePDFDtos", interfacePDFDtos);
            WordUtils.downloadWord(response, map, downloadName);
        }
    }


    @RequestMapping("/list.do")
    @ResponseBody
    public JsonResult webList(@RequestParam String moduleId, String interfaceName, String url,
                               Integer currentPage, String password, String visitCode) throws MyException {
        if (MyString.isEmpty(moduleId)) {
            throw new MyException(MyError.E000020);
        }

        Module module = moduleService.getById(moduleId);
        Project project = projectCache.get(module.getProjectId());
        // å¦‚æžœæ˜¯ç§?æœ‰é¡¹ç›®ï¼Œå¿…é¡»ç™»å½•æ‰?èƒ½è®¿é—®ï¼Œå…¬å¼€é¡¹ç›®éœ€è¦?æŸ¥çœ‹æ˜¯å?¦éœ€è¦?å¯†ç ?
        checkFrontPermission(password, visitCode, project);

        InterfaceQuery query = new InterfaceQuery().setModuleId(moduleId).setInterfaceName(interfaceName).setFullUrl(url).setCurrentPage(currentPage);
        Page page = new Page(query);
        page.setAllRow(interfaceService.count(query));

        List<InterfaceDto> interfaces = InterfaceAdapter.getDto(interfaceService.query(query), module, null);

        return new JsonResult().data(interfaces).page(page).others(
                Tools.getMap("crumbs", Tools.getCrumbs(projectCache.get(module.getProjectId()).getName(),
                        "# /module/list?projectId=" + module.getProjectId(), module.getName(), "void")));
    }

    @RequestMapping("/detail.do")
    @ResponseBody
    public JsonResult webDetail(@ModelAttribute InterfaceWithBLOBs interFace, String password, String visitCode) throws MyException {
        interFace = interfaceService.getById(interFace.getId());
        if (interFace != null) {
            Module module = moduleCache.get(interFace.getModuleId());
            Project project = projectCache.get(interFace.getProjectId());
            // å¦‚æžœæ˜¯ç§?æœ‰é¡¹ç›®ï¼Œå¿…é¡»ç™»å½•æ‰?èƒ½è®¿é—®ï¼Œå…¬å¼€é¡¹ç›®éœ€è¦?æŸ¥çœ‹æ˜¯å?¦éœ€è¦?å¯†ç ?
            checkFrontPermission(password, visitCode, project);

            /**
             * æŸ¥è¯¢ç›¸å?Œæ¨¡å?—ä¸‹ï¼Œç›¸å?ŒæŽ¥å?£å??çš„å…¶å®ƒç‰ˆæœ¬å?·
             */
            InterfaceQuery query = new InterfaceQuery().setModuleId(interFace.getModuleId()).setEqualInterfaceName(interFace.getInterfaceName())
                    .setExceptVersion(interFace.getVersion()).setPageSize(ALL_PAGE_SIZE).setProjectId(interFace.getProjectId());
            List<InterfaceDto> versions = InterfaceAdapter.getDto(interfaceService.query(query), module, null);

            return new JsonResult(1, InterfaceAdapter.getDtoWithBLOBs(interFace, module, null, false), null,
                    Tools.getMap("versions", versions,
                            "crumbs",
                            Tools.getCrumbs(
                                    project.getName(), "#/module/list?projectId=" + project.getId(),
                                    module.getName() + ":æŽ¥å?£åˆ—è¡¨", "#/interface/list?projectId=" + project.getId() + "&moduleId=" + module.getId(),
                                    interFace.getInterfaceName(), "void"), "module", moduleCache.get(interFace.getModuleId())));
        } else {
            throw new MyException(MyError.E000012);
        }
    }

}
