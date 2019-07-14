package com.roncoo.pay.reconciliation.utils.alipay;

import com.roncoo.pay.trade.utils.AlipayConfigUtil;
import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* *
 *类�??：AlipaySubmit
 *功能：支付�?�?�接�?�请求�??交类
 *详细：构造支付�?�?�接�?�表�?�HTML文本，获�?�远程HTTP数�?�
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
 *该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个�?�考。
 */

public class AlipaySubmit {

	/**
	 * �?�作身份者ID，签约账�?�
	 */
	private static final String PARTNER = AlipayConfigUtil.readConfig("partner");

	/**
	 * MD5密钥，安全检验�?
	 */
	private static final String KEY = AlipayConfigUtil.readConfig("key");

	/**
	 * 支付�?�??供给商户的�?务接入网关URL(新)
	 */
	private static final String ALIPAY_GATEWAY_NEW = AlipayConfigUtil.readConfig("alipay_gateway_new");

	/**
	 * 签�??方�?
	 */
	private static final String SIGN_TYPE = AlipayConfigUtil.readConfig("sign_type");

	/**
	 * 字符编�?格�? 目�?支�?
	 */
	private static final String INPUT_CHARSET = AlipayConfigUtil.readConfig("input_charset");

	/**
	 * 生�?签�??结果
	 * 
	 * @param sPara
	 *            �?签�??的数组
	 * @return 签�??结果字符串
	 */
	public static String buildRequestMysign(Map<String, String> sPara) {
		String prestr = AlipayCore.createLinkString(sPara); // 把数组所有元素，按照“�?�数=�?�数值�?的模�?用“&�?字符拼接�?字符串
		String mysign = "";
		if (SIGN_TYPE.equals("MD5")) {
			mysign = MD5.sign(prestr, KEY, INPUT_CHARSET);
		}
		return mysign;
	}

	/**
	 * 生�?�?请求给支付�?的�?�数数组
	 * 
	 * @param sParaTemp
	 *            请求�?的�?�数数组
	 * @return �?请求的�?�数数组
	 */
	public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签�??�?�数
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);

		// 生�?签�??结果
		String mysign = buildRequestMysign(sPara);

		// 签�??结果与签�??方�?加入请求�??交�?�数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", SIGN_TYPE);

		return sPara;
	}

	/**
	 * 建立请求，以表�?�HTML形�?构造（默认）
	 * 
	 * @param sParaTemp
	 *            请求�?�数数组
	 * @param strMethod
	 *            �??交方�?。两个值�?�选：post�?get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @return �??交表�?�HTML文本
	 */
	public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
		// 待请求�?�数数组
		Map<String, String> sPara = buildRequestPara(sParaTemp);

		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW + "_input_charset=" + INPUT_CHARSET + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请�?�?�?�有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

		return sbHtml.toString();
	}

	/**
	 * MAP类型数组转�?��?NameValuePair类型
	 * 
	 * @param properties
	 *            MAP类型数组
	 * @return NameValuePair类型数组
	 */
	public static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
		}

		return nameValuePair;
	}

	/**
	 * 用于防钓鱼，调用接�?�query_timestamp�?�获�?�时间戳的处�?�函数 注�?：远程解�?XML出错，与�?务器是�?�支�?SSL等�?置有关
	 * 
	 * @return 时间戳字符串
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	public static String query_timestamp() throws MalformedURLException, DocumentException, IOException {

		// 构造访问query_timestamp接�?�的URL串
		String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + PARTNER + "&_input_charset" + INPUT_CHARSET;
		StringBuffer result = new StringBuffer();

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new URL(strUrl).openStream());

		List<Node> nodeList = doc.selectNodes("//alipay/*");

		for (Node node : nodeList) {
			// 截�?�部分�?需�?解�?的信�?�
			if (node.getName().equals("is_success") && node.getText().equals("T")) {
				// 判断是�?�有�?功标示
				List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
				for (Node node1 : nodeList1) {
					result.append(node1.getText());
				}
			}
		}

		return result.toString();
	}
}
