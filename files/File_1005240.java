package org.jeecgframework.web.cgform.enhance;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.math.RandomUtils;

/**
 * è®¢å?•å?·ç”Ÿæˆ?è§„åˆ™å®žçŽ°ç±»
 * @author YanDong
 */
public class OrderNumFillRule implements IFillRuleHandler{

	@Override
	public Object execute(String paramJson) {
		String prefix="CN";
		//è®¢å?•å‰?ç¼€é»˜è®¤ä¸ºCN å¦‚æžœè§„åˆ™å?‚æ•°ä¸?ä¸ºç©ºï¼Œåˆ™å?–è‡ªå®šä¹‰å‰?ç¼€
		if(paramJson!=null && !"".equals(paramJson)){
			JSONObject jsonObject = JSONObject.fromObject(paramJson);
			Object obj = jsonObject.get("prefix");
			if(obj!=null)prefix=obj.toString();
		}
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		int random=RandomUtils.nextInt(90)+10;
		return prefix+format.format(new Date())+random;
	}
}
