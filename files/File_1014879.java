package cn.timebusker.service.impl;

import cn.timebusker.security.constant.RoleConstant;
import cn.timebusker.mapper.UserMapper;
import cn.timebusker.model.UserEntity;
import cn.timebusker.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
@Log4j
public class BaseUserService implements UserService {

    private final UserMapper userMapper;

    public BaseUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean insert(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if (exist(username)) {
            return false;
        }
        encryptPassword(userEntity);
        userEntity.setRoles(RoleConstant.ROLE_USER);
        int result = userMapper.insert(userEntity);
        return result == 1;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 判断用户是�?�存在
     *
     * @param username 账�?�
     * @return 密�?
     */
    private boolean exist(String username) {
        UserEntity userEntity = userMapper.selectByUsername(username);
        return (userEntity != null);
    }

    /**
     * 加密密�?
     */
    private void encryptPassword(UserEntity userEntity) {
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
    }

}
