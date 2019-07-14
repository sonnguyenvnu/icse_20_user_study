package com.sohu.cache.web.factory;

import java.util.Map;

import com.sohu.cache.constant.UserLoginTypeEnum;
import com.sohu.cache.util.ConstUtils;
import com.sohu.cache.web.service.UserLoginStatusService;

/**
 * 用户登录状�?信�?��?务工厂
 * @author leifu
 * @Date 2016年6月15日
 * @Time 下�?�1:48:36
 */
public class UserLoginStatusFactory {

    private Map<UserLoginTypeEnum, UserLoginStatusService> userloginTypeMap;

    public UserLoginStatusService getUserLoginStatusService() {
        UserLoginTypeEnum loginTypeEnum = UserLoginTypeEnum.getLoginTypeEnum(ConstUtils.USER_LOGIN_TYPE);
        return userloginTypeMap.get(loginTypeEnum);
    }

    public void setUserloginTypeMap(Map<UserLoginTypeEnum, UserLoginStatusService> userloginTypeMap) {
        this.userloginTypeMap = userloginTypeMap;
    }

}
