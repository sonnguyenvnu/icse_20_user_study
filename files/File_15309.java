/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.library.util;

import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**通用字符串(String)相关类,为null时返回""
 * @author Lemon
 * @use StringUtil.xxxMethod(...);
 */
public class StringUtil {
	private static final String TAG = "StringUtil";

	public StringUtil() {
	}

	public static final String EMPTY = "无";
	public static final String UNKNOWN = "未知";
	public static final String UNLIMITED = "�?�?";

	public static final String I = "我";
	public static final String YOU = "你";
	public static final String HE = "他";
	public static final String SHE = "她";
	public static final String IT = "它";

	public static final String MALE = "男";
	public static final String FEMALE = "女";

	public static final String TODO = "未完�?";
	public static final String DONE = "已完�?";

	public static final String FAIL = "失败";
	public static final String SUCCESS = "�?功";

	public static final String SUNDAY = "日";
	public static final String MONDAY = "一";
	public static final String TUESDAY = "二";
	public static final String WEDNESDAY = "三";
	public static final String THURSDAY = "四";
	public static final String FRIDAY = "五";
	public static final String SATURDAY = "六";

	public static final String YUAN = "元";


	private static String currentString = "";
	/**获�?�刚传入处�?��?�的string
	 * @must 上个影�?currentString的方法 和 这个方法都应该在�?�一线程中，�?�则返回值�?�能�?对
	 * @return
	 */
	public static String getCurrentString() {
		return currentString == null ? "" : currentString;
	}

	//获�?�string,为null时返回"" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**获�?�string,为null则返回""
	 * @param tv
	 * @return
	 */
	public static String getString(TextView tv) {
		if (tv == null || tv.getText() == null) {
			return "";
		}
		return getString(tv.getText().toString());
	}
	/**获�?�string,为null则返回""
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		return object == null ? "" : getString(String.valueOf(object));
	}
	/**获�?�string,为null则返回""
	 * @param cs
	 * @return
	 */
	public static String getString(CharSequence cs) {
		return cs == null ? "" : getString(cs.toString());
	}
	/**获�?�string,为null则返回""
	 * @param s
	 * @return
	 */
	public static String getString(String s) {
		return s == null ? "" : s;
	}

	//获�?�string,为null时返回"" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//获�?�去掉�?�?�空格�?�的string<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**获�?�去掉�?�?�空格�?�的string,为null则返回""
	 * @param tv
	 * @return
	 */
	public static String getTrimedString(TextView tv) {
		return getTrimedString(getString(tv));
	}
	/**获�?�去掉�?�?�空格�?�的string,为null则返回""
	 * @param object
	 * @return
	 */
	public static String getTrimedString(Object object) {
		return getTrimedString(getString(object));
	}
	/**获�?�去掉�?�?�空格�?�的string,为null则返回""
	 * @param cs
	 * @return
	 */
	public static String getTrimedString(CharSequence cs) {
		return getTrimedString(getString(cs));
	}
	/**获�?�去掉�?�?�空格�?�的string,为null则返回""
	 * @param s
	 * @return
	 */
	public static String getTrimedString(String s) {
		return getString(s).trim();
	}

	//获�?�去掉�?�?�空格�?�的string>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//获�?�去掉所有空格�?�的string <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**获�?�去掉所有空格�?�的string,为null则返回""
	 * @param tv
	 * @return
	 */
	public static String getNoBlankString(TextView tv) {
		return getNoBlankString(getString(tv));
	}
	/**获�?�去掉所有空格�?�的string,为null则返回""
	 * @param object
	 * @return
	 */
	public static String getNoBlankString(Object object) {
		return getNoBlankString(getString(object));
	}
	/**获�?�去掉所有空格�?�的string,为null则返回""
	 * @param cs
	 * @return
	 */
	public static String getNoBlankString(CharSequence cs) {
		return getNoBlankString(getString(cs));
	}
	/**获�?�去掉所有空格�?�的string,为null则返回""
	 * @param s
	 * @return
	 */
	public static String getNoBlankString(String s) {
		return getString(s).replaceAll(" ", "");
	}

