package com.cg.baseproject.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.cg.baseproject.manager.AppLogMessageMgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 主�?功能： 用于App验�?数�?�验�?
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版�?�所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressLint("SimpleDateFormat")
@SuppressWarnings("rawtypes")
public class ValidateUtils {

	
	//邮箱表达�?
	private final static Pattern email_pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
	
	//手机�?�表达�?
	private final static Pattern phone_pattern = Pattern.compile("^(13|15|18)\\d{9}$");
	
	//银行�?��?�表达�?
	private final static Pattern bankNo_pattern = Pattern.compile("^[0-9]{16,19}$");
	
	//座机�?��?表达�?
	private final static Pattern plane_pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$");  
	
	//�?�零表达�?
	private final static Pattern notZero_pattern = Pattern.compile("^\\+?[1-9][0-9]*$");
	
	//数字表达�?
	private final static Pattern number_pattern = Pattern.compile("^[0-9]*$");
	
	//大写字�?表达�?
	private final static Pattern upChar_pattern = Pattern.compile("^[A-Z]+$");
	
	//�?写字�?表达�?
	private final static Pattern lowChar_pattern = Pattern.compile("^[a-z]+$");

	//大�?写字�?表达�?
	private final static Pattern letter_pattern = Pattern.compile("^[A-Za-z]+$");
	
	//中文汉字表达�?
	private final static Pattern chinese_pattern = Pattern.compile("^[\u4e00-\u9fa5],{0,}$");
	
	//�?�形�?表达�?
	private final static Pattern onecode_pattern = Pattern.compile("^(([0-9])|([0-9])|([0-9]))\\d{10}$");
	
	//邮政编�?表达�?
	private final static Pattern postalcode_pattern = Pattern.compile("([0-9]{3})+.([0-9]{4})+"); 
	
	//IP地�?�表达�?
	private final static Pattern ipaddress_pattern = Pattern.compile("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"); 
	
	//URL地�?�表达�?
	private final static Pattern url_pattern = Pattern.compile("(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"); 
	
	//用户�??表达�?
	private final static Pattern username_pattern = Pattern.compile("^[A-Za-z0-9_]{1}[A-Za-z0-9_.-]{3,31}"); 
	
	//真实姓�??表达�?
	private final static Pattern realnem_pattern = Pattern.compile("[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*"); 
	
	//匹�?HTML标签,通过下�?�的表达�?�?�以匹�?出HTML中的标签属性。
	private final static Pattern html_patter = Pattern.compile("<\\\\/?\\\\w+((\\\\s+\\\\w+(\\\\s*=\\\\s*(?:\".*?\"|'.*?'|[\\\\^'\">\\\\s]+))?)+\\\\s*|\\\\s*)\\\\/?>");

	//抽�?�注释,如果你需�?移除HMTL中的注释，�?�以使用如下的表达�?。
	private final static Pattern notes_patter = Pattern.compile("<!--(.*?)-->");

	//查找CSS属性,通过下�?�的表达�?，�?�以�?�索到相匹�?的CSS属性。
	private final static Pattern css_patter = Pattern.compile("^\\\\s*[a-zA-Z\\\\-]+\\\\s*[:]{1}\\\\s[a-zA-Z0-9\\\\s.#]+[;]{1}");

	//�??�?�页�?�超链接,�??�?�html中的超链接。
	private final static Pattern hyperlink_patter = Pattern.compile("(<a\\\\s*(?!.*\\\\brel=)[^>]*)(href=\"https?:\\\\/\\\\/)((?!(?:(?:www\\\\.)?'.implode('|(?:www\\\\.)?', $follow_list).'))[^\"]+)\"((?!.*\\\\brel=)[^>]*)(?:[^>]*)>");

	//�??�?�网页图片,�?�若你想�??�?�网页中所有图片信�?�，�?�以利用下�?�的表达�?。
	private final static Pattern image_patter = Pattern.compile("\\\\< *[img][^\\\\\\\\>]*[src] *= *[\\\\\"\\\\']{0,1}([^\\\\\"\\\\'\\\\ >]*)");

	//�??�?�Color Hex Codes,有时需�?抽�?�网页中的颜色代�?，�?�以使用下�?�的表达�?。
	private final static Pattern color_patter = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

