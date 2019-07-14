package com.roncoo.pay.trade.utils.alipay.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* *
 *类�??：AlipayFunction
 *功能：支付�?接�?�公用函数类
 *详细：该类是请求�?通知返回两个文件所调用的公用函数核心处�?�文件，�?需�?修改
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
 *该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个�?�考。
 */

public class AlipayCore {

    /** �?有构造函数 **/
    private AlipayCore(){}

    /** 
     * 除去数组中的空值和签�??�?�数
     * @param sArray 签�??�?�数组
     * @return 去掉空值与签�??�?�数�?�的新签�??�?�数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.isEmpty()) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)
                || "sign_type".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * 把数组所有元素排�?，并按照“�?�数=�?�数值�?的模�?用“&�?字符拼接�?字符串
     * @param params 需�?排�?并�?�与字符拼接的�?�数组
     * @return 拼接�?�字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，�?包括最�?�一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 生�?文件摘�?
     * @param strFilePath 文件路径
     * @param file_digest_type 摘�?算法
     * @return 文件摘�?结果
     */
    public static String getAbstract(String strFilePath, String file_digest_type) throws IOException {
        PartSource file = new FilePartSource(new File(strFilePath));
    	if("MD5".equals(file_digest_type)){
    		return DigestUtils.md5Hex(file.createInputStream());
    	}
    	else if("SHA".equals(file_digest_type)) {
    		return DigestUtils.sha256Hex(file.createInputStream());
    	}
    	else {
    		return "";
    	}
    }
}
