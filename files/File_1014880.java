package cn.timebusker.web;

import cn.timebusker.model.UserEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @DESC:AuthoTestController
 * @author:timebusker
 * @date:2018/7/7
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthoRoleController {

    /**
     * 基于方法使用注解，�?�以结�?�Spring EL表达�?细粒度的�?��?
     * <p>
     * 更安全
     */

    // 基于角色�?��?校验
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/authRole")
    public String role() {
        return "admin auth";
    }

    // 匹�?校验（执行�?校验(PreAuthorize)，执行�?�校验(PostAuthorize)）
    @PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
    @PostAuthorize("returnObject%2==0")
    @RequestMapping("/grep")
    public Integer test(Integer id, String username, UserEntity user) {
        // ...
        return id;
    }

    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @RequestMapping("/test2")
    public List<Integer> test2(List<Integer> idList) {
        // ...
        return idList;
    }

}
