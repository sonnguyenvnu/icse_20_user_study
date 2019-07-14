package com.springboot.demo.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private JavaMailSender jms;

	@Value("${spring.mail.username}")
	private String from;
	
    @Autowired
    private TemplateEngine templateEngine;

	@RequestMapping("sendSimpleEmail")
	public String sendSimpleEmail() {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo("888888@qq.com"); // æŽ¥æ”¶åœ°å?€
			message.setSubject("ä¸€å°?ç®€å?•çš„é‚®ä»¶"); // æ ‡é¢˜
			message.setText("ä½¿ç”¨Spring Bootå?‘é€?ç®€å?•é‚®ä»¶ã€‚"); // å†…å®¹
			jms.send(message);
			return "å?‘é€?æˆ?åŠŸ";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("sendHtmlEmail")
	public String sendHtmlEmail() {
		MimeMessage message = null;
		try {
			message = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); 
			helper.setTo("888888@qq.com"); // æŽ¥æ”¶åœ°å?€
			helper.setSubject("ä¸€å°?HTMLæ ¼å¼?çš„é‚®ä»¶"); // æ ‡é¢˜
			// å¸¦HTMLæ ¼å¼?çš„å†…å®¹
			StringBuffer sb = new StringBuffer("<p style='color:#42b983'>ä½¿ç”¨Spring Bootå?‘é€?HTMLæ ¼å¼?é‚®ä»¶ã€‚</p>");
			helper.setText(sb.toString(), true);
			jms.send(message);
			return "å?‘é€?æˆ?åŠŸ";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("sendAttachmentsMail")
	public String sendAttachmentsMail() {
		MimeMessage message = null;
		try {
			message = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); 
			helper.setTo("888888@qq.com"); // æŽ¥æ”¶åœ°å?€
			helper.setSubject("ä¸€å°?å¸¦é™„ä»¶çš„é‚®ä»¶"); // æ ‡é¢˜
			helper.setText("è¯¦æƒ…å?‚è§?é™„ä»¶å†…å®¹ï¼?"); // å†…å®¹
			// ä¼ å…¥é™„ä»¶
			FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/file/é¡¹ç›®æ–‡æ¡£.docx"));
            helper.addAttachment("é¡¹ç›®æ–‡æ¡£.docx", file);
            jms.send(message);
			return "å?‘é€?æˆ?åŠŸ";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("sendInlineMail")
	public String sendInlineMail() {
		MimeMessage message = null;
		try {
			message = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); 
			helper.setTo("888888@qq.com"); // æŽ¥æ”¶åœ°å?€
			helper.setSubject("ä¸€å°?å¸¦é?™æ€?èµ„æº?çš„é‚®ä»¶"); // æ ‡é¢˜
			helper.setText("<html><body>å?šå®¢å›¾ï¼š<img src='cid:img'/></body></html>", true); // å†…å®¹
			// ä¼ å…¥é™„ä»¶
			FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/sunshine.png"));
            helper.addInline("img", file); 
            jms.send(message);
			return "å?‘é€?æˆ?åŠŸ";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("sendTemplateEmail")
	public String sendTemplateEmail(String code) {
		MimeMessage message = null;
		try {
			message = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); 
			helper.setTo("888888@qq.com"); // æŽ¥æ”¶åœ°å?€
			helper.setSubject("é‚®ä»¶æ‘¸æ?¿æµ‹è¯•"); // æ ‡é¢˜
			// å¤„ç?†é‚®ä»¶æ¨¡æ?¿
		    Context context = new Context();
		    context.setVariable("code", code);
		    String template = templateEngine.process("emailTemplate", context);
			helper.setText(template, true);
			jms.send(message);
			return "å?‘é€?æˆ?åŠŸ";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