	//获�?�去掉所有空格�?�的string >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//获�?�string的长度<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**获�?�string的长度,为null则返回0
	 * @param tv
	 * @param trim
	 * @return
	 */
	public static int getLength(TextView tv, boolean trim) {
		return getLength(getString(tv), trim);
	}
	/**获�?�string的长度,为null则返回0
	 * @param object
	 * @param trim
	 * @return
	 */
	public static int getLength(Object object, boolean trim) {
		return getLength(getString(object), trim);
	}
	/**获�?�string的长度,为null则返回0
	 * @param cs
	 * @param trim
	 * @return
	 */
	public static int getLength(CharSequence cs, boolean trim) {
		return getLength(getString(cs), trim);
	}
	/**获�?�string的长度,为null则返回0
	 * @param s
	 * @param trim
	 * @return
	 */
	public static int getLength(String s, boolean trim) {
		s = trim ? getTrimedString(s) : s;
		return getString(s).length();
	}

	//获�?�string的长度>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//判断字符是�?��?�空 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**判断字符是�?��?�空
	 * @param tv
	 * @param trim
	 * @return
	 */
	public static boolean isNotEmpty(TextView tv, boolean trim) {
		return isNotEmpty(getString(tv), trim);
	}
	/**判断字符是�?��?�空
	 * @param object
	 * @param trim
	 * @return
	 */
	public static boolean isNotEmpty(Object object, boolean trim) {
		return isNotEmpty(getString(object), trim);
	}
	/**判断字符是�?��?�空
	 * @param cs
	 * @param trim
	 * @return
	 */
	public static boolean isNotEmpty(CharSequence cs, boolean trim) {
		return isNotEmpty(getString(cs), trim);
	}
	/**判断字符是�?��?�空
	 * @param s
	 * @param trim
	 * @return
	 */
	public static boolean isNotEmpty(String s, boolean trim) {
		//		Log.i(TAG, "getTrimedString   s = " + s);
		if (s == null) {
			return false;
		}
		if (trim) {
			s = s.trim();
		}
		if (s.length() <= 0) {
			return false;
		}

		currentString = s;

		return true;
	}

