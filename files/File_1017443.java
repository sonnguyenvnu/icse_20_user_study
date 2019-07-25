package com.foxinmy.weixin4j.mp.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.mp.type.SemCategory;

/**
 * 语义�?�解�?�数
 * 
 * @className SemQuery
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014年11月7日
 * @since JDK 1.6
 * @see
 */
public class SemQuery implements Serializable {

	private static final long serialVersionUID = 679548284525912436L;
	private JSONObject jsonObj;

	/**
	 * 输入文本串
	 * 
	 * @param query
	 */
	public SemQuery(String query) {
		jsonObj = new JSONObject();
		jsonObj.put("query", query);
	}

	/**
	 * 城市�??称,与�?纬度二选一传入
	 * 
	 * @param city
	 * @return
	 */
	public SemQuery city(String city) {
		jsonObj.put("city", city);
		return this;
	}

	/**
	 * 需�?使用的�?务类别,多个用,隔开,�?能为空
	 * 
	 * @param categorys
	 * @return
	 */
	public SemQuery category(SemCategory... categorys) {
		StringBuilder category = new StringBuilder();
		if (categorys.length == 1) {
			category.append(categorys[0].name());
		} else {
			for (int i = 0; i < categorys.length - 1; i++) {
				category.append(categorys[i].name()).append(",");
			}
			category.append(categorys[categorys.length - 1].name());
		}
		jsonObj.put("category", category.toString());
		return this;
	}

	/**
	 * ￼App id,开�?�者的唯一标识,用于区分开放者, 如果为空,则没法使用上下文�?�解功能。
	 * 
	 * @param appid
	 * @return
	 */
	public SemQuery appid(String appid) {
		jsonObj.put("appid", appid);
		return this;
	}

	/**
	 * 用户唯一 id(并�?�开�?�者 id),用于区分该开�?�者下�?�?�用户,如果为空,则没法使用上下文�?�解功能。appid 和
	 * uid�?�时存在的情况下,�?�?�以使用上下文�?�解功能。
	 * 
	 * @param uid
	 * @return
	 */
	public SemQuery uid(String uid) {
		jsonObj.put("uid", uid);
		return this;
	}

	/**
	 * 区域�??称,在城市存在的情况下�?��?;与�?纬度 二选一传入
	 * 
	 * @param region
	 * @return
	 */
	public SemQuery region(String region) {
		jsonObj.put("region", region);
		return this;
	}

	/**
	 * 纬度�?度;与城市二选一传入
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public SemQuery location(float latitude, float longitude) {
		jsonObj.put("latitude", latitude);
		jsonObj.put("longitude", longitude);
		return this;
	}

	/**
	 * 输入文本串
	 * 
	 * @param query
	 * @return
	 */
	public static SemQuery build(String query) {
		return new SemQuery(query);
	}

	public String toJson() {
		return jsonObj.toJSONString();
	}

	@Override
	public String toString() {
		return "SemQuery " + jsonObj;
	}
}
