package org.jeecgframework.core.util;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * java汉字转拼音�?作工具类
 */

public class PinyinUtil {

	/**
	 * 
	 * 将字符串转�?��?拼音数组
	 * 
	 * 
	 * 
	 * @param src
	 * 
	 * @return
	 */

	public static String[] stringToPinyin(String src) {

		return stringToPinyin(src, false, null);

	}

	/**
	 * 
	 * 将字符串转�?��?拼音数组
	 * 
	 * 
	 * 
	 * @param src
	 * 
	 * @return
	 */

	public static String[] stringToPinyin(String src, String separator) {

		return stringToPinyin(src, true, separator);

	}

	/**
	 * 
	 * 将字符串转�?��?拼音数组
	 * 
	 * 
	 * 
	 * @param src
	 * 
	 * @param isPolyphone
	 * 
	 *            是�?�查出多音字的所有拼音
	 * 
	 * @param separator
	 * 
	 *            多音字拼音之间的分隔符
	 * 
	 * @return
	 */

	public static String[] stringToPinyin(String src, boolean isPolyphone,

	String separator) {

		// 判断字符串是�?�为空

		if ("".equals(src) || null == src) {

			return null;

		}

		char[] srcChar = src.toCharArray();

		int srcCount = srcChar.length;

		String[] srcStr = new String[srcCount];

		for (int i = 0; i < srcCount; i++) {

			srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);

		}

