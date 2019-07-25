package cn.crap.controller.user;

import cn.crap.adapter.InterfaceAdapter;
import cn.crap.adapter.ProjectMetaAdapter;
import cn.crap.beans.Config;
import cn.crap.controller.visitor.MockController;
import cn.crap.dto.*;
import cn.crap.enu.InterfaceContentType;
import cn.crap.enu.MonitorType;
import cn.crap.enu.MyError;
import cn.crap.enu.ProjectPermissionEnum;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.base.BaseController;
import cn.crap.framework.interceptor.AuthPassport;
import cn.crap.model.Error;
import cn.crap.model.InterfaceWithBLOBs;
import cn.crap.model.Module;
import cn.crap.model.Project;
import cn.crap.query.ErrorQuery;
import cn.crap.query.InterfaceQuery;
import cn.crap.service.ErrorService;
import cn.crap.service.ISearchService;
import cn.crap.service.InterfaceService;
import cn.crap.service.ProjectMetaService;
import cn.crap.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user/interface")
public class InterfaceController extends BaseController{

	@Autowired
	private InterfaceService interfaceService;
	@Autowired
	private ISearchService luceneService;
	@Autowired
	private ErrorService errorService;
	@Autowired
	private ProjectMetaService projectMetaService;

	@RequestMapping("/list.do")
	@ResponseBody
	@AuthPassport
	public JsonResult list(@ModelAttribute InterfaceQuery query, String url) throws MyException{
        Module module = getModule(query);
        Project project = getProject(query);
        checkPermission(project, ProjectPermissionEnum.READ);

        InterfaceQuery interfaceQuery = query.setFullUrl(url);
		Page page= new Page(interfaceQuery);

		List<InterfaceDto> interfaces = InterfaceAdapter.getDto(interfaceService.query(interfaceQuery), module, project);
		page.setAllRow(interfaceService.count(interfaceQuery));
		JsonResult result = new JsonResult(1, interfaces, page);
		result.putOthers("module", module);

		return result;
		
	}

	@RequestMapping("/detail.do")
	@ResponseBody
	public JsonResult detail(String id, String moduleId, String projectId, String envId) throws MyException {
		InterfaceWithBLOBs model;
		Module module;
		if(id != null){
			model= interfaceService.getById(id);
			module = moduleCache.get(model.getModuleId());
		} else{
			model = InterfaceAdapter.getInit();
			module = moduleCache.get(moduleId);
			model.setModuleId( moduleId);
			model.setProjectId(module.getProjectId() == null ? projectId : module.getProjectId());
            // æ ¹æ?®æ¨¡æ?¿åˆ?å§‹åŒ–æŽ¥å?£
            if(!MyString.isEmpty(module.getTemplateId())){
				InterfaceWithBLOBs template = interfaceService.getById(module.getTemplateId());
				if(template != null){
				    BeanUtil.copyProperties(template, model, "id", "projectId", "interfaceName", "url",
                            "fulUrl", "sequence", "isTemplate");
				}
			}
		}
        checkPermission(model.getProjectId(), ProjectPermissionEnum.READ);
		InterfaceDto interfaceDto = InterfaceAdapter.getDtoWithBLOBs(model, module, null, false);

		// debugé¡µé?¢çŽ¯å¢ƒåˆ‡æ?¢
		if (envId != null){
			ProjectMetaDTO dto = ProjectMetaAdapter.getDto(projectMetaService.get(envId), null);
			if (dto != null && MyString.isNotEmpty(dto.getEnvUrl())){
				interfaceDto.setFullUrl(interfaceDto.getFullUrl().replaceFirst(dto.getEnvUrl(), dto.getValue()));
			}
		}
		return new JsonResult(1, interfaceDto);
	}
	
