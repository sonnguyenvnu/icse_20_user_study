package com.github.vole.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description �??供一些常用的函數處�?�
 */
public class CommonUtils {
	/**
	 * 整数数字正则表达�?
	 */
	private final static String INTEGER_REGEX = "^[+-]?[\\d]+$";

	/**
	 * 数字格�?正则表达�?(整数浮点数)
	 */
	private final static String NUMBER_REGEX = "^[+-]?[\\d]+(\\.\\d+)?$";

	/**
	 * @todo 判断字符串是整数
	 * @param obj
	 * @return
	 */
	public static boolean isInteger(String obj) {
		return StringUtil.matches(obj, INTEGER_REGEX);
	}

	/**
	 * @todo 判断字符串是�?�为数字
	 * @param numberStr
	 * @return
	 */
	public static boolean isNumber(String numberStr) {
		return StringUtil.matches(numberStr, NUMBER_REGEX);
	}

	/**
	 * @todo 产生�?机数数组
	 * @param maxValue
	 *            �?机数的最大值
	 * @param size
	 *            �?机数的个数
	 * @return
	 */
	public static Object[] randomArray(int maxValue, int size) {
		int realSize = size;
		if (realSize > maxValue)
			realSize = maxValue;
		// 长度等于最大值
		if (realSize == maxValue) {
			Object[] result = new Object[maxValue];
			for (int i = 0; i < maxValue; i++) {
				result[i] = i;
			}
			return result;
		}
		Set<Integer> resultSet = new HashSet<Integer>(realSize);
		int randomNum;
		while (resultSet.size() < realSize) {
			randomNum = (int) (Math.random() * maxValue);
			resultSet.add(randomNum);
		}
		return resultSet.toArray();
	}

	/**
	 * @按照概率获�?�对应概率的数�?�索引，如：A：概率80%，B：10%，C：6%，D：4%，将出现概率放入数组， 按�?机规则返回对应概率的索引
	 * @param probabilities
	 * @return
	 */
	public static int getProbabilityIndex(int[] probabilities) {
		int total = 0;
		for (int probabilitiy : probabilities)
			total = total + probabilitiy;
		int randomData = (int) (Math.random() * total) + 1;
		int base = 0;
		for (int i = 0; i < probabilities.length; i++) {
			if (randomData > base && randomData <= base + probabilities[i])
				return i;
			base = base + probabilities[i];
		}
		return 0;
	}

	/**
	 * @todo 获得指定路径的文件
	 * @param file
	 *            文件路径like:classpath:xxx.xml或xxx.xml
	 * @return
	 */
	public static InputStream getFileInputStream(Object file) {
		if (file == null)
			return null;
		try {
			if (file instanceof File)
				return new FileInputStream((File) file);
			else {
				// 文件路径
				if (new File((String) file).exists())
					return new FileInputStream((String) file);
				else {
					String realFile = (String) file;
					if (StringUtil.indexOfIgnoreCase(realFile.trim(), "classpath:") == 0)
						realFile = realFile.trim().substring(10).trim();
					if (realFile.charAt(0) == '/')
						realFile = realFile.substring(1);
					return Thread.currentThread().getContextClassLoader().getResourceAsStream(realFile);
				}
			}
		} catch (FileNotFoundException fn) {
			fn.printStackTrace();
		}
		return null;
	}