		return srcStr;

	}

	/**
	 * 
	 * 将�?�个字符转�?��?拼音
	 * 
	 * 
	 * 
	 * @param src
	 * 
	 * @return
	 */

	public static String charToPinyin(char src, boolean isPolyphone,

	String separator) {

		// 创建汉语拼音处�?�类

		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

		// 输出设置，大�?写，音标方�?

		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuffer tempPinying = new StringBuffer();

		// 如果是中文

		if (src > 128) {

			try {

				// 转�?�得出结果

				String[] strs = PinyinHelper.toHanyuPinyinStringArray(src,

				defaultFormat);

				// 是�?�查出多音字，默认是查出多音字的第一个字符

				if (isPolyphone && null != separator) {

					for (int i = 0; i < strs.length; i++) {

						tempPinying.append(strs[i]);

						if (strs.length != (i + 1)) {

							// 多音字之间用特殊符�?�间隔起�?�

							tempPinying.append(separator);

						}

					}

				} else {

					tempPinying.append(strs[0]);

				}

			} catch (BadHanyuPinyinOutputFormatCombination e) {

				e.printStackTrace();

			}

		} else {

			tempPinying.append(src);

		}

		return tempPinying.toString();

	}

	public static String hanziToPinyin(String hanzi) {

		return hanziToPinyin(hanzi, " ");

	}

	/**
	 * 
	 * 将汉字转�?��?拼音
	 * 
	 * @param hanzi
	 * 
	 * @param separator
	 * 
	 * @return
	 */

	@SuppressWarnings("deprecation")
	public static String hanziToPinyin(String hanzi, String separator) {

		// 创建汉语拼音处�?�类

		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

		// 输出设置，大�?写，音标方�?

		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		String pinyingStr = "";

		try {

			pinyingStr = PinyinHelper.toHanYuPinyinString(hanzi, defaultFormat,
					separator,false);


		} catch (BadHanyuPinyinOutputFormatCombination e) {

			e.printStackTrace();

		}

		return pinyingStr;

	}

	/**
	 * 
	 * 将字符串数组转�?��?字符串
	 * 
	 * @param str
	 * 
	 * @param separator
	 *            �?�个字符串之间的分隔符
	 * 
	 * @return
	 */

	public static String stringArrayToString(String[] str, String separator) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length; i++) {

			sb.append(str[i]);

			if (str.length != (i + 1)) {

				sb.append(separator);

			}

		}

		return sb.toString();

	}

	/**
	 * 
	 * 简�?�的将�?�个字符数组之间连接起�?�
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static String stringArrayToString(String[] str) {

		return stringArrayToString(str, "");

	}

	/**
	 * 
	 * 将字符数组转�?��?字符串
	 * 
	 * @param str
	 * 
	 * @param separator
	 *            �?�个字符串之间的分隔符
	 * 
	 * @return
	 */

	public static String charArrayToString(char[] ch, String separator) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < ch.length; i++) {

			sb.append(ch[i]);

			if (ch.length != (i + 1)) {

				sb.append(separator);

			}

		}

		return sb.toString();

	}

	/**
	 * 
	 * 将字符数组转�?��?字符串
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static String charArrayToString(char[] ch) {

		return charArrayToString(ch, " ");

	}

	/**
	 * 
	 * �?�汉字的首字�?
	 * 
	 * @param src
	 * 
	 * @param isCapital
	 *            是�?�是大写
	 * 
	 * @return
	 */

	public static char[] getHeadByChar(char src, boolean isCapital) {

		// 如果�?是汉字直接返回

		if (src <= 128) {

			return new char[] { src };

		}

		// 获�?�所有的拼音

		String[] pinyingStr = PinyinHelper.toHanyuPinyinStringArray(src);

		// 创建返回对象

		int polyphoneSize = pinyingStr.length;

		char[] headChars = new char[polyphoneSize];

		int i = 0;

		// 截�?�首字符

		for (String s : pinyingStr) {

			char headChar = s.charAt(0);

			// 首字�?是�?�大写，默认是�?写

			if (isCapital) {

				headChars[i] = Character.toUpperCase(headChar);

			} else {

				headChars[i] = headChar;

			}

			i++;

		}

		return headChars;

	}

	/**
	 * 
	 * �?�汉字的首字�?(默认是大写)
	 * 
	 * @param src
	 * 
	 * @return
	 */

	public static char[] getHeadByChar(char src) {

		return getHeadByChar(src, true);

	}

	/**
	 * 
	 * 查找字符串首字�?
	 * 
	 * @param src
	 * 
	 * @return
	 */

	public static String[] getHeadByString(String src) {

		return getHeadByString(src, true);

	}

	/**
	 * 
	 * 查找字符串首字�?
	 * 
	 * @param src
	 * 
	 * @param isCapital
	 *            是�?�大写
	 * 
	 * @return
	 */

	public static String[] getHeadByString(String src, boolean isCapital) {

		return getHeadByString(src, isCapital, null);

	}

	/**
	 * 
	 * 查找字符串首字�?
	 * 
	 * @param src
	 * 
	 * @param isCapital
	 *            是�?�大写
	 * 
	 * @param separator
	 *            分隔符
	 * 
	 * @return
	 */

	public static String[] getHeadByString(String src, boolean isCapital,
			String separator) {

		char[] chars = src.toCharArray();

		String[] headString = new String[chars.length];

		int i = 0;

		for (char ch : chars) {

			char[] chs = getHeadByChar(ch, isCapital);

			StringBuffer sb = new StringBuffer();

			if (null != separator) {

				int j = 1;

				for (char ch1 : chs) {

					sb.append(ch1);

					if (j != chs.length) {

						sb.append(separator);

					}

					j++;

				}

			} else {

				sb.append(chs[0]);

			}

			headString[i] = sb.toString();

			i++;

		}

		return headString;

	}

	/**
	 * 将汉字转�?�为全拼
	 * 
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		// org.jeecgframework.core.util.LogUtil.info(t1.length);
		String[] t2 = new String[t1.length];
		// org.jeecgframework.core.util.LogUtil.info(t2.length);
		// 设置汉字拼音输出的格�?
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断能�?�为汉字字符
				// org.jeecgframework.core.util.LogUtil.info(t1[i]);
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几�?全拼都存到t2数组中
					t4 += t2[0];// �?�出该汉字全拼的第一�?读音并连接到字符串t4�?�
				} else {
					// 如果�?是汉字字符，间接�?�出字符并连接到字符串t4�?�
					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * �??�?��?个汉字的首字�?
	 * 
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// �??�?�汉字的首字�?
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 将字符串转�?��?ASCII�?
	 * 
	 * @param cnStr
	 * @return String
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		// 将字符串转�?��?字节�?列
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// org.jeecgframework.core.util.LogUtil.info(Integer.toHexString(bGBK[i] & 0xff));
			// 将�?个字符转�?��?ASCII�?
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	/**
	 * 汉字转�?��?汉语拼音首字�?，英文字符�?�?�
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 汉字转�?��?汉语拼音，英文字符�?�?�
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 字符串集�?�转�?�字符串(逗�?�分隔)
	 * 
	 * @author wyh
	 * @param stringSet
	 * @return
	 */
	public static String makeStringByStringSet(Set<String> stringSet) {
		StringBuilder str = new StringBuilder();
		int i = 0;
		for (String s : stringSet) {
			if (i == stringSet.size() - 1) {
				str.append(s);
			} else {
				str.append(s + ",");
			}
			i++;
		}
		return str.toString().toLowerCase();
	}

	/**
	 * 获�?�拼音集�?�
	 * 
	 * @author wyh
	 * @param src
	 * @return Set<String>
	 */
	public static Set<String> getPinyin(String src) {
		if (src != null && !src.trim().equalsIgnoreCase("")) {
			char[] srcChar;
			srcChar = src.toCharArray();
			// 汉语拼音格�?输出类
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

			// 输出设置，大�?写，音标方�?等
			hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

			String[][] temp = new String[src.length()][];
			for (int i = 0; i < srcChar.length; i++) {
				char c = srcChar[i];
				// 是中文或者a-z或者A-Z转�?�拼音(我的需求，是�?留中文或者a-z或者A-Z)
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
					try {
						temp[i] = PinyinHelper.toHanyuPinyinStringArray(
								srcChar[i], hanYuPinOutputFormat);
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else if (((int) c >= 65 && (int) c <= 90)
						|| ((int) c >= 97 && (int) c <= 122)) {
					temp[i] = new String[] { String.valueOf(srcChar[i]) };
				} else {
					temp[i] = new String[] { "" };
				}
			}
			String[] pingyinArray = Exchange(temp);
			Set<String> pinyinSet = new HashSet<String>();
			for (int i = 0; i < pingyinArray.length; i++) {
				pinyinSet.add(pingyinArray[i]);
			}
			return pinyinSet;
		}
		return null;
	}

	/**
	 * 递归
	 * 
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	public static String[] Exchange(String[][] strJaggedArray) {
		String[][] temp = DoExchange(strJaggedArray);
		return temp[0];
	}

	/**
	 * 递归
	 * 
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	private static String[][] DoExchange(String[][] strJaggedArray) {
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];
			int Index = 0;
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < len2; j++) {
					temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
					Index++;
				}
			}
			String[][] newArray = new String[len - 1][];
			for (int i = 2; i < len; i++) {
				newArray[i - 1] = strJaggedArray[i];
			}
			newArray[0] = temp;
			return DoExchange(newArray);
		} else {
			return strJaggedArray;
		}
	}

}
