package com.foxinmy.weixin4j.http;

import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.NameValue;
import com.foxinmy.weixin4j.util.URLEncodingUtil;

/**
 * 键值对�?�数
 * 
 * @className UrlParameter
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年5月29日
 * @since JDK 1.6
 * @see
 */
public class URLParameter extends NameValue {

	private static final long serialVersionUID = -115491642760990655L;

	public URLParameter(String name, String value) {
		super(name, value);
	}

	public String encoding() {
		return String.format("%s=%s",
				URLEncodingUtil.encoding(getName(), Consts.UTF_8, true),
				URLEncodingUtil.encoding(getValue(), Consts.UTF_8, true));
	}

	@Override
	public String toString() {
		return String.format("[URLParameter name=%s, value=%s]", getName(),
				getValue());
	}
}
