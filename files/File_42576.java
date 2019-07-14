package com.roncoo.pay.reconciliation.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 
 * @类功能说明： 微信对账接�?�工具类.
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公�?��??称：广州领课网络科技有�?公�?�（龙果学院:www.roncoo.com）
 * @作者：Along.shen
 * @创建时间：2016年5月23日,上�?�10:24:57.
 * @版本：V1.0
 *
 */
public class WeiXinBaseUtils {
	private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 生�?16�?�?机的字符串
	 * 
	 * @return
	 */
	public static String createNoncestr() {
		return createNoncestr(16);
	}

	/**
	 * 生�?�?机的字符串
	 * 
	 * @param length
	 * @return
	 */
	private static String createNoncestr(int length) {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		int clength = chars.length();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rd.nextInt(clength - 1)));
		}
		return sb.toString();
	}

	/**
	 * 生�?xml文件
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToXml(HashMap<String, String> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (isNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";

			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}

		xml += "</xml>";
		return xml;
	}

	private static boolean isNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

}