	//文件路径�?�扩展�??校验,验�?windows下文件路径和扩展�??（下�?�的例�?中为.txt文件）
	private final static Pattern route_patter = Pattern.compile("^([a-zA-Z]\\\\:|\\\\\\\\)\\\\\\\\([^\\\\\\\\]+\\\\\\\\)*[^\\\\/:*?\"<>|]+\\\\.txt(l)?$");

	//�??�?�URL链接,下�?�的这个表达�?�?�以筛选出一段文本中的URL
	// ^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?
	//检查URL的�?缀,应用开�?�中很多时候需�?区分请求是HTTPS还是HTTP，通过下�?�的表达�?�?�以�?�出一个url的�?缀然�?��?逻辑判断。
//if (!s.match(/^[a-zA-Z]+:\\/\\//))
//	{
//		s = 'http://' + s;
//	}
	//校验IP-v6地�?�
//	(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))
//校验IP-v4地�?�
//	\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b
//	判断IE的版本
//	^.*MSIE [5-8](?:\\.[0-9]+)?(?!.*Trident\\/[5-9]\\.0).*$
//	校验金�?
//^[0-9]+(.[0-9]{2})?$
//	校验密�?强度
//^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$


    /**
     * 获�?�身份�?�?�所有区域编�?设置
     * @return Hashtable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Hashtable getAreaCodeAll() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙�?�");
        hashtable.put("21", "辽�?");
        hashtable.put("22", "�?�林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江�?");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "�?建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河�?�");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖�?�");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海�?�");
        hashtable.put("50", "�?庆");
        hashtable.put("51", "四�?");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云�?�");
        hashtable.put("54", "西�?");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "�?�海");
        hashtable.put("64", "�?�?");
        hashtable.put("65", "新疆");
        hashtable.put("71", "�?�湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**
     * 根�?�身份�?�返回所在区域信�?�
     * @param idCard
     * @return String
     */
    @SuppressWarnings("unchecked")
    public String getIDCardArea(String idCard) {
        Hashtable<String, String> ht = getAreaCodeAll();
        String area = ht.get(idCard.substring(0, 2));
        return area;
    }


