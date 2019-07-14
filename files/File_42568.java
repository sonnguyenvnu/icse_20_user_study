
package com.roncoo.pay.reconciliation.utils.alipay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/* *
 *类�??：UtilDate
 *功能：自定义订�?�类
 *详细：工具类，�?�以用作获�?�系统日期�?订�?�编�?�等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代�?�?�是为了方便商户测试而�??供的样例代�?，商户�?�以根�?�自己网站的需�?，按照技术文档编写,并�?�一定�?使用该代�?。
 *该代�?仅供学习和研究支付�?接�?�使用，�?�是�??供一个�?�考。
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort = "yyyyMMdd";
	
    
    /**
     * 返回系统当�?时间(精确到毫秒),作为一个唯一的订�?�编�?�
     * @return
     *      以yyyyMMddHHmmss为格�?的当�?系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获�?�系统当�?日期(精确到毫秒)，格�?：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获�?�系统当期年月日(精确到天)，格�?：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生�?机的三�?数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
}
