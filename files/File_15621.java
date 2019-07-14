/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.apijson;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**通用字符串(String)相关类,为null时返回""
 * @author Lemon
 * @use StringUtil.
 */
public class StringUtil {
	private static final String TAG = "StringUtil";

	public StringUtil() {
	}

	public static final String UTF_8 = "utf-8";

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
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		return object == null ? "" : object.toString();
	}
	/**获�?�string,为null则返回""
	 * @param cs
	 * @return
	 */
	public static String getString(CharSequence cs) {
		return cs == null ? "" : cs.toString();
	}
	/**获�?�string,为null则返回""
	 * @param s
	 * @return
	 */
	public static String getString(String s) {
		return s == null ? "" : s;
	}
	/**获�?�string,为null则返回""
	 * ignoreEmptyItem = false;
	 * split = ","
	 * @param array
	 * @return {@link #getString(Object[], boolean)}
	 */
	public static String getString(Object[] array) {
		return getString(array, false);
	}
	/**获�?�string,为null则返回""
	 * split = ","
	 * @param array
	 * @param ignoreEmptyItem
	 * @return {@link #getString(Object[], boolean)}
	 */
	public static String getString(Object[] array, boolean ignoreEmptyItem) {
		return getString(array, null, ignoreEmptyItem);
	}
	/**获�?�string,为null则返回""
	 * ignoreEmptyItem = false;
	 * @param array
	 * @param split
	 * @return {@link #getString(Object[], String, boolean)}
	 */
	public static String getString(Object[] array, String split) {
		return getString(array, split, false);
	}
	/**获�?�string,为null则返回""
	 * @param array
	 * @param split
	 * @param ignoreEmptyItem
	 * @return
	 */
	public static String getString(Object[] array, String split, boolean ignoreEmptyItem) {
		String s = "";
		if (array != null) {
			if (split == null) {
				split = ",";
			}
			for (int i = 0; i < array.length; i++) {
				if (ignoreEmptyItem && isEmpty(array[i], true)) {
					continue;
				}
				s += ((i > 0 ? split : "") + array[i]);
			}
		}
		return getString(s);
	}

	//获�?�string,为null时返回"" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//获�?�去掉�?�?�空格�?�的string<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
		return getString(s).replaceAll("\\s", "");
	}

	//获�?�去掉所有空格�?�的string >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//获�?�string的长度<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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


	//判断字符是�?�为空 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**判断字符是�?�为空
	 * @param object
	 * @param trim
	 * @return
	 */
	public static boolean isEmpty(Object object, boolean trim) {
		return isEmpty(getString(object), trim);
	}
	/**判断字符是�?�为空
	 * @param cs
	 * @param trim
	 * @return
	 */
	public static boolean isEmpty(CharSequence cs, boolean trim) {
		return isEmpty(getString(cs), trim);
	}
	/**判断字符是�?�为空
	 * @param s
	 * @param trim
	 * @return
	 */
	public static boolean isEmpty(String s, boolean trim) {
		//		Log.i(TAG, "getTrimedString   s = " + s);
		if (s == null) {
			return true;
		}
		if (trim) {
			s = s.trim();
		}
		if (s.isEmpty()) {
			return true;
		}

		currentString = s;

		return false;
	}

	//判断字符是�?�为空 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//判断字符是�?��?�空 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
		return ! isEmpty(s, trim);
	}

	//判断字符是�?��?�空 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//判断字符类型 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final Pattern PATTERN_NUMBER;
	public static final Pattern PATTERN_PHONE;
	public static final Pattern PATTERN_EMAIL;
	public static final Pattern PATTERN_ID_CARD;
	public static final Pattern PATTERN_ALPHA;
	public static final Pattern PATTERN_PASSWORD; //TODO
	public static final Pattern PATTERN_NAME;
	public static final Pattern PATTERN_ALPHA_BIG;
	public static final Pattern PATTERN_ALPHA_SMALL;
	static {
		PATTERN_NUMBER = Pattern.compile("^[0-9]+$");
		PATTERN_ALPHA = Pattern.compile("^[a-zA-Z]+$");
		PATTERN_ALPHA_BIG = Pattern.compile("^[A-Z]+$");
		PATTERN_ALPHA_SMALL = Pattern.compile("^[a-z]+$");
		PATTERN_NAME = Pattern.compile("^[0-9a-zA-Z_]+$");//已用55个中英字符测试通过
		PATTERN_PHONE = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9])|(17[0-9]))\\d{8}$");
		PATTERN_EMAIL = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		PATTERN_ID_CARD = Pattern.compile("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)");
		PATTERN_PASSWORD = Pattern.compile("^[0-9a-zA-Z]+$");
	}

	/**判断手机格�?是�?�正确
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (isNotEmpty(phone, true) == false) {
			return false;
		}

		currentString = phone;
		return PATTERN_PHONE.matcher(phone).matches();
	}
	/**判断手机格�?是�?�正确
	 * @param s
	 * @return
	 */
	public static boolean isPassword(String s) {
		return getLength(s, false) >= 6 && PATTERN_PASSWORD.matcher(s).matches();
	}
	/**判断是�?�全是数字密�?
	 * @param s
	 * @return
	 */
	public static boolean isNumberPassword(String s) {
		return getLength(s, false) == 6 && isNumer(s);
	}
	/**判断email格�?是�?�正确
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isNotEmpty(email, true) == false) {
			return false;
		}

		currentString = email;
		return PATTERN_EMAIL.matcher(email).matches();
	}


	/**判断是�?�全是验�?�?
	 * @param s
	 * @return
	 */
	public static boolean isVerify(String s) {
		return getLength(s, false) >= 4 && isNumer(s);
	}
	/**判断是�?�全是数字
	 * @param s
	 * @return
	 */
	public static boolean isNumer(String s) {
		if (isNotEmpty(s, true) == false) {
			return false;
		}

		currentString = s;
		return PATTERN_NUMBER.matcher(s).matches();
	}
	/**判断是�?�全是字�?
	 * @param s
	 * @return
	 */
	public static boolean isAlpha(String s) {
		if (isEmpty(s, true)) {
			return false;
		}

		currentString = s;
		return PATTERN_ALPHA.matcher(s).matches();
	}
	/**判断是�?�全是数字或字�?
	 * @param s
	 * @return
	 */
	public static boolean isNumberOrAlpha(String s) {
		return isNumer(s) || isAlpha(s);
	}

	/**判断是�?�为代�?�??称，�?�能包�?�字�?，数字或下划线
	 * @param s
	 * @return
	 */
	public static boolean isName(String s) {
		return s != null && PATTERN_NAME.matcher(s).matches();
	}
	/**判断是�?�为首字�?大写的代�?�??称
	 * @param s
	 * @return
	 */
	public static boolean isBigName(String s) {
		s = getString(s);
		if (s.isEmpty() || PATTERN_ALPHA_BIG.matcher(s.substring(0, 1)).matches() == false) {
			return false;
		}
		return s.length() <= 1 ? true : isName(s.substring(1));
	}
	/**判断是�?�为首字�?�?写的代�?�??称
	 * @param s
	 * @return
	 */
	public static boolean isSmallName(String s) {
		s = getString(s);
		if (s.isEmpty() || PATTERN_ALPHA_SMALL.matcher(s.substring(0, 1)).matches() == false) {
			return false;
		}
		return s.length() <= 1 ? true : isName(s.substring(1));
	}


	/**判断字符类型是�?�是身份�?�?�
	 * @param number
	 * @return
	 */
	public static boolean isIDCard(String number) {
		if (isNumberOrAlpha(number) == false) {
			return false;
		}
		number = getString(number);
		if (number.length() == 15) {
			Log.i(TAG, "isIDCard number.length() == 15 old IDCard");
			currentString = number;
			return true;
		}
		if (number.length() == 18) {
			currentString = number;
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

	public static final String SEPARATOR = "/";
	/**判断是�?�为路径
	 * @param path
	 * @return
	 */
	public static boolean isPath(String path) {
		return StringUtil.isNotEmpty(path, true) && path.contains(SEPARATOR)
				&& path.contains(SEPARATOR + SEPARATOR) == false && path.endsWith(SEPARATOR) == false;
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
		return getNumber(s, false);
	}
	/**去掉string内所有�?�数字类型字符
	 * @param s
	 * @param onlyStart 中间有�?�数字时�?�获�?��?�?�的数字
	 * @return
	 */
	public static String getNumber(String s, boolean onlyStart) {
		if (isNotEmpty(s, true) == false) {
			return "";
		}

		String numberString = "";
		String single;
		for (int i = 0; i < s.length(); i++) {
			single = s.substring(i, i + 1);
			if (isNumer(single)) {
				numberString += single;
			} else {
				if (onlyStart) {
					return numberString;
				}
			}
		}

		return numberString;
	}

	//�??�?�特殊字符>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//校正（自动补全等）字符串<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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


	/**分割路径
	 * @param path
	 * @return
	 */
	public static String[] splitPath(String path) {
		if (StringUtil.isNotEmpty(path, true) == false) {
			return null;
		}
		return isPath(path) ? split(path, SEPARATOR) : new String[] {path};
	}
	/**将s分割�?String[]
	 * @param s
	 * @return
	 */
	public static String[] split(String s) {
		return split(s, null);
	}
	/**将s用split分割�?String[]
	 * trim = true;
	 * @param s
	 * @param split
	 * @return
	 */
	public static String[] split(String s, String split) {
		return split(s, split, true);
	}
	/**将s用split分割�?String[]
	 * @param s
	 * @param split
	 * @param trim 去掉�?�?�两端的split
	 * @return
	 */
	public static String[] split(String s, String split, boolean trim) {
		s = getString(s);
		if (s.isEmpty()) {
			return null;
		}
		if (isNotEmpty(split, false) == false) {
			split = ",";
		}
		if (trim) {
			while (s.startsWith(split)) {
				s = s.substring(split.length());
			}
			while (s.endsWith(split)) {
				s = s.substring(0, s.length() - split.length());
			}
		}
		return s.contains(split) ? s.split(split) : new String[]{s};
	}

	/**
	 * @param key
	 * @param suffix
	 * @return key + suffix，第一个字�?�?写
	 */
	public static String addSuffix(String key, String suffix) {
		key = getNoBlankString(key);
		if (key.isEmpty()) {
			return firstCase(suffix);
		}
		return firstCase(key) + firstCase(suffix, true);
	}
	/**
	 * @param key
	 */
	public static String firstCase(String key) {
		return firstCase(key, false);
	}
	/**
	 * @param key
	 * @param upper
	 * @return
	 */
	public static String firstCase(String key, boolean upper) {
		key = getString(key);
		if (key.isEmpty()) {
			return "";
		}

		String first = key.substring(0, 1);
		key = (upper ? first.toUpperCase() : first.toLowerCase()) + key.substring(1, key.length());

		return key;
	}

	/**全部大写
	 * @param s
	 * @return
	 */
	public static String toUpperCase(String s) {
		return toUpperCase(s, false);
	}
	/**全部大写
	 * @param s
	 * @param trim
	 * @return
	 */
	public static String toUpperCase(String s, boolean trim) {
		s = trim ? getTrimedString(s) : getString(s);
		return s.toUpperCase();
	}
	/**全部�?写
	 * @param s
	 * @return
	 */
	public static String toLowerCase(String s) {
		return toLowerCase(s, false);
	}
	/**全部�?写
	 * @param s
	 * @return
	 */
	public static String toLowerCase(String s, boolean trim) {
		s = trim ? getTrimedString(s) : getString(s);
		return s.toLowerCase();
	}

	public static String concat(String left, String right) {
		return concat(left, right, null);
	}
	public static String concat(String left, String right, String split) {
		return concat(left, right, split, true);
	}
	public static String concat(String left, String right, boolean trim) {
		return concat(left, right, null, trim);
	}
	public static String concat(String left, String right, String split, boolean trim) {
		if (isEmpty(left, trim)) {
			return right;
		}
		if (isEmpty(right, trim)) {
			return left;
		}

		if (split == null) {
			split = ",";
		}
		return left + split + right;
	}

	//校正（自动补全等）字符串>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
