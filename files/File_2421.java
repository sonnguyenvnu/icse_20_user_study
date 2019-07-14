package com.zheng.upms.server.controller.manage;

import com.zheng.common.base.BaseController;
import com.zheng.upms.client.shiro.session.UpmsSessionDao;
import com.zheng.upms.common.constant.UpmsResult;
import com.zheng.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ä¼šè¯?ç®¡ç?†controller
 * Created by shuzheng on 2017/2/28.
 */
@Controller
@Api(value = "ä¼šè¯?ç®¡ç?†", description = "ä¼šè¯?ç®¡ç?†")
@RequestMapping("/manage/session")
public class UpmsSessionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsSessionController.class);

    @Autowired
    private UpmsSessionDao sessionDAO;

    @ApiOperation(value = "ä¼šè¯?é¦–é¡µ")
    @RequiresPermissions("upms:session:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/session/index.jsp";
    }

    @ApiOperation(value = "ä¼šè¯?åˆ—è¡¨")
    @RequiresPermissions("upms:session:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
        return sessionDAO.getActiveSessions(offset, limit);
    }

    @ApiOperation(value = "å¼ºåˆ¶é€€å‡º")
    @RequiresPermissions("upms:session:forceout")
    @RequestMapping(value = "/forceout/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object forceout(@PathVariable("ids") String ids) {
        int count = sessionDAO.forceout(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
