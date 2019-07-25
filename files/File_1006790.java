/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x7.core.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 
 */
public class NumberUtil {
	
	// 默认除法�?算精度  
    private static final int DEF_DIV_SCALE = 10;

	public static double getValue(Double d) {
		return d == null ? 0 : d;
	}

	public static int getValue(Integer i) {
		return i == null ? 0 : i;
	}

	public static float getValue(Float f) {
		return f == null ? 0 : f;
	}

	public static long getValue(Long l) {
		return l == null ? 0 : l;
	}

	/**
	 * 截�?��?数点�?�2�?, �?四�?五入<br>
	 * 
	 * @param d
	 * 
	 */
	public static double getMoney(double d) {
		double result = (getValue(d) * 100);
		result = Math.round(result);

		int i = (int) (result);

		return i / 100.0;
	}

	/**
	 * 截�?��?数点�?�3�?, �?四�?五入<br>
	 * 
	 * @param w
	 * 
	 */
	public static double getWeight(double w) {
		double result = (getValue(w) * 1000);
		result = Math.round(result);

		int i = (int) (result);

		return i / 1000.0;
	}

	/**
	 * �??供精确的加法�?算
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个�?�数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * �??供精确的�?法�?算
	 * 
	 * @param v1
	 *            被�?数
	 * @param v2
	 *            �?数
	 * @return 两个�?�数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * �??供精确的乘法�?算
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个�?�数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * �??供（相对）精确的除法�?算，当�?�生除�?尽的情况时，精确到 �?数点以�?�10�?，以�?�的数字四�?五入
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个�?�数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * �??供（相对）精确的除法�?算。当�?�生除�?尽的情况时，由scale�?�数指 定精度，以�?�的数字四�?五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需�?精确到�?数点以�?�几�?。
	 * @return 两个�?�数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �??供精确的�?数�?四�?五入处�?�。
	 * 
	 * @param v
	 *            需�?四�?五入的数字
	 * @param scale
	 *            �?数点�?��?留几�?
	 * @return 四�?五入�?�的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static boolean isNumeric(String str) {    
		if (StringUtil.isNullOrEmpty(str)){
			return false;
		}
	    Pattern pattern = null;
	    if (str.contains("."))
	    	pattern = Pattern.compile("-?[0-9]+.?[0-9]+");    
	    else
	    	pattern = Pattern.compile("-?[0-9]+");   
	    return pattern.matcher(str).matches();    
	}  

}
