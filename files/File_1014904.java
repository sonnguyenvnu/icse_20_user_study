package com.timebusker.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.timebusker.exception.CommonException;
import com.timebusker.common.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @DESC:IndexController
 * @author:timebusker
 * @date:2018/8/22
 */
@Controller
public class IndexController extends AbstractController {

    @Autowired
    private Producer producer;

    private static final String USER_ACCOUNT = "admin";

    /**
     * èŽ·å?–éªŒè¯?ç ?
     *
     * @throws CommonException
     * @throws IOException
     */
    @RequestMapping("/authcode")
    public void captcha() throws CommonException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //ç”Ÿæˆ?æ–‡å­—éªŒè¯?ç ?
        String text = producer.createText();
        //ç”Ÿæˆ?å›¾ç‰‡éªŒè¯?ç ?
        BufferedImage image = producer.createImage(text);
        //ä¿?å­˜åˆ°shiro session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        // è¾“å‡ºéªŒè¯?ç ?å›¾ç‰‡åˆ°é¡µé?¢
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * ç™»å½•
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String username, String password, String captcha) throws IOException {
        String kaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return R.error("éªŒè¯?ç ?ä¸?æ­£ç¡®");
//        }
        if (!USER_ACCOUNT.equals(username)) {
            return R.error("è´¦æˆ·ä¸?æ­£ç¡®");
        }
        if (!USER_ACCOUNT.equals(password)) {
            return R.error("å¯†ç ?ä¸?æ­£ç¡®");
        }
        session.setAttribute("USER_ACCOUNT", USER_ACCOUNT);
        return R.ok().put("token", USER_ACCOUNT);
    }
}
