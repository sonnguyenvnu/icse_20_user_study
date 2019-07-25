package com.myimooc.mail.register.service;

import com.myimooc.mail.register.dao.UserRepository;
import com.myimooc.mail.register.domain.User;
import com.myimooc.mail.register.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * 标题: 用户�?务<br>
 * �??述: 用户�?务<br>
 * 时间: 2017/06/07<br>
 *
 * @author zc
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 用户注册的方法
     *
     * @param user
     */
    public void regist(User user) {
        // 将数�?�存入到数�?�库
        userRepository.save(user);

        // �?��?一�?激活邮件
        try {
            MailUtils.sendMail(user.getEmail(), user.getCode());
        } catch (Exception e) {
            System.out.println("邮件�?��?异常" + e);
        }
    }

    /**
     * 用户激活的方法
     *
     * @param user
     */
    public boolean registActive(String code) {

        User user = userRepository.findByCode(code);
        if (null == user) {
            return false;
        }

        user.setState(1);
        user.setCode("");

        userRepository.save(user);

        return true;
    }

}