	/**
	 * 简�?�表达�?(�?�独列出�?�便于�?�容错性处�?�)
	 * 
	 * @param sql
	 * @param paramValues
	 * @param preCount
	 * @return
	 */
	private static String evalSimpleExpress(String sql, List paramValues, int preCount) {
		// �?能超过两个�?算符
		if (sql.indexOf("||") != -1 && sql.indexOf("&&") != -1)
			return "undefine";
		// 比较符�?�(等于用==,最�?�用=进行容错处�?�)
		String[] compareStr = { "!=", "==", ">=", "<=", ">", "<", "=" };
		String splitStr = "==";
		String logicStr = "&&";
		String[] expressions;
		try {
			if (sql.indexOf("||") != -1)
				logicStr = "||";
			expressions = sql.split(logicStr);
			// 超过2个�?算,交freemarker
			if (expressions.length > 2)
				return "undefine";
			boolean[] expressResult = new boolean[expressions.length];
			String express;
			Object value;
			String compareValue;
			for (int i = 0; i < expressions.length; i++) {
				value = paramValues.get(preCount + i);
				express = expressions[i].trim();
				for (int j = 0; j < compareStr.length; j++) {
					if (express.indexOf(compareStr[j]) != -1) {
						splitStr = compareStr[j];
						break;
					}
				}
				compareValue = express.split(splitStr)[1];
				// 计算�?�个比较的结果
				expressResult[i] = compare(value, splitStr, compareValue);
			}
			if (expressions.length == 1) {
				return (expressResult[0] ? "true" : "false");
			} else {
				if (logicStr.equals("&&")) {
					return ((expressResult[0] && expressResult[1]) ? "true" : "false");
				} else {
					return ((expressResult[0] || expressResult[1]) ? "true" : "false");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "undefine";
	}

	/**
	 * 两个数�?�进行比较
	 * 
	 * @param value
	 * @param compareType
	 * @param compareValue
	 * @return
	 */
	private static boolean compare(Object value, String compareType, String compareValue) {
		String append = "0";
		String[] calculateStr = { "+", "-" };
		for (String calculate : calculateStr) {
			if (compareValue.indexOf(calculate) > 0) {
				String[] tmpAry = compareValue.split(calculate.equals("+") ? "\\+" : "\\-");
				append = calculate + tmpAry[1].trim();
				compareValue = tmpAry[0].trim();
				break;
			}
		}
		String type = "string";
		String dayTimeFmt = "yyyy-MM-dd HH:mm:ss";
		String dayFmt = "yyyy-MM-dd";
		String lowCompareValue = compareValue.toLowerCase();
		if (lowCompareValue.equals("now()") || lowCompareValue.equals(".now") || lowCompareValue.equals("${.now}")
				|| lowCompareValue.equals("nowtime()")) {
			compareValue = DateUtil.formatDate(DateUtil.addSecond(new Date(), Double.parseDouble(append)), dayTimeFmt);
			type = "time";
		} else if (lowCompareValue.equals("day()") || lowCompareValue.equals("sysdate()")
				|| lowCompareValue.equals(".day") || lowCompareValue.equals(".day()")
				|| lowCompareValue.equals("${.day}")) {
			compareValue = DateUtil.formatDate(DateUtil.addSecond(new Date(), Double.parseDouble(append)), dayFmt);
			type = "date";
		}

		// 替�?�掉�?�引�?�和�?�引�?�
		compareValue = compareValue.replaceAll("\'", "").replaceAll("\"", "");
		String realValue = value.toString();
		if (type.equals("time")) {
			realValue = DateUtil.formatDate(value, dayTimeFmt);
		} else if (type.equals("date"))
			realValue = DateUtil.formatDate(value, dayFmt);
		// 等于(兼容等于�?��?�法)
		if (compareType.equals("==") || compareType.equals("=")) {
			return realValue.equalsIgnoreCase(compareValue);
		}
		// �?等于
		if (compareType.equals("!=")) {
			return !realValue.equalsIgnoreCase(compareValue);
		}
		// 大于等于
		if (compareType.equals(">=")) {
			return moreEqual(value, realValue, compareValue, type);
		}
		// �?于等于
		if (compareType.equals("<=")) {
			return lessEqual(value, realValue, compareValue, type);
		}
		// 大于
		if (compareType.equals(">")) {
			return more(value, realValue, compareValue, type);
		}
		// �?于
		if (compareType.equals("<")) {
			return less(value, realValue, compareValue, type);
		}
		return true;
	}

	/**
	 * 大于等于
	 * 
	 * @param value
	 * @param valueStr
	 * @param compare
	 * @param type
	 * @return
	 */
	private static boolean moreEqual(Object value, String valueStr, String compare, String type) {
		if (type.equals("time") || type.equals("date")) {
			return DateUtil.convertDateObject(valueStr).compareTo(DateUtil.convertDateObject(compare)) >= 0;
		}
		// 数字
		if (CommonUtils.isNumber(valueStr) && CommonUtils.isNumber(compare)) {
			return Double.parseDouble(valueStr) >= Double.parseDouble(compare);
		} else
			return valueStr.compareTo(compare) >= 0;
	}

	/**
	 * �?于等于
	 * 
	 * @param value
	 * @param valueStr
	 * @param compare
	 * @param type
	 * @return
	 */
	private static boolean lessEqual(Object value, String valueStr, String compare, String type) {
		if (type.equals("time") || type.equals("date")) {
			return DateUtil.convertDateObject(valueStr).compareTo(DateUtil.convertDateObject(compare)) <= 0;
		}
		// 数字
		if (CommonUtils.isNumber(valueStr) && CommonUtils.isNumber(compare)) {
			return Double.parseDouble(valueStr) <= Double.parseDouble(compare);
		} else
			return valueStr.compareTo(compare) <= 0;
	}

	/**
	 * 大于
	 * 
	 * @param value
	 * @param valueStr
	 * @param compare
	 * @param type
	 * @return
	 */
	private static boolean more(Object value, String valueStr, String compare, String type) {
		if (type.equals("time") || type.equals("date")) {
			return DateUtil.convertDateObject(valueStr).compareTo(DateUtil.convertDateObject(compare)) > 0;
		}
		// 数字
		if (CommonUtils.isNumber(valueStr) && CommonUtils.isNumber(compare)) {
			return Double.parseDouble(valueStr) > Double.parseDouble(compare);
		} else
			return valueStr.compareTo(compare) > 0;
	}

	/**
	 * �?于
	 * 
	 * @param value
	 * @param valueStr
	 * @param compare
	 * @param type
	 * @return
	 */
	private static boolean less(Object value, String valueStr, String compare, String type) {
		if (type.equals("time") || type.equals("date")) {
			return DateUtil.convertDateObject(valueStr).compareTo(DateUtil.convertDateObject(compare)) < 0;
		}
		// 数字
		if (CommonUtils.isNumber(valueStr) && CommonUtils.isNumber(compare)) {
			return Double.parseDouble(valueStr) < Double.parseDouble(compare);
		} else
			return valueStr.compareTo(compare) < 0;
	}
	
	/**
	 * @todo 加工字段�??称，将数�?�库sql查询的columnName转�?对应对象的属性�??称(去除下划线)
	 * @param labelNames
	 * @return
	 */
	public static String[] humpFieldNames(String[] labelNames) {
		if (labelNames == null)
			return null;
		String[] result = new String[labelNames.length];
		for (int i = 0, n = labelNames.length; i < n; i++)
			result[i] = StringUtil.toHumpStr(labelNames[i], false);
		return result;
	}
}
