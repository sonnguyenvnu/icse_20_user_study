package com.us.example.controller;



import com.us.example.bean.Message;
import com.us.example.bean.Response;
import com.us.example.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by yangyibo on 16/12/29.
 *
 */
@CrossOrigin
@Controller
public class WebSocketController {
    @Autowired
    private WebSocketService ws;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @RequestMapping(value = "/login")
    public String login(){
        return  "login";
    }
    @RequestMapping(value = "/ws")
    public String ws(){
        return  "ws";
    }
    @RequestMapping(value = "/chat")
    public String chat(){
        return  "chat";
    }
    //http://localhost:8080/ws
    @MessageMapping("/welcome")//æµ?è§ˆå™¨å?‘é€?è¯·æ±‚é€šè¿‡@messageMapping æ˜ å°„/welcome è¿™ä¸ªåœ°å?€ã€‚
    @SendTo("/topic/getResponse")//æœ?åŠ¡å™¨ç«¯æœ‰æ¶ˆæ?¯æ—¶,ä¼šè®¢é˜…@SendTo ä¸­çš„è·¯å¾„çš„æµ?è§ˆå™¨å?‘é€?æ¶ˆæ?¯ã€‚
    public Response say(Message message) throws Exception {
        Thread.sleep(1000);
        return new Response("Welcome, " + message.getName() + "!");
    }

    //http://localhost:8080/Welcome1
    @RequestMapping("/Welcome1")
    @ResponseBody
    public String say2()throws Exception
    {
        ws.sendMessage();
        return "is ok";
    }

    @MessageMapping("/chat")
    //åœ¨springmvc ä¸­å?¯ä»¥ç›´æŽ¥èŽ·å¾—principal,principal ä¸­åŒ…å?«å½“å‰?ç”¨æˆ·çš„ä¿¡æ?¯
    public void handleChat(Principal principal, Message message) {

        /**
         * æ­¤å¤„æ˜¯ä¸€æ®µç¡¬ç¼–ç ?ã€‚å¦‚æžœå?‘é€?äººæ˜¯wyf åˆ™å?‘é€?ç»™ wisely å¦‚æžœå?‘é€?äººæ˜¯wisely å°±å?‘é€?ç»™ wyfã€‚
         * é€šè¿‡å½“å‰?ç”¨æˆ·,ç„¶å?ŽæŸ¥æ‰¾æ¶ˆæ?¯,å¦‚æžœæŸ¥æ‰¾åˆ°æœªè¯»æ¶ˆæ?¯,åˆ™å?‘é€?ç»™å½“å‰?ç”¨æˆ·ã€‚
         */
        if (principal.getName().equals("admin")) {
            //é€šè¿‡convertAndSendToUser å?‘ç”¨æˆ·å?‘é€?ä¿¡æ?¯,
            // ç¬¬ä¸€ä¸ªå?‚æ•°æ˜¯æŽ¥æ”¶æ¶ˆæ?¯çš„ç”¨æˆ·,ç¬¬äºŒä¸ªå?‚æ•°æ˜¯æµ?è§ˆå™¨è®¢é˜…çš„åœ°å?€,ç¬¬ä¸‰ä¸ªå?‚æ•°æ˜¯æ¶ˆæ?¯æœ¬èº«

            messagingTemplate.convertAndSendToUser("abel",
                    "/queue/notifications", principal.getName() + "-send:"
                            + message.getName());
            /**
             * 72 è¡Œæ“?ä½œç›¸ç­‰äºŽ 
             * messagingTemplate.convertAndSend("/user/abel/queue/notifications",principal.getName() + "-send:"
             + message.getName());
             */
        } else {
            messagingTemplate.convertAndSendToUser("admin",
                    "/queue/notifications", principal.getName() + "-send:"
                            + message.getName());
        }
    }
}
