package com.lou.springboot.service.impl;

import com.lou.springboot.dao.AdminUserDao;
import com.lou.springboot.entity.AdminUser;
import com.lou.springboot.service.AdminUserService;
import com.lou.springboot.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13
 * @qqäº¤æµ?ç¾¤ 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public PageResult getAdminUserPage(PageUtil pageUtil) {
        //å½“å‰?é¡µç ?ä¸­çš„æ•°æ?®åˆ—è¡¨
        List<AdminUser> users = adminUserDao.findAdminUsers(pageUtil);
        //æ•°æ?®æ€»æ?¡æ•° ç”¨äºŽè®¡ç®—åˆ†é¡µæ•°æ?®
        int total = adminUserDao.getTotalAdminUser(pageUtil);
        PageResult pageResult = new PageResult(users, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public AdminUser updateTokenAndLogin(String userName, String password) {
        AdminUser adminUser = adminUserDao.getAdminUserByUserNameAndPassword(userName, MD5Util.MD5Encode(password, "UTF-8"));
        if (adminUser != null) {
            //ç™»å½•å?Žå?³æ‰§è¡Œä¿®æ”¹tokençš„æ“?ä½œ
            String token = getNewToken(System.currentTimeMillis() + "", adminUser.getId());
            if (adminUserDao.updateUserToken(adminUser.getId(), token) > 0) {
                //è¿”å›žæ•°æ?®æ—¶å¸¦ä¸Štoken
                adminUser.setUserToken(token);
                return adminUser;
            }
        }
        return null;
    }

    /**
     * èŽ·å?–tokenå€¼
     *
     * @param sessionId
     * @param userId
     * @return
     */
    private String getNewToken(String sessionId, Long userId) {
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public AdminUser selectById(Long id) {
        return adminUserDao.getAdminUserById(id);
    }

    @Override
    public AdminUser selectByUserName(String userName) {
        return adminUserDao.getAdminUserByUserName(userName);
    }

    @Override
    public int save(AdminUser user) {
        //å¯†ç ?åŠ å¯†
        user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
        return adminUserDao.addUser(user);
    }

    @Override
    public int updatePassword(AdminUser user) {
        return adminUserDao.updateUserPassword(user.getId(), MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return adminUserDao.deleteBatch(ids);
    }

    @Override
    public AdminUser getAdminUserByToken(String userToken) {
        return adminUserDao.getAdminUserByToken(userToken);
    }
}
