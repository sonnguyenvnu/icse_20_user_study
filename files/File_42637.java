package com.roncoo.pay.trade.utils.alipay.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @功能说明:
 * @创建者: Peter
 * @创建时间: 16/6/14  下�?�3:43
 * @公�?��??称:广州市领课网络科技有�?公�?� 龙果学院(www.roncoo.com)
 * @版本:V1.0
 */
public class AliPayUtil {

    public static Map<String , String> parseNotifyMsg(Map<String, String[]> requestParams){

        Map<String,String> params = new HashMap<String,String>();

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        return params;
    }

}
