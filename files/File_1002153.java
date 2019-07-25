package me.zhengjie.modules.security.rest;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.monitor.service.RedisService;
import me.zhengjie.modules.security.security.AuthenticationInfo;
import me.zhengjie.modules.security.security.AuthorizationUser;
import me.zhengjie.modules.security.security.ImgResult;
import me.zhengjie.modules.security.security.JwtUser;
import me.zhengjie.modules.security.utils.VerifyCodeUtils;
import me.zhengjie.utils.EncryptUtils;
import me.zhengjie.modules.security.utils.JwtTokenUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * æŽˆæ?ƒã€?æ ¹æ?®tokenèŽ·å?–ç”¨æˆ·è¯¦ç»†ä¿¡æ?¯
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * ç™»å½•æŽˆæ?ƒ
     * @param authorizationUser
     * @return
     */
    @Log("ç”¨æˆ·ç™»å½•")
    @PostMapping(value = "${jwt.auth.path}")
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser){

        // æŸ¥è¯¢éªŒè¯?ç ?
        String code = redisService.getCodeVal(authorizationUser.getUuid());
        // æ¸…é™¤éªŒè¯?ç ?
        redisService.delete(authorizationUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("éªŒè¯?ç ?å·²è¿‡æœŸ");
        }
        if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("éªŒè¯?ç ?é”™è¯¯");
        }
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
            throw new AccountExpiredException("å¯†ç ?é”™è¯¯");
        }

        if(!jwtUser.isEnabled()){
            throw new AccountExpiredException("è´¦å?·å·²å?œç”¨ï¼Œè¯·è?”ç³»ç®¡ç?†å‘˜");
        }

        // ç”Ÿæˆ?ä»¤ç‰Œ
        final String token = jwtTokenUtil.generateToken(jwtUser);

        // è¿”å›ž token
        return ResponseEntity.ok(new AuthenticationInfo(token,jwtUser));
    }

    /**
     * èŽ·å?–ç”¨æˆ·ä¿¡æ?¯
     * @return
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo(){
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        return ResponseEntity.ok(jwtUser);
    }

    /**
     * èŽ·å?–éªŒè¯?ç ?
     */
    @GetMapping(value = "vCode")
    public ImgResult getCode(HttpServletResponse response) throws IOException {

        //ç”Ÿæˆ?éš?æœºå­—ä¸²
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtil.simpleUUID();
        redisService.saveCode(uuid,verifyCode);
        // ç”Ÿæˆ?å›¾ç‰‡
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            return new ImgResult(Base64.encode(stream.toByteArray()),uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            stream.close();
        }
    }
}