	/**
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	@RequestMapping("/copy.do")
	@ResponseBody
	public JsonResult copy(@RequestParam String id, String interfaceName, String url, String moduleId) throws MyException, IOException {
		InterfaceWithBLOBs interFace = interfaceService.getById(id);

		//åˆ¤æ–­æ˜¯å?¦æ‹¥æœ‰åŽŸæ?¥é¡¹ç›®çš„æ?ƒé™?
		checkPermission(interFace.getProjectId(), ProjectPermissionEnum.READ);
        Project project = getProject(interFace.getProjectId(), moduleId);
        Module module = moduleCache.get(moduleId);
		// æ£€æŸ¥æ–°é¡¹ç›®çš„æ?ƒé™?
        checkPermission(project, ProjectPermissionEnum.ADD_INTER);

		if(!Config.canRepeatUrl){
			if (interfaceService.count(new InterfaceQuery().setModuleId(moduleId).setEqualFullUrl(module.getUrl() + interFace.getUrl())) > 0){
				throw new MyException(MyError.E000004);
			}
		}

		interFace.setId(null);
		interFace.setUrl(url);
        interFace.setFullUrl((module.getUrl() == null ? "" : module.getUrl()) + url);
        interFace.setInterfaceName(interfaceName);
        interFace.setModuleId(moduleId);
        interFace.setProjectId(project.getId());
		interfaceService.insert(interFace);

        interFace.setId(interFace.getId());
		luceneService.add(InterfaceAdapter.getSearchDto(interFace));
		return new JsonResult(1, interFace);
	}

	@RequestMapping("/addOrUpdate.do")
	@ResponseBody
	@AuthPassport
	public JsonResult addOrUpdate(@ModelAttribute InterfaceDto dto, String modifyRemark) throws IOException, MyException {
		Assert.notNull(dto.getProjectId(), "projectId can't be null");
        Assert.notNull(dto.getUrl(), "url can't be null");

        String id = dto.getId();
        Module module = moduleCache.get(dto.getModuleId());
        String newProjectId = getProjectId(dto.getProjectId(), dto.getModuleId());
        dto.setProjectId(newProjectId);
		dto.setUrl(dto.getUrl().trim());
        dto.setFullUrl(module.getUrl() + dto.getUrl());

        List<ParamDto> headerList =JSONArray.parseArray(dto.getHeader() == null ? "[]" : dto.getHeader(), ParamDto.class);
        ParamDto contentTypeDto = Optional.ofNullable(headerList).orElse(Lists.newArrayList()).stream()
                .filter(tempHeader -> tempHeader.getName() != null && tempHeader.getName().equalsIgnoreCase(IConst.C_CONTENT_TYPE)).findFirst().orElse(null);
        if (C_PARAM_FORM.equals(dto.getParamType())){
		    dto.setParam(C_PARAM_FORM_PRE + dto.getParam());
			/**
			 * æ™®é€šè¡¨å?•æ¨¡å¼?ä¸?æ”¯æŒ?content-typeè®¾ç½®
			 */
			if (contentTypeDto != null){
                headerList.remove(contentTypeDto);
            }
        }
        /**
         * è‡ªå®šä¹‰å?‚æ•°ï¼Œä¸‹æ‹‰æ·»åŠ Content-Type
         */
        else {
            String reqContentType = Optional.ofNullable(dto.getReqContentType()).orElse(InterfaceContentType.JSON.getType());
            if (contentTypeDto == null){
                ParamDto paramDto = new ParamDto(C_CONTENT_TYPE, C_TRUE, C_STRING, reqContentType, "æŒ‡å®šå?‚æ•°ç±»åž‹ä¸º" + reqContentType);
                headerList.add(0, paramDto);
            } else{
                contentTypeDto.setDef(reqContentType);
                contentTypeDto.setRemark("æŒ‡å®šå?‚æ•°ç±»åž‹ä¸º" + reqContentType);
            }
		}
        dto.setHeader(JSON.toJSONString(headerList));
		
		/**
		 * æ ¹æ?®é€‰ç?€çš„é”™è¯¯ç ?idï¼Œç»„è£…jsonå­—ç¬¦ä¸²
		 */
        ErrorQuery query = new ErrorQuery().setProjectId(newProjectId).setErrorCodeList(Tools.getIdsFromField(dto.getErrorList())).setPageSize(100);
        List<Error> errors  = errorService.query(query);
		dto.setErrors(JSONArray.toJSONString(errors));

		LoginInfoDto user = LoginUserHelper.getUser();
		dto.setUpdateBy("userNameï¼š"+user.getUserName()+" | trueNameï¼š"+ user.getTrueName());
        dto.setMonitorType(MonitorType.No.getValue());
        InterfaceWithBLOBs interFace = InterfaceAdapter.getModel(dto);
        if (id != null) {
			String oldProjectId = interfaceService.getById(interFace.getId()).getProjectId();
			// åˆ¤æ–­æ˜¯å?¦æœ‰ä¿®æ”¹æ¨¡å?—çš„æ?ƒé™?
			checkPermission(oldProjectId, ProjectPermissionEnum.READ);
            checkPermission(newProjectId, ProjectPermissionEnum.MOD_INTER);

			//å?Œä¸€æ¨¡å?—ä¸‹ä¸?å…?è®¸ url é‡?å¤?
            InterfaceQuery interfaceQuery = new InterfaceQuery().setProjectId(newProjectId).setFullUrl(interFace.getFullUrl()).setExceptId(interFace.getId());
			if( !Config.canRepeatUrl && interfaceService.count(interfaceQuery) >0 ){
				throw new MyException(MyError.E000004);
			}
			
			interfaceService.update(interFace, "æŽ¥å?£", modifyRemark);
			stringCache.del(MockController.getMockKey(id, true));
			stringCache.del(MockController.getMockKey(id, false));
			stringCache.del(MockController.getMockKey(id, null));
		} else {
			checkPermission(interFace.getProjectId(), ProjectPermissionEnum.ADD_INTER);
            InterfaceQuery interfaceQuery = new InterfaceQuery().setProjectId(newProjectId).setFullUrl(interFace.getFullUrl());
			if(!Config.canRepeatUrl && interfaceService.count(interfaceQuery)>0){
				return new JsonResult(MyError.E000004);
			}
			interfaceService.insert(interFace);
			id = interFace.getId();
		}

        luceneService.update(InterfaceAdapter.getSearchDto(interfaceService.getById(id)));
        return new JsonResult(1, interFace);
	}


	@RequestMapping("/debug.do")
	@ResponseBody
	@AuthPassport
	public JsonResult debug(@ModelAttribute InterfaceDto interFace) throws IOException, Exception {
		Assert.notNull(interFace.getMethod(), "è¯·æ±‚æ–¹æ³•ä¸?èƒ½ä¸ºç©º");
		Assert.notNull(interFace.getFullUrl(), "åœ°å?€ä¸?èƒ½ä¸ºç©º");
        Assert.isTrue(interFace.getFullUrl().startsWith("http"), "åœ°å?€å¿…é¡»ä»¥ httpå¼€å¤´");

        String fullUrl = interFace.getFullUrl();

		List<ParamDto> headerList = JSONArray.parseArray(interFace.getHeader() == null ? "[]" : interFace.getHeader(), ParamDto.class);

        Map<String, String> httpHeaders = new HashMap<>();
        for (ParamDto paramDto : headerList) {
            httpHeaders.put(paramDto.getName(), paramDto.getDef());
        }
        // å¦‚æžœè‡ªå®šä¹‰å?‚æ•°ä¸?ä¸ºç©ºï¼Œåˆ™è¡¨ç¤ºéœ€è¦?ä½¿ç”¨postå?‘é€?è‡ªå®šä¹‰åŒ…ä½“
        if (C_PARAM_RAW.equals(interFace.getParamType())) {
            return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.postBody(fullUrl, interFace.getParam(), httpHeaders)));
        }

        List<ParamDto> paramList = JSONArray.parseArray(interFace.getParam() == null ? "[]" : interFace.getParam(), ParamDto.class);


        Map<String, String> httpParams = new HashMap<>();
        for (ParamDto paramDto : paramList) {
            if (fullUrl.contains("{" + paramDto.getRealName() + "}")) {
                fullUrl = fullUrl.replace("{" + paramDto.getName() + "}", paramDto.getDef());
            } else {
                httpParams.put(paramDto.getName(), paramDto.getDef());
            }
        }
        try {
            switch (interFace.getMethod()) {
                case "POST":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.post(fullUrl, httpParams, httpHeaders)));
                case "GET":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.get(fullUrl, httpParams, httpHeaders)));
                case "PUT":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.put(fullUrl, httpParams, httpHeaders)));
                case "DELETE":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.delete(fullUrl, httpParams, httpHeaders)));
                case "HEAD":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.head(fullUrl, httpParams, httpHeaders)));
                case "OPTIONS":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.options(fullUrl, httpParams, httpHeaders)));
                case "TRACE":
                    return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.trace(fullUrl, httpParams, httpHeaders)));
				case "PATCH":
					return new JsonResult(1, Tools.getMap("debugResult", HttpPostGet.patch(fullUrl, httpParams, httpHeaders)));
                default:
                    return new JsonResult(1, Tools.getMap("debugResult", "ä¸?æ”¯æŒ?çš„è¯·æ±‚æ–¹æ³•ï¼š" + interFace.getMethod()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, Tools.getMap("debugResult", "è°ƒè¯•å‡ºé”™\r\nmessage:" + e.getMessage()));
        }

	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult delete(String id, String ids) throws MyException, IOException{
		if( MyString.isEmpty(id) && MyString.isEmpty(ids)){
			throw new MyException(MyError.E000029);
		}
		if( MyString.isEmpty(ids) ){
			ids = id;
		}
		
		for(String tempId : ids.split(",")){
			if(MyString.isEmpty(tempId)){
				continue;
			}
			InterfaceWithBLOBs interFace = interfaceService.getById( tempId );
			checkPermission(interFace.getProjectId(), ProjectPermissionEnum.DEL_INTER);

            luceneService.delete(new SearchDto(interFace.getId()));
			interfaceService.delete(interFace.getId(), "æŽ¥å?£", "");
		}
		return new JsonResult(1, null);
	}
}
