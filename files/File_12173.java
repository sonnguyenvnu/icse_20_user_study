package com.geekq.common.utils.numcal;

/**
 * 用户状�?类，记录用户在平�?�使用系统中所有的状�?。
 * @author 邱润泽
 */
public class BitStatesUtils {
	public final static Long OP_BASIC_INFO = 1L; //用户注册�?功的标示,�?�为默认�?始状�?
	public final static Long OP_BIND_PHONE = 2L << 0; //用户绑定手机状�?�?
	public final static Long OP_BIND_EMAIL = 2L << 1;//用户绑定邮箱
	public final static Long OP_BASE_INFO = 2L << 2;//填写基本资料
	public final static Long OP_REAL_AUTH = 2L << 3;//用户实�??认�?
	public final static Long OP_VEDIO_AUTH = 2L << 4;//视频认�?
	public final static Long OP_HAS_BIDRQUEST=2l<<5;//当�?用户有一个借款还在借款�?程当中

	/**
	 * @param states 所有状�?值
	 * @param value  需�?判断状�?值
	 * @return 是�?�存在
	 */
	public static boolean hasState(long states, long value) {
		return (states & value) != 0;
	}

	/**
	 * @param states 已有状�?值
	 * @param value  需�?添加状�?值
	 * @return 新的状�?值
	 */
	public static long addState(long states, long value) {
		if (hasState(states, value)) {
			return states;
		}
		return (states | value);
	}

	/**
	 * @param states 已有状�?值
	 * @param value  需�?删除状�?值
	 * @return 新的状�?值
	 */
	public static long removeState(long states, long value) {
		if (!hasState(states, value)) {
			return states;
		}
		return states ^ value;
	}
}
