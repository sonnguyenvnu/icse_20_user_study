package com.us.example.security;

import com.us.example.dao.UserDao;
import com.us.example.domain.SysRole;
import com.us.example.domain.SysUser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyibo on 17/1/18.
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接�?�

    @Autowired
    UserDao userDao;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) { //�?写loadUserByUsername 方法获得 userdetails 类型用户

        SysUser user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户�??�?存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的�?��?。�?��?把用户�?��?添加到authorities 就万事大�?�。
        for(SysRole role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info("loadUserByUsername: " + user);
        }
        user.setGrantedAuthorities(authorities); //用于登录时 @AuthenticationPrincipal 标签�?�值
        return user;
    }

}
