package cn.crap.controller.admin;

import cn.crap.beans.Config;
import cn.crap.dto.LoginInfoDto;
import cn.crap.dto.SearchDto;
import cn.crap.dto.SettingDto;
import cn.crap.enu.MyError;
import cn.crap.enu.ProjectPermissionEnum;
import cn.crap.enu.SettingStatus;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.base.BaseController;
import cn.crap.framework.interceptor.AuthPassport;
import cn.crap.query.SearchQuery;
import cn.crap.service.ISearchService;
import cn.crap.service.tool.SystemService;
import cn.crap.utils.HttpPostGet;
import cn.crap.utils.LoginUserHelper;
import cn.crap.utils.Page;
import cn.crap.utils.Tools;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends BaseController {
    @Autowired
    private ISearchService luceneService;
    @Autowired
    private SystemService systemService;

    /**
     * admin dashboard
     * å?Žå?°ç®¡ç?†ä¸»é¡µé?¢
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin.do")
    @AuthPassport
    public String showHomePage() throws Exception {
        return "resources/html/admin/index.html";
    }

    /**
     * èŽ·å?–ç³»ç»Ÿæœ€æ–°ä¿¡æ?¯
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/property.do")
    @AuthPassport(authority = C_AUTH_SETTING)
    @ResponseBody
    public JsonResult property() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        properties.put("domain", settingCache.getDomain());
        properties.put("openRegister", Config.openRegister);
        properties.put("luceneSearchNeedLogin", Config.luceneSearchNeedLogin);
        properties.put("openRegister", Config.openRegister);
        properties.put("canRepeatUrl", Config.canRepeatUrl);
        properties.put("cacheTime", Config.cacheTime);
        properties.put("loginInforTime", Config.loginInforTime);
        properties.put("fileSize", Config.fileSize);
        properties.put("fileType", Config.fileType);
        properties.put("imageType", Config.imageType);
        properties.put("mail", Config.mail);
        properties.put("clientID", Config.clientID);

        returnMap.put("properties", properties);
        // ä»ŽcrapApièŽ·å?–ç‰ˆæœ¬ä¿¡æ?¯
        try {
            String crapApiInfo =
                    HttpPostGet.get("http://api.crap.cn/mock/trueExam.do?id=c107b205-c365-4050-8fa9-dbb7a38b3d11&cache=true", null, null);
            JSONObject json = JSONObject.fromObject(crapApiInfo);
            if (json.getInt("success") == 1) {
                json = json.getJSONObject("data");
                returnMap.put("crapApiInfo",
                        Tools.getStrMap("latestVersion", json.getString("latestVersion"),
                                "latestVersion", json.getString("latestVersion"),
                                "addFunction", json.getString("addFunction"),
                                "updateUrl", json.getString("updateUrl"),
                                "noticeUrl", json.getString("noticeUrl"),
                                "notice", json.getString("notice")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(1, returnMap);
    }

    /**
     * ç™»å½• or æ³¨å†Œé¡µé?¢
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginOrRegister.do")
    public String loginOrRegister() throws Exception {
        return "resources/html/admin/loginOrRegister.html";
    }


    /**
     * åˆ é™¤é”™è¯¯æ??ç¤º
     */
    @RequestMapping("/admin/closeErrorTips.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_ADMIN)
    public JsonResult closeErrorTips() throws Exception {
        stringCache.del(C_CACHE_ERROR_TIP);
        return new JsonResult(1, null);
    }

    /**
     * å?Žå?°é¡µé?¢åˆ?å§‹åŒ–
     */
    @RequestMapping({"/admin/init.do", "back/init.do"})
    @ResponseBody
    @AuthPassport
    public JsonResult init(HttpServletRequest request) throws Exception {
        Map<String, String> settingMap = new HashMap<>();
        for (SettingDto setting : settingCache.getAll()) {
            if (SettingStatus.COMMON.getStatus().equals(setting.getStatus())) {
                settingMap.put(setting.getKey(), setting.getValue());
            }
        }

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("settingMap", settingMap);
        LoginInfoDto user = LoginUserHelper.getUser();
        returnMap.put("sessionAdminName", user.getUserName());
        returnMap.put("adminPermission", user.getAuthStr());
        returnMap.put("sessionAdminId", user.getId());
        returnMap.put("errorTips", stringCache.get(C_CACHE_ERROR_TIP));

        return new JsonResult(1, returnMap);
    }

    /**
     * é‡?å»ºç´¢å¼•ï¼Œå?ªæœ‰æœ€é«˜ç®¡ç?†å‘˜æ‰?å…·æœ‰è¯¥æ?ƒé™?
     */
    @ResponseBody
    @RequestMapping("/admin/rebuildIndex.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult rebuildIndex() throws Exception {
        return new JsonResult(1, luceneService.rebuild());
    }

    /**
     * æ¸…é™¤ç¼“å­˜ï¼Œå?ªæœ‰æœ€é«˜ç®¡ç?†å‘˜æ‰?å…·æœ‰è¯¥æ?ƒé™?
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/flushDB.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult flushDb() {
        projectCache.flushDB();
        stringCache.flushDB();
        moduleCache.flushDB();
        settingCache.flushDB();
        objectCache.flushDB();
        return new JsonResult().success();
    }

    @ResponseBody
    @RequestMapping("/admin/compress.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult compress() throws Exception{
        try {
            systemService.compressSource();
        } catch (Throwable e){
            log.error("åŽ‹ç¼©jsã€?cssæ–‡ä»¶å¼‚å¸¸", e);
        }
        systemService.mergeSource();
        return new JsonResult().success();
    }

    @ResponseBody
    @RequestMapping("/admin/cleanLog.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult cleanLog() throws Exception{
        systemService.cleanLog();
        return new JsonResult().success();
    }

    /**
     * æ?œç´¢ç›®å‰?å?ªæ”¯æŒ?é¡¹ç›®ä¸‹æ?œç´¢
     * è·¨é¡¹ç›®æ¶‰å?Šåˆ°ç”¨æˆ·ã€?æˆ?å‘˜æ?ƒé™?é—®é¢˜ï¼Œæš‚ä¸?å®žçŽ°
     * æ?œç´¢
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/user/search.do")
    @AuthPassport
    public JsonResult search(@ModelAttribute SearchQuery query) throws Exception{
        if (query.getProjectId() == null){
            throw new MyException(MyError.E000056);
        }
        checkPermission(query.getProjectId(), ProjectPermissionEnum.READ);

        String keyword = (query.getKeyword() == null ? "" : query.getKeyword().trim());
        query.setKeyword(keyword.length() > 200 ? keyword.substring(0, 200) : keyword.trim());
        
        List<SearchDto> search = luceneService.search(query);
        Page page = new Page(query);
        return new JsonResult().success().data(search).page(page);
    }

}
