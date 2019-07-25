package com.myimooc.small.auth.wxauth.rest;

import java.net.URLEncoder;
import java.security.AccessController;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.myimooc.small.auth.wxauth.domain.User;
import com.myimooc.small.auth.wxauth.repository.UserRepository;
import com.myimooc.small.auth.wxauth.util.AuthUtils;
import sun.security.action.GetPropertyAction;

/**
 * 登录认�?REST
 * @author ZhangCheng on 2017-08-12
 *
 */
@Controller
public class LoginRest {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value={"/","","/index"})
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	/**
	 * 第一步：用户�?��?授�?�，获�?�code
	 * 入�?�地�?�
	 */
	@RequestMapping("wxlogin")
	public Object doLogin(HttpServletRequest req)throws Exception{
		// 编�?�??称
		 String enc = AccessController.doPrivileged(new GetPropertyAction("file.encoding"));
		// 用户授�?��?�微信回调地�?�
		String backUrl = "/callback";
		@SuppressWarnings("deprecation")
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtils.APPID
				+ "&redirect_uri="+URLEncoder.encode(backUrl,enc)
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo "
				+ "&state=STATE#wechat_redirect";
		
		return "redirect:"+url;
	}
	
	/**
	 * 第二步：通过code�?��?�网页授�?�access_token
	 * 回调地�?�-得到code，从而去获得access_token 和 openid
	 */
	@RequestMapping("/callback")
	public ModelAndView doCallBack(HttpServletRequest req)throws Exception{
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtils.APPID
				+ "&secret="+AuthUtils.APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = AuthUtils.doGetJson(url);
		String openid = jsonObject.getString("openid");
		String accessToken = jsonObject.getString("access_token");
		// 第三步：刷新access_token（如果需�?）
		// 此处�?略
		
		// 第四步：拉�?�用户信�?�(需scope为 snsapi_userinfo)
		String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken
				+ "&openid="+openid
				+ "&lang=zh_CN";
		JSONObject userInfo = AuthUtils.doGetJson(infoUrl);
		System.out.println("用户信�?�："+userInfo);
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("info",userInfo);
		String unionid = userInfo.getString("unionid");
		
		// 1.使用微信用户信�?�直接登录，无须注册和绑定，直接跳转到登录�?功界�?�
        boolean isNeedBind = false;
        if (isNeedBind){
            mv.addObject("info",userInfo);
            return mv;
        }
		
		// 2.将微信与当�?系统的账�?�进行绑定，绑定�?�跳转到登录�?功界�?�
		User user = userRepository.findByunionid(unionid);
		if(null != user && (!Objects.equals("", user.getNickname()))){
			// 已绑定，直接跳转绑定�?功的页�?�
			mv.setViewName("bindsuccess");
			mv.addObject("nickname", user.getNickname());
			return mv;
		}else{
			// 未绑定，跳转到自己系统的登录页�?�
			mv.setViewName("login");
			mv.addObject("unionid", unionid);
			return mv;
		}
	}
	
	/**
	 * 登录并绑定微信账�?�
	 */
	@PostMapping("/bindwx")
	public Object bindwx(User user){
		userRepository.save(user);
		return "账�?�绑定�?功";
	}
}