    /**
     * 56�??�?定义
     * @return Hashtable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Hashtable getMinorityAll() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("汉�?", "汉�?");
        hashtable.put("壮�?", "壮�?");
        hashtable.put("满�?", "满�?");
        hashtable.put("回�?", "回�?");
        hashtable.put("苗�?", "苗�?");
        hashtable.put("维�?�尔�?", "维�?�尔�?");
        hashtable.put("土家�?", "土家�?");
        hashtable.put("�?�?", "�?�?");
        hashtable.put("蒙�?��?", "蒙�?��?");
        hashtable.put("�?�?", "�?�?");
        hashtable.put("布�?�?", "布�?�?");
        hashtable.put("侗�?", "侗�?");
        hashtable.put("瑶�?", "瑶�?");
        hashtable.put("�?鲜�?", "�?鲜�?");
        hashtable.put("白�?", "白�?");
        hashtable.put("哈尼�?", "哈尼�?");
        hashtable.put("哈�?�克�?", "哈�?�克�?");
        hashtable.put("黎�?", "黎�?");
        hashtable.put("傣�?", "傣�?");
        hashtable.put("畲�?", "畲�?");
        hashtable.put("傈僳�?", "傈僳�?");
        hashtable.put("仡佬�?", "仡佬�?");
        hashtable.put("东乡�?", "东乡�?");
        hashtable.put("高山�?", "高山�?");
        hashtable.put("拉祜�?", "拉祜�?");
        hashtable.put("水�?", "水�?");
        hashtable.put("佤�?", "佤�?");
        hashtable.put("纳西�?", "纳西�?");
        hashtable.put("羌�?", "羌�?");
        hashtable.put("土�?", "土�?");
        hashtable.put("仫佬�?", "仫佬�?");
        hashtable.put("锡伯�?", "锡伯�?");
        hashtable.put("柯尔克孜�?", "柯尔克孜�?");
        hashtable.put("达斡尔�?", "达斡尔�?");
        hashtable.put("景颇�?", "景颇�?");
        hashtable.put("毛�?��?", "毛�?��?");
        hashtable.put("撒拉�?", "撒拉�?");
        hashtable.put("布朗�?", "布朗�?");
        hashtable.put("塔�?�克�?", "塔�?�克�?");
        hashtable.put("阿昌�?", "阿昌�?");
        hashtable.put("普米�?", "普米�?");
        hashtable.put("鄂温克�?", "鄂温克�?");
        hashtable.put("怒�?", "怒�?");
        hashtable.put("京�?", "京�?");
        hashtable.put("基诺�?", "基诺�?");
        hashtable.put("德昂�?", "德昂�?");
        hashtable.put("�?安�?", "�?安�?");
        hashtable.put("俄罗斯�?", "俄罗斯�?");
        hashtable.put("裕固�?", "裕固�?");
        hashtable.put("乌孜别克�?", "乌孜别克�?");
        hashtable.put("门巴�?", "门巴�?");
        hashtable.put("鄂伦春�?", "鄂伦春�?");
        hashtable.put("独龙�?", "独龙�?");
        hashtable.put("塔塔尔�?", "塔塔尔�?");
        hashtable.put("赫哲�?", "赫哲�?");
        hashtable.put("�?�巴�?", "�?�巴�?");
        return hashtable;
    }
    
	/**
	 * 验�?是�?�为空串 (包括空格�?制表符�?回车符�?�?�行符组�?的字符串 若输入字符串为null或空字符串,返回true)
	 * @param str 验�?字符
	 * @return boolean   
	 */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || str.length() == 0) {
        	 return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
            return false;
            }
        }
        	return true;
    }
 
    
	/**
	 * 是�?��?为空
	 * @param s
	 */
	public static boolean isNotEmpty(String s){
		return s != null && !"".equals(s.trim());
	}
	
	/**
	 * 验�?�?�零正整数
	 * @param str 验�?字符
	 * @return boolean 
	 */
	public static boolean isNotZero(String str) {
		return notZero_pattern.matcher(str).matches();
	}

	
	/**
	 * 验�?是数字
	 * @param str 验�?字符
	 * @return boolean   
	 */
	public static boolean isNumber(String str) {
		return number_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?是大写字�?
	 * @param str 验�?字符
	 * @return boolean   
	 */
	public static boolean isUpChar(String str) {
		return upChar_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?是�?写字�?
	 * @param str 验�?字符
	 * @return boolean   
	 */
	public static boolean isLowChar(String str) {
		return lowChar_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?是英文字�?
	 * @param str 验�?字符
	 * @return boolean   
	 */
	public static boolean isLetter(String str) {
		return letter_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?输入汉字
	 * @param str 验�?字符
	 * @return boolean
	 */
	public static boolean isChinese(String str) {
		return chinese_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?真实姓�??
	 * @param str  验�?字符
	 * @return
	 */
	public static boolean isRealName(String str){
		return realnem_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验�?是�?�是�?�形�?
	 * @param oneCode �?�形�?
	 * @return boolean 
	 */
	public static boolean isOneCode(String oneCode) {
		return onecode_pattern.matcher(oneCode).matches();
	}


	/**
	 * 是�?��?�有特殊符�?�
	 *
	 * @param str 待验�?的字符串
	 * @return 是�?��?�有特殊符�?�
	 */
	public static boolean hasSpecialCharacter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~�?@#￥%……&*（）——+|{}�?】‘；：�?“’。，�?？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	
	/**
	 * 验�?邮箱是�?�正确
	 * @param email  邮箱地�?�
	 * @return boolean   
	 */
	public static boolean isEmail(String email) {
		return email_pattern.matcher(email).matches();
	}
	
	
	
	/**
	 * 验�?手机�?�是�?�正确
	 * @param phone 手机�?��?
	 * @return boolean   
	 */
	public static boolean isPhone(String phone) {
		 return phone_pattern.matcher(phone).matches();
	}

	
	/**
	 * 验�?座机�?��?是�?�正确
	 * @param plane 座机�?��?
	 * @return boolean   
	 */
	public static boolean isPlane(String plane) {
		 return plane_pattern.matcher(plane).matches();
	}
	
	
	
	
	/**
	 * 验�?邮政编�?是�?�正确
	 * @param postalcode 邮政编�?
	 * @return boolean   
	 */
	public static boolean isPostalCode(String postalcode) {
		return postalcode_pattern.matcher(postalcode).matches();
	}
	

	/**
	 * 验�?IP地�?�是�?�正确
	 * @param ipaddress IP地�?�
	 * @return boolean   
	 */
	public static boolean isIpAddress(String ipaddress){
        return ipaddress_pattern.matcher(ipaddress).matches();
	}
	
	
	
	/**
	 * 验�?URL地�?�是�?�正确
	 * @param url 地�?�
	 * @return boolean   
	 */
	public static boolean isURL(String url){
		 return url_pattern.matcher(url).matches();
	}
	
	
	
    
    /**
     * 验�?是�?�是正整数
     * @param str 验�?字符
     * @return boolean
     */
	public static boolean isInteger(String str){
		try{
			Integer.valueOf(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	/**
	 * 验�?是�?�是�?数
	 * @param paramString 验�?字符
	 * @return boolean   
	 */
	public static boolean isPoint(String paramString){
		if(paramString.indexOf(".") > 0){
			if(paramString.substring(paramString.indexOf(".")).length() > 3){
				return false;
			}
		}
		return true;
	}
    
	
	/**
	 * 验�?是�?�银行�?��?�
	 * @param bankNo 银行�?��?�
	 * @return
	 */
	public static boolean isBankNo(String bankNo){
		//替�?�空格
		bankNo = bankNo.replaceAll(" ", "");
		//银行�?��?��?�为12�?数字
		if(12 == bankNo.length()){
			return true;
		}
		//银行�?��?��?�为16-19�?数字
		return bankNo_pattern.matcher(bankNo).matches();
	}

	/**
	 * 验�?身份�?�?��?是�?�正确
	 * @param IDCardNo 身份�?�?��? 
	 * @return boolean   
	 */
	public static boolean isIDCard(String IDCardNo) {
		//记录错误信�?�	
		String errmsg = ""; 
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };
		String Ai = "";
		
		//================ 身份�?�?��?的长度 15�?或18�? ================
		if (IDCardNo.length() != 15 && IDCardNo.length() != 18) {
			errmsg = "身份�?�?��?长度应该为15�?或18�?!";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}
		
		//================ 数字 除最�?�以为都为数字 ================
		if (IDCardNo.length() == 18) {
			Ai = IDCardNo.substring(0, 17);
		} else if (IDCardNo.length() == 15) {
			Ai = IDCardNo.substring(0, 6) + "19" + IDCardNo.substring(6, 15);
		}
		if (isNumber(Ai) == false) {
			errmsg = "身份�?15�?�?��?都应为数字 ; 18�?�?��?除最�?�一�?外，都应为数字";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}
		
		//================ 出生年月是�?�有效 ================
		//年份
		String strYear = Ai.substring(6, 10);
		//月份
		String strMonth = Ai.substring(10, 12);
		//日
		String strDay = Ai.substring(12, 14);
		if (DateUtils.getDateIsTrue(strYear, strMonth, strDay) == false) {
			errmsg = "身份�?生日无效";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errmsg = "身份�?生日�?在有效范围";
				AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
				return false;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errmsg = "身份�?生日�?在有效范围";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg  + e.getMessage());
			return false;
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
			errmsg = "身份�?生日�?在有效范围";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg + e1.getMessage());
			return false;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errmsg = "身份�?月份无效";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errmsg = "身份�?日期无效";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}

		//================ 地区�?时候有效 ================
		Hashtable hashtable = getAreaCodeAll();
		if (hashtable.get(Ai.substring(0, 2)) == null) {
			errmsg = "身份�?地区编�?错误";
			AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
			return false;
		}

		//================ 判断最�?�一�?的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
				TotalmulAiWi = TotalmulAiWi+ Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;
		if (IDCardNo.length() == 18) {
			if (Ai.equals(IDCardNo) == false) {
				errmsg = "身份�?无效，�?是�?�法的身份�?�?��?";
				AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
				return false;
			}
		} else {
				return true;
		}
				return true;
	}

	
	/**
	 * 判断是�?�有特殊字符
	 * @param str 验�?字符
	 * @return boolean   
	 */
	public static boolean isPeculiarStr(String str){
		boolean flag = false;
		String regEx = "[^0-9a-zA-Z\u4e00-\u9fa5]+";
		if(str.length() != (str.replaceAll(regEx, "").length())) {
			flag = true;
		}
			return  flag;
	}
	
	
	/**
	 * 判断是�?�为用户�??账�?�(规则如下：用户�??由下划线或字�?开头，由数字�?字�?�?下划线�?点�?�?�?�组�?的4-32�?字符)
	 * @param username 用户�?? 
	 * @return boolean   
	 */
	public static boolean isUserName(String username) {
		return username_pattern.matcher(username).matches();
	}

	/**
	 * 获�?�字符串中文字符的长度（�?个中文算2个字符）.
	 *
	 * @param str
	 *            指定的字符串
	 * @return 中文字符的长度
	 */
	public static int chineseLength(String str) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获�?�字段值的长度，如果�?�中文字符，则�?个中文字符长度为2，�?�则为1 */
		if (!isEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				/* 获�?�一个字符 */
				String temp = str.substring(i, i + 1);
				/* 判断是�?�为中文字符 */
				if (temp.matches(chinese)) {
					valueLength += 2;
				}
			}
		}
		return valueLength;
	}

    /**
     * �??述：获�?�字符串的长度.
     *
     * @param str
     *            指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获�?�字段值的长度，如果�?�中文字符，则�?个中文字符长度为2，�?�则为1
            for (int i = 0; i < str.length(); i++) {
                // 获�?�一个字符
                String temp = str.substring(i, i + 1);
                // 判断是�?�为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    valueLength += 2;
                } else {
                    // 其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * �??述：获�?�指定长度的字符所在�?置.
     *
     * @param str
     *            指定的字符串
     * @param maxL
     *            �?�?�到的长度（字符长度，中文字符计2个）
     * @return 字符的所在�?置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        // 获�?�字段值的长度，如果�?�中文字符，则�?个中文字符长度为2，�?�则为1
        for (int i = 0; i < str.length(); i++) {
            // 获�?�一个字符
            String temp = str.substring(i, i + 1);
            // 判断是�?�为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * �??述：是�?��?�是字�?和数字.
     *
     * @param str
     *            指定的字符串
     * @return 是�?��?�是字�?和数字:是为true，�?�则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * �??述：是�?�包�?�中文.
     *
     * @param str
     *            指定的字符串
     * @return 是�?�包�?�中文:是为true，�?�则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获�?�字段值的长度，如果�?�中文字符，则�?个中文字符长度为2，�?�则为1
            for (int i = 0; i < str.length(); i++) {
                // 获�?�一个字符
                String temp = str.substring(i, i + 1);
                // 判断是�?�为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * �??述：从输入�?中获得String.
     *
     * @param is
     *            输入�?
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // 最�?�一个\n删除
            if (sb.indexOf("\n") != -1
                && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * �??述：截�?�字符串到指定字节长度.
	 *
     * @param str
	 *            the str
	 * @param length
	 *            指定字节长度
	 * @return 截�?��?�的字符串
	 */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * �??述：截�?�字符串到指定字节长度.
     *
     * @param str
     *            文本
     * @param length
     *            字节长度
     * @param dot
     *            �?略符�?�
     * @return 截�?��?�的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * �??述：截�?�字符串从第一个指定字符.
     *
     * @param str1
     *            原文本
     * @param str2
     *            指定字符
     * @param offset
     *            �??移的索引
     * @return 截�?��?�的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * �??述：获�?�字节长度.
     *
     * @param str
     *            文本
     * @param charset
     *            字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获�?�大�?的�??述.
     *
     * @param size
     *            字节个数
     * @return 大�?的�??述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * �??述：ip地�?�转�?�为10进制数.
     *
     * @param ip
     *            the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
            | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 获�?�UUID
     *
     * @return 32UUID�?写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }


	/**
	 * 手机�?��?，中间4�?星�?�替�?�
	 *
	 * @param phone 手机�?�
	 * @return 星�?�替�?�的手机�?�
	 */
	public static String phoneNoHide(String phone) {
		// 括�?�表示组，被替�?�的部分$n表示第n组的内容
		// 正则表达�?中，替�?�字符串，括�?�的�?�?是分组，在replace()方法中，
		// �?�数二中�?�以使用$n(n为数字)�?��?次引用模�?串中用括�?�定义的字串。
		// "(\d{3})\d{4}(\d{4})", "$1****$2"的这个�?�?就是用括�?�，
		// 分为(�?3个数字)中间4个数字(最�?�4个数字)替�?�为(第一组数值，�?�?�?�?�$1)(中间为*)(第二组数值，�?�?�?�?�$2)
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 银行�?��?�，�?留最�?�4�?，其他星�?�替�?�
	 *
	 * @param cardId �?��?�
	 * @return 星�?�替�?�的银行�?��?�
	 */
	public static String cardIdHide(String cardId) {
		return cardId.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1");
	}

	/**
	 * 身份�?�?�，中间10�?星�?�替�?�
	 *
	 * @param id 身份�?�?�
	 * @return 星�?�替�?�的身份�?�?�
	 */
	public static String idHide(String id) {
		return id.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1** **** ****$2");
	}

	/**
	 * 是�?�为车牌�?�（沪A88888）
	 *
	 * @param vehicleNo 车牌�?�
	 * @return 是�?�为车牌�?�
	 */

	public static boolean checkVehicleNo(String vehicleNo) {
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
		return pattern.matcher(vehicleNo).find();

	}

//	/**
//	 * 匹�?中国邮政编�?
//	 *
//	 * @param postcode 邮政编�?
//	 * @return 验�?�?功返回true，验�?失败返回false
//	 */
//	public static boolean checkPostcode(String postcode) {
//		String regex = "[1-9]\\d{5}";
//		return Pattern.matches(regex, postcode);
//	}


	/**
	 * 判断字符串是�?�为连续数字 45678901等
	 *
	 * @param str 待验�?的字符串
	 * @return 是�?�为连续数字
	 */
	public static boolean isContinuousNum(String str) {
		if (TextUtils.isEmpty(str))
			return false;
		if (!isNumber(str))
			return true;
		int len = str.length();
		for (int i = 0; i < len - 1; i++) {
			char curChar = str.charAt(i);
			char verifyChar = (char) (curChar + 1);
			if (curChar == '9')
				verifyChar = '0';
			char nextChar = str.charAt(i + 1);
			if (nextChar != verifyChar) {
				return false;
			}
		}
		return true;
	}



	/**
	 * 是�?�是纯字�?
	 *
	 * @param str 待验�?的字符串
	 * @return 是�?�是纯字�?
	 */
	public static boolean isAlphaBetaString(String str) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}

		Pattern p = Pattern.compile("^[a-zA-Z]+$");// 从开头到结尾必须全部为字�?或者数字
		Matcher m = p.matcher(str);

		return m.find();
	}

	/**
	 * 判断字符串是�?�为连续字�? xyZaBcd等
	 *
	 * @param str 待验�?的字符串
	 * @return 是�?�为连续字�?
	 */
	public static boolean isContinuousWord(String str) {
		if (TextUtils.isEmpty(str))
			return false;
		if (!isAlphaBetaString(str))
			return true;
		int len = str.length();
		String local = str.toLowerCase();
		for (int i = 0; i < len - 1; i++) {
			char curChar = local.charAt(i);
			char verifyChar = (char) (curChar + 1);
			if (curChar == 'z')
				verifyChar = 'a';
			char nextChar = local.charAt(i + 1);
			if (nextChar != verifyChar) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是�?�是日期
	 * 20120506 共八�?，�?四�?-年，中间两�?-月，最�?�两�?-日
	 *
	 * @param date    待验�?的字符串
	 * @param yearlen yearlength
	 * @return 是�?�是真实的日期
	 */
	public static boolean isRealDate(String date, int yearlen) {
		int len = 4 + yearlen;
		if (date == null || date.length() != len)
			return false;

		if (!date.matches("[0-9]+"))
			return false;

		int year = Integer.parseInt(date.substring(0, yearlen));
		int month = Integer.parseInt(date.substring(yearlen, yearlen + 2));
		int day = Integer.parseInt(date.substring(yearlen + 2, yearlen + 4));

		if (year <= 0)
			return false;
		if (month <= 0 || month > 12)
			return false;
		if (day <= 0 || day > 31)
			return false;

		switch (month) {
			case 4:
			case 6:
			case 9:
			case 11:
				return day > 30 ? false : true;
			case 2:
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
					return day > 29 ? false : true;
				return day > 28 ? false : true;
			default:
				return true;
		}
	}

}
	
	
	
	
	
