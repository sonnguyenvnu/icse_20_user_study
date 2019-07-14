package org.hswebframework.web.controller.dashboard;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.AuthenticationPredicate;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.commons.entity.DataStatus;
import org.hswebframework.web.controller.dashboard.model.UserDashBoardResponse;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.dashboard.DashBoardConfigEntity;
import org.hswebframework.web.dashboard.DashBoardService;
import org.hswebframework.web.entity.authorization.UserSettingEntity;
import org.hswebframework.web.service.authorization.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhouhao
 * @since 3.0
 */
@RestController
@RequestMapping("/dashboard-user")
@Api(tags = "仪表盘-用户�?置", value = "仪表盘用户�?置")
@Authorize
public class DashBoardUserConfigController {

    @Autowired
    private UserSettingService userSettingService;

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/all")
    @ApiOperation("获�?�用户�?�选择的仪表盘�?置")
    public ResponseMessage<List<UserDashBoardResponse>> getUserAllDashBoardConfig(Authentication authentication) {
        List<UserDashBoardResponse> configList = dashBoardService.select()
                .stream()
                .filter(Objects::nonNull)
                .filter(config-> DataStatus.STATUS_ENABLED.equals(config.getStatus()))
                //过滤�?��?
                .filter(config -> StringUtils.isEmpty(config) ||
                        AuthenticationPredicate.has(config.getPermission()).test(authentication))
                .map(config -> config.copyTo(new UserDashBoardResponse()))
                .collect(Collectors.toList());
        return ResponseMessage.ok(configList);
    }

    @GetMapping
    @ApiOperation("获�?�用户自定义的仪表盘�?置")
    public ResponseMessage<List<UserDashBoardResponse>> getUserConfigDashBoardConfig(Authentication authentication) {
        UserSettingEntity settingEntity = userSettingService.selectByUser(authentication.getUser().getId(), "dashboard-config", "current");
        List<DashBoardConfigEntity> configs;

        if (settingEntity == null) {
            configs = dashBoardService.selectAllDefaults();
            Collections.sort(configs);
        } else {
            List<String> ids = JSON.parseArray(settingEntity.getSetting(), String.class);
            configs = dashBoardService.selectByPk(ids);
            configs.sort(Comparator.comparing(conf -> ids.indexOf(conf.getId())));
        }
        List<UserDashBoardResponse> configList = configs.stream()
                //过滤�?��?
                .filter(config -> DataStatus.STATUS_ENABLED.equals(config.getStatus())&&(!StringUtils.hasText(config.getPermission()) ||
                        AuthenticationPredicate.has(config.getPermission()).test(authentication)))
                .map(config -> config.copyTo(new UserDashBoardResponse()))
                .collect(Collectors.toList());

        return ResponseMessage.ok(configList);
    }

    @PutMapping
    @ApiOperation("�?存用户自定义的仪表盘�?置")
    public ResponseMessage<Void> saveUserDashBoardConfig(@RequestBody List<String> configIdList, Authentication authentication) {
        UserSettingEntity settingEntity = userSettingService.selectByUser(authentication.getUser().getId(), "dashboard-config", "current");
        if (settingEntity == null) {
            settingEntity = userSettingService.createEntity();
            settingEntity.setUserId(authentication.getUser().getId());
            settingEntity.setKey("dashboard-config");
            settingEntity.setSettingId("current");
        }
        settingEntity.setSetting(JSON.toJSONString(configIdList));
        userSettingService.saveOrUpdate(settingEntity);

        return ResponseMessage.ok();
    }
}
