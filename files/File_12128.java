package com.geekq.admin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.HashMap;
import java.util.Map;

/**
 * æ•°æ?®å­—å…¸
 * 
 * @author Stef
 * 
 */
@Getter
@Setter
@Alias("SystemDictionary")
public class SystemDictionary extends BaseDomain {
	private static final long serialVersionUID = 3382007784095246946L;
	private String sn; // ç¼–ç ?
	private String title; // å??ç§°
	private String intro; // ç®€ä»‹

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("sn", sn);
		m.put("title", title);
		m.put("intro", intro);
		return JSONObject.toJSONString(m);
	}
}
