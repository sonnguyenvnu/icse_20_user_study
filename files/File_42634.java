package com.roncoo.pay.trade.utils.alipay.util;

import com.roncoo.pay.trade.utils.alipay.config.AlipayConfigUtil;
import com.roncoo.pay.trade.utils.alipay.sign.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* *
 *类�??：AlipayNotify
 *功能：支付�?通知处�?�类
 *详细：处�?�支付�?�?�接�?�通知返回
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
 *该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个�?�考

 *************************注�?*************************
 *调试通知返回时，�?�查看或改写log日志的写入TXT里的数�?�，�?�检查通知返回是�?�正常
 */
public class AlipayNotify {

    private static final Logger LOG = LoggerFactory.getLogger(AlipayNotify.class);
    /**
     * 支付�?消�?�验�?地�?�
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     * 验�?消�?�是�?�是支付�?�?�出的�?�法消�?�
     * @param params 通知返回�?�的�?�数数组
     * @return 验�?结果
     */
    public static boolean verify(Map<String, String> params) {

        //判断responsetTxt是�?�为true，isSign是�?�为true
        //responsetTxt的结果�?是true，与�?务器设置问题�?�?�作身份者ID�?notify_id一分钟失效有关
        //isSign�?是true，与安全校验�?�?请求时的�?�数格�?（如：带自定义�?�数等）�?编�?格�?有关
    	String responseTxt = "false";
		if(params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        //写日志记录（若�?调试，请�?�消下�?�两行注释）
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回�?�的�?�数：" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);

        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根�?��??馈回�?�的信�?�，生�?签�??结果
     * @param Params 通知返回�?�的�?�数数组
     * @param sign 比对的签�??结果
     * @return 生�?的签�??结果
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
    	//过滤空值�?sign与sign_type�?�数
    	Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
        //获�?�待签�??字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        //获得签�??验�?结果
        boolean isSign = false;
        if(AlipayConfigUtil.sign_type.equals("MD5") ) {
        	isSign = MD5.verify(preSignStr, sign, AlipayConfigUtil.key, AlipayConfigUtil.input_charset);
        }
        return isSign;
    }

    /**
    * 获�?�远程�?务器ATN结果,验�?返回URL
    * @param notify_id 通知校验ID
    * @return �?务器ATN结果
    * 验�?结果集：
    * invalid命令�?�数�?对 出现这个错误，请检测返回处�?�中partner和key是�?�为空 
    * true 返回正确信�?�
    * false 请检查防�?�墙或者是�?务器阻止端�?�问题以�?�验�?时间是�?�超过一分钟
    */
    private static String verifyResponse(String notify_id) {
        //获�?�远程�?务器ATN结果，验�?是�?�是支付�?�?务器�?��?�的请求

        String partner = AlipayConfigUtil.partner;
        String veryfyUrl = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

        return checkUrl(veryfyUrl);
    }

    /**
    * 获�?�远程�?务器ATN结果
    * @param urlvalue 指定URL路径地�?�
    * @return �?务器ATN结果
    * 验�?结果集：
    * invalid命令�?�数�?对 出现这个错误，请检测返回处�?�中partner和key是�?�为空 
    * true 返回正确信�?�
    * false 请检查防�?�墙或者是�?务器阻止端�?�问题以�?�验�?时间是�?�超过一分钟
    */
    private static String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            LOG.error("alipay checkUrl exception:",e);
            inputLine = "";
        }

        return inputLine;
    }
}
