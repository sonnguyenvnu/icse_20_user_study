package com.roncoo.pay.trade.utils.alipay.util.httpClient;

import com.roncoo.pay.trade.utils.alipay.config.AlipayConfigUtil;
import org.apache.commons.httpclient.Header;

import java.io.UnsupportedEncodingException;

/* *
 *类�??：HttpResponse
 *功能：Http返回对象的�?装
 *详细：�?装Http返回信�?�
 *版本：3.3
 *日期：2011-08-17
 *说明：
 *以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
 *该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个�?�考。
 */

public class HttpResponse {

    /**
     * 返回中的Header信�?�
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;

    /**
     * btye类型的result
     */
    private byte[]   byteResult;

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return null;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, AlipayConfigUtil.input_charset);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