	//判断字符是�?��?�空 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//判断字符类型 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//判断手机格�?是�?�正确
	public static boolean isPhone(String phone) {
		if (isNotEmpty(phone, true) == false) {
			return false;
		}

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9])|(17[0-9]))\\d{8}$");

		currentString = phone;

		return p.matcher(phone).matches();
	}
	//判断email格�?是�?�正确
	public static boolean isEmail(String email) {
		if (isNotEmpty(email, true) == false) {
			return false;
		}

		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);

		currentString = email;

		return p.matcher(email).matches();
	}
	//判断是�?�全是数字
	public static boolean isNumer(String number) {
		if (isNotEmpty(number, true) == false) {
			return false;
		}

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(number);
		if (!isNum.matches()) {
			return false;
		}

		currentString = number;

		return true;
	}
	/**判断字符类型是�?�是�?��?或字�?
	 * @param inputed
	 * @return
	 */
	public static boolean isNumberOrAlpha(String inputed) {
		if (inputed == null) {
			Log.e(TAG, "isNumberOrAlpha  inputed == null >> return false;");
			return false;
		}
		Pattern pNumber = Pattern.compile("[0-9]*");
		Matcher mNumber;
		Pattern pAlpha = Pattern.compile("[a-zA-Z]");
		Matcher mAlpha;
		for (int i = 0; i < inputed.length(); i++) {
			mNumber = pNumber.matcher(inputed.substring(i, i+1));
			mAlpha = pAlpha.matcher(inputed.substring(i, i+1));
			if(! mNumber.matches() && ! mAlpha.matches()){
				return false;
			}
		}

		currentString = inputed;
		return true;
	}

	/**判断字符类型是�?�是身份�?�?�
	 * @param idCard
	 * @return
	 */
	public static boolean isIDCard(String idCard) {
		if (isNumberOrAlpha(idCard) == false) {
			return false;
		}
		idCard = getString(idCard);
		if (idCard.length() == 15) {
			Log.w(TAG, "isIDCard idCard.length() == 15 old IDCard");
			currentString = idCard;
			return true;
		}
		if (idCard.length() == 18) {
			currentString = idCard;
			return true;
		}

		return false;
	}

	public static final String HTTP = "http";
	public static final String URL_PREFIX = "http://";
	public static final String URL_PREFIXs = "https://";
	public static final String URL_STAFFIX = URL_PREFIX;
	public static final String URL_STAFFIXs = URL_PREFIXs;
	/**判断字符类型是�?�是网�?�
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		if (isNotEmpty(url, true) == false) {
			return false;
		} else if (! url.startsWith(URL_PREFIX) && ! url.startsWith(URL_PREFIXs)) {
			return false;
		}

		currentString = url;
		return true;
	}

	public static final String FILE_PATH_PREFIX = "file://";
	/**判断文件路径是�?�存在
	 * @param path
	 * @return
	 */
	public static boolean isFilePathExist(String path) {
		return StringUtil.isFilePath(path) && new File(path).exists();
	}
	/**判断字符类型是�?�是路径
	 * @param path
	 * @return
	 */
	public static boolean isFilePath(String path) {
		if (isNotEmpty(path, true) == false) {
			return false;
		}

		if (! path.contains(".") || path.endsWith(".")) {
			return false;
		}

		currentString = path;

		return true;
	}

	//判断字符类型 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//�??�?�特殊字符<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**去掉string内所有�?�数字类型字符
	 * @param tv
	 * @return
	 */
	public static String getNumber(TextView tv) {
		return getNumber(getString(tv));
	}
	/**去掉string内所有�?�数字类型字符
	 * @param object
	 * @return
	 */
	public static String getNumber(Object object) {
		return getNumber(getString(object));
	}
	/**去掉string内所有�?�数字类型字符
	 * @param cs
	 * @return
	 */
	public static String getNumber(CharSequence cs) {
		return getNumber(getString(cs));
	}
	/**去掉string内所有�?�数字类型字符
	 * @param s
	 * @return
	 */
	public static String getNumber(String s) {
		if (isNotEmpty(s, true) == false) {
			return "";
		}

		String numberString = "";
		String single;
		for (int i = 0; i < s.length(); i++) {
			single = s.substring(i, i + 1);
			if (isNumer(single)) {
				numberString += single;
			}
		}

		return numberString;
	}

	//�??�?�特殊字符>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//校正（自动补全等）字符串<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**获�?�网�?�，自动补全
	 * @param tv
	 * @return
	 */
	public static String getCorrectUrl(TextView tv) {
		return getCorrectUrl(getString(tv));
	}
	/**获�?�网�?�，自动补全
	 * @param url
	 * @return
	 */
	public static String getCorrectUrl(String url) {
		Log.i(TAG, "getCorrectUrl : \n" + url);
		if (isNotEmpty(url, true) == false) {
			return "";
		}

//		if (! url.endsWith("/") && ! url.endsWith(".html")) {
//			url = url + "/";
//		}

		if (isUrl(url) == false) {
			return URL_PREFIX + url;
		}
		return url;
	}

	/**获�?�去掉所有 空格 �?"-" �?"+86" �?�的phone
	 * @param tv
	 * @return
	 */
	public static String getCorrectPhone(TextView tv) {
		return getCorrectPhone(getString(tv));
	}
	/**获�?�去掉所有 空格 �?"-" �?"+86" �?�的phone
	 * @param phone
	 * @return
	 */
	public static String getCorrectPhone(String phone) {
		if (isNotEmpty(phone, true) == false) {
			return "";
		}

		phone = getNoBlankString(phone);
		phone = phone.replaceAll("-", "");
		if (phone.startsWith("+86")) {
			phone = phone.substring(3);
		}
		return phone;
	}


	/**获�?�邮箱，自动补全
	 * @param tv
	 * @return
	 */
	public static String getCorrectEmail(TextView tv) {
		return getCorrectEmail(getString(tv));
	}
	/**获�?�邮箱，自动补全
	 * @param email
	 * @return
	 */
	public static String getCorrectEmail(String email) {
		if (isNotEmpty(email, true) == false) {
			return "";
		}

		email = getNoBlankString(email);
		if (isEmail(email) == false && ! email.endsWith(".com")) {
			email += ".com";
		}

		return email;
	}


	public static final int PRICE_FORMAT_DEFAULT = 0;
	public static final int PRICE_FORMAT_PREFIX = 1;
	public static final int PRICE_FORMAT_SUFFIX = 2;
	public static final int PRICE_FORMAT_PREFIX_WITH_BLANK = 3;
	public static final int PRICE_FORMAT_SUFFIX_WITH_BLANK = 4;
	public static final String[] PRICE_FORMATS = {
			"", "￥", "元", "￥ ", " 元"
	};

	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @return
	 */
	public static String getPrice(String price) {
		return getPrice(price, PRICE_FORMAT_DEFAULT);
	}
	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @param formatType 添加�?��?（元）
	 * @return
	 */
	public static String getPrice(String price, int formatType) {
		if (isNotEmpty(price, true) == false) {
			return getPrice(0, formatType);
		}

		//�?�独写到getCorrectPrice? <<<<<<<<<<<<<<<<<<<<<<
		String correctPrice = "";
		String s;
		for (int i = 0; i < price.length(); i++) {
			s = price.substring(i, i + 1);
			if (".".equals(s) || isNumer(s)) {
				correctPrice += s;
			}
		}
		//�?�独写到getCorrectPrice? >>>>>>>>>>>>>>>>>>>>>>

		Log.i(TAG, "getPrice  <<<<<<<<<<<<<<<<<< correctPrice =  " + correctPrice);
		if (correctPrice.contains(".")) {
//			if (correctPrice.startsWith(".")) {
//				correctPrice = 0 + correctPrice;
//			}
			if (correctPrice.endsWith(".")) {
				correctPrice = correctPrice.replaceAll(".", "");
			}
		}

		Log.i(TAG, "getPrice correctPrice =  " + correctPrice + " >>>>>>>>>>>>>>>>");
		return isNotEmpty(correctPrice, true) ? getPrice(new BigDecimal(0 + correctPrice), formatType) : getPrice(0, formatType);
	}
	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @return
	 */
	public static String getPrice(BigDecimal price) {
		return getPrice(price, PRICE_FORMAT_DEFAULT);
	}
	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @return
	 */
	public static String getPrice(double price) {
		return getPrice(price, PRICE_FORMAT_DEFAULT);
	}
	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @param formatType 添加�?��?（元）
	 * @return
	 */
	public static String getPrice(BigDecimal price, int formatType) {
		return getPrice(price == null ? 0 : price.doubleValue(), formatType);
	}
	/**获�?�价格，�?留两�?�?数
	 * @param price
	 * @param formatType 添加�?��?（元）
	 * @return
	 */
	public static String getPrice(double price, int formatType) {
		String s = new DecimalFormat("#########0.00").format(price);
		switch (formatType) {
			case PRICE_FORMAT_PREFIX:
				return PRICE_FORMATS[PRICE_FORMAT_PREFIX] + s;
			case PRICE_FORMAT_SUFFIX:
				return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX];
			case PRICE_FORMAT_PREFIX_WITH_BLANK:
				return PRICE_FORMATS[PRICE_FORMAT_PREFIX_WITH_BLANK] + s;
			case PRICE_FORMAT_SUFFIX_WITH_BLANK:
				return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX_WITH_BLANK];
			default:
				return s;
		}
	}


	//校正（自动补全等）字符串>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
