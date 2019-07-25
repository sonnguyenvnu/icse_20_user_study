package com.kakarote.crm9.erp.admin.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.Cache;
import com.kakarote.crm9.common.constant.BaseConstant;
import com.kakarote.crm9.erp.admin.entity.AdminUser;
import com.kakarote.crm9.erp.admin.service.AdminRoleService;
import com.kakarote.crm9.utils.BaseUtil;
import com.kakarote.crm9.utils.R;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.redis.Redis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户登录
 *
 * @author z
 */
@Clear
public class AdminLoginController extends Controller {

    @Inject
    private AdminRoleService adminRoleService;

    public static Prop prop = PropKit.use("config/crm9-config.txt");

    public void index() {
        redirect("/index.html");
    }

    /**
     * @param username 用户�??
     * @param password 密�?
     * @author zhangzhiwei
     * 用户登录
     */
    public void login(@Para("username") String username, @Para("password") String password) {
        String key = BaseConstant.USER_LOGIN_ERROR_KEY+username;
        Cache redis = Redis.use();
        long beforeTime = System.currentTimeMillis() - 60*5*1000;
        if(redis.exists(key)){
            Set zrevrange = redis.zrange(key, 0,0);
            Long time = (Long) zrevrange.iterator().next()+60*5*1000;
            if (redis.zcount(key, beforeTime, System.currentTimeMillis()) >= 5) {
                long expire = (time-System.currentTimeMillis())/1000;
                renderJson(R.error("密�?错误次数过多，请等"+expire+"秒�?�在�?试�?"));
                return;
            }
        }
        redis.zadd(key,System.currentTimeMillis(),System.currentTimeMillis());
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            renderJson(R.error("请输入用户�??和密�?�?"));
            return;
        }
        AdminUser user = AdminUser.dao.findFirst(Db.getSql("admin.user.queryByUserName"), username.trim());
        if (user == null) {
            renderJson(R.error("用户�??或密�?错误�?"));
        } else {
            if (user.getStatus() == 0) {
                renderJson(R.error("账户被�?用�?"));
            }
            if (BaseUtil.verify(username + password, user.getSalt(), user.getPassword())) {
                if (user.getStatus() == 2) {
                    user.setStatus(1);
                }
                String token = IdUtil.simpleUUID();
                user.setLastLoginIp(BaseUtil.getLoginAddress(getRequest()));
                user.setLastLoginTime(new Date());
                user.update();
                user.setRoles(adminRoleService.queryRoleIdsByUserId(user.getUserId()));
                Redis.use().setex(token, 360000, user);
                user.remove("password", "salt");
                setCookie("Admin-Token", token, 360000);
                renderJson(R.ok().put("Admin-Token", token).put("user", user).put("auth", adminRoleService.auth(user.getUserId())));
            } else {
                Log.getLog(getClass()).warn("用户登录失败");
                renderJson(R.error("用户�??或密�?错误�?"));
            }
        }
    }

    /**
     * @author zhangzhiwei
     * 退出登录
     */
    public void logout() {
        String token = BaseUtil.getToken(getRequest());
        if (!StrUtil.isEmpty(token)) {
            Redis.use().del(token);
            removeCookie("Admin-Token");
        }
        renderJson(R.ok());
    }

    public void version() {
        renderJson(R.ok().put("name", BaseConstant.NAME).put("version", BaseConstant.VERSION));
    }

    public void ping() {
        List<String> arrays = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Db.use().getConfig().getConnection();
            if (connection != null) {
                arrays.add("数�?�库连接�?功");
            }
        } catch (Exception e) {
            arrays.add("数�?�库连接异常");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        try {
            String ping = Redis.use().ping();
            if ("PONG".equals(ping)) {
                arrays.add("Redis�?置�?功");
            } else {
                arrays.add("Redis�?置失败");
            }
        } catch (Exception e) {
            arrays.add("Redis�?置失败");
        }
        renderJson(R.ok().put("data", arrays));
    }

    /**
     * @author wyq
     * 接入钉钉
     */
    public void dingLogin(String code){
        String appkey = prop.get("appkey");
        String appSecert = prop.get("appSecret");
        String tokenJson = HttpUtil.get("https://oapi.dingtalk.com/gettoken?appkey="+appkey+"&appsecret="+appSecert);
        String accessToken = JSONObject.parseObject(tokenJson).getString("access_token");
        String userJson = HttpUtil.get("https://oapi.dingtalk.com/user/getuserinfo?access_token="+accessToken+"&code="+code);
        String userId = JSONObject.parseObject(userJson).getString("userid");
        String userInfo = HttpUtil.get("https://oapi.dingtalk.com/user/get?access_token="+accessToken+"&userid="+userId);
        String mobile = JSONObject.parseObject(userInfo).getString("mobile");
        Integer isUser = Db.queryInt("select count(*) from 72crm_admin_user where mobile = ?",mobile);
        if (isUser > 0){
            AdminUser user = AdminUser.dao.findFirst(Db.getSql("admin.user.queryByUserName"), mobile.trim());
            String token = IdUtil.simpleUUID();
            user.setLastLoginIp(BaseUtil.getLoginAddress(getRequest()));
            user.setLastLoginTime(new Date());
            user.update();
            user.setRoles(adminRoleService.queryRoleIdsByUserId(user.getUserId()));
            Redis.use().setex(token, 360000, user);
            user.remove("password", "salt");
            setCookie("Admin-Token", token, 360000);
            renderJson(R.ok().put("Admin-Token", token).put("user", user).put("auth", adminRoleService.auth(user.getUserId())));
        }else {
            renderJson(R.error("账户�?存在"));
        }
    }
}
