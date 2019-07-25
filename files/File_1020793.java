package com.fisher.auth.mobile;

import com.fisher.auth.security.UserDetailsImpl;
import com.fisher.auth.service.SysUserService;
import com.fisher.common.constants.SecurityConstants;
import com.fisher.common.vo.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 手机验�?�?登录逻辑实现
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private RedisTemplate<String, String> redisTemplate;

    private SysUserService sysUserService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = mobileAuthenticationToken.getPrincipal().toString();
        String realCode = redisTemplate.opsForValue().get(SecurityConstants.REDIS_CODE_PREFIX + mobile);
        String inputCode = authentication.getCredentials().toString();
        // 判断手机的验�?�?是�?�存在
        if (realCode == null) {
            logger.debug("登录失败，当�?手机�?�验�?�?�?存在或者已�?过期");
            throw new BadCredentialsException("登录失败，验�?�?�?存在");
        }
        // 判断是�?�验�?�?跟redis中存的验�?�?是�?�正确
        if(!inputCode.equalsIgnoreCase(realCode)) {
            logger.debug("登录失败，您输入的验�?�?�?正确");

            throw new BadCredentialsException("登录失败，验�?�?�?正确");
        }
        SysUserVo sysUserVo = sysUserService.loadUserByMobile(mobile);
        if(sysUserVo == null) {
            logger.debug("登录失败，用户�?存在");
            throw new UsernameNotFoundException("登录失败, 手机�?��?�?存在");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(sysUserVo);
        // �?新构造token  登录�?功
        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, inputCode, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
