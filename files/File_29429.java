package com.sohu.cache.util;

/**
 * Integer 工具类
 * 
 * @author 银时：yinshi.nc / yinshi.nc@taobao.com
 * @Date Jan 13, 2012
 */
public class IntegerUtil {

	/**
	 * 如果为0,则返回默认值
	 * 
	 * @param originalInt
	 * @param defaultInt
	 *            默认Integer
	 * @return
	 */
	public static Integer defaultIfZero( Integer originalInt, Integer defaultInt ) {
		if ( 0 == originalInt ) {
			return defaultInt;
		}
		return originalInt;
	}

	/**
	 * 如果为0,则返回默认值
	 * 
	 * @param originalInt
	 * @param defaultInt
	 *            默认Integer
	 * @return
	 */
	public static Integer defaultIfError( String originalStr, Integer defaultInt ) {

		try {
			return Integer.parseInt( StringUtil.trimToEmpty( originalStr ) );
		} catch ( Exception e ) {
			return defaultInt;
		}
	}

	/**
	 * 如果是一个�?�?�法的整型，那么返回一个默认值
	 * 
	 * @param originalInt
	 * @param defaultInt
	 *            默认Integer
	 * @return
	 */
	public static Integer defaultIfError( Integer originalStr, Integer defaultInt ) {

		try {
			return Integer.valueOf( originalStr );
		} catch ( Exception e ) {
			return defaultInt;
		}
	}

	/**
	 * 如果�?�正,则返回默认值<br>
	 * @param originalInt
	 * @param defaultInt
	 *            默认Integer
	 * @return originalInt if originalInt>0, return defaultInt if originalInt<=0
	 */
	public static Integer defaultIfSmallerThan0( Integer originalInt, Integer defaultInt ) {
		if ( 0 >= originalInt ) {
			return defaultInt;
		}
		return originalInt;
	}
	
	/** 将String 转化�? Integer，如果�?于等于0，将抛异常 */
	public static Integer exceptionIfSmallerThan0( String originalStr )throws Exception{
		try {
			int num = Integer.parseInt( StringUtil.trimToEmpty( originalStr ) );
			if( num > 0  )
				return num;
			else
				throw new Exception();
		} catch ( Exception e ) {
			throw new Exception( originalStr + " is smaller than 0, or it is a  invalid parameter " );
		}
	}
	
	
	
	/**
	 * 判断是�?�大余0
	 * @return false if num <=0  , true if num >0
	 */
	public static boolean isBiggerThan0( int num ){
		if( 0>= num )
			return false;
		return true;
	}

	/**
	 * Return maxInt if too big, else return original.
	 * 
	 * @param originalInt
	 * @param maxInt
	 *            max int
	 * @return
	 */
	public static Integer maxIfTooBig( Integer originalInt, Integer maxInt ) {
		if ( originalInt >= maxInt ) {
			originalInt = maxInt;
		}
		return originalInt;
	}

}
