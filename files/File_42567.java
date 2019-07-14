package com.roncoo.pay.reconciliation.utils.alipay;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

/** 
* 功能：支付�?MD5签�??处�?�核心文件，�?需�?修改
* 版本：3.3
* 修改日期：2012-08-17
* 说明：
* 以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
* 该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个
* */

public class MD5 {

    /**
     * 签�??字符串
     * @param text 需�?签�??的字符串
     * @param key 密钥
     * @param input_charset 编�?格�?
     * @return 签�??结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签�??字符串
     * @param text 需�?签�??的字符串
     * @param sign 签�??结果
     * @param key 密钥
     * @param input_charset 编�?格�?
     * @return 签�??结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签�??过程中出现错误,指定的编�?集�?对,您目�?指定的编�?集是:" + charset);
        }
    }

}
