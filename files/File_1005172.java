package org.jeecgframework.core.util;

/**
 * �?水�?�生�?规则(按默认规则递增，数字从1-99开始递增，数字到99，递增字�?;�?数�?够增加�?数)
 * A001
 * A001A002
 * @author zhangdaihao
 *
 */
public class YouBianCodeUtil {

	// 数字�?数(默认生�?3�?的数字)

	private static final int numLength = 2;//代表数字�?数

	public static final int zhanweiLength = 1+numLength;

	/**
	 * 根�?��?一个code，获�?��?�级下一个code
	 * 例如:当�?最大code为D01A04，下一个code为：D01A05
	 * 
	 * @param code
	 * @return
	 */
	public static synchronized String getNextYouBianCode(String code) {
		String newcode = "";
		if (code == null || code =="") {
			String zimu = "A";
			String num = getStrNum(1);
			newcode = zimu + num;
		} else {
			String before_code = code.substring(0, code.length() - 1- numLength);
			String after_code = code.substring(code.length() - 1 - numLength,code.length());
			char after_code_zimu = after_code.substring(0, 1).charAt(0);
			Integer after_code_num = Integer.parseInt(after_code.substring(1));
//			 org.jeecgframework.core.util.LogUtil.info(after_code);
//			 org.jeecgframework.core.util.LogUtil.info(after_code_zimu);
//			 org.jeecgframework.core.util.LogUtil.info(after_code_num);

			String nextNum = "";
			char nextZimu = 'A';
			// 先判断数字等于999*，则计数从1�?新开始，递增
			if (after_code_num == getMaxNumByLength(numLength)) {
				nextNum = getNextStrNum(0);
			} else {
				nextNum = getNextStrNum(after_code_num);
			}
			// 先判断数字等于999*，则字�?从A�?新开始,递增
			if(after_code_num == getMaxNumByLength(numLength)) {
				nextZimu = getNextZiMu(after_code_zimu);
			}else{
				nextZimu = after_code_zimu;
			}

			// 例如Z99，下一个code就是Z99A01
			if ('Z' == after_code_zimu && getMaxNumByLength(numLength) == after_code_num) {
				newcode = code + (nextZimu + nextNum);
			} else {
				newcode = before_code + (nextZimu + nextNum);
			}
		}
		return newcode;

	}

	/**
	 * 根�?�父亲code,获�?�下级的下一个code
	 * 
	 * 例如：父亲CODE:A01
	 *       当�?CODE:A01B03
	 *       获�?�的code:A01B04
	 *       
	 * @param parentCode   上级code
	 * @param localCode    �?�级code
	 * @return
	 */
	public static synchronized String getSubYouBianCode(String parentCode,String localCode) {
		if(localCode!=null && localCode!=""){

//			return parentCode + getNextYouBianCode(localCode);
			return getNextYouBianCode(localCode);

		}else{
			parentCode = parentCode + "A"+ getNextStrNum(0);
		}
		return parentCode;
	}

	

	/**
	 * 将数字�?�?��?数补零
	 * 
	 * @param num
	 * @return
	 */
	private static String getNextStrNum(int num) {
		return getStrNum(getNextNum(num));
	}

	/**
	 * 将数字�?�?��?数补零
	 * 
	 * @param num
	 * @return
	 */
	private static String getStrNum(int num) {
		String s = String.format("%0" + numLength + "d", num);
		return s;
	}

	/**
	 * 递增获�?�下个数字
	 * 
	 * @param num
	 * @return
	 */
	private static int getNextNum(int num) {
		num++;
		return num;
	}

	/**
	 * 递增获�?�下个字�?
	 * 
	 * @param num
	 * @return
	 */
	private static char getNextZiMu(char zimu) {
		if (zimu == 'Z') {
			return 'A';
		}
		zimu++;
		return zimu;
	}
	
	/**
	 * 根�?�数字�?数获�?�最大值
	 * @param length
	 * @return
	 */
	private static int getMaxNumByLength(int length){
		if(length==0){
			return 0;
		}
		String max_num = "";
		for (int i=0;i<length;i++){
			max_num = max_num + "9";
		}
		return Integer.parseInt(max_num);
	}
	public static String[] cutYouBianCode(String code){
		if(code==null||StringUtil.isEmpty(code)){
			return null;
		}else{
			//获�?�标准长度为numLength+1,截�?�的数�?为code.length/numLength+1
			int c = code.length()/(numLength+1);
			String[] cutcode = new String[c];
			for(int i =0 ; i <c;i++){
				cutcode[i] = code.substring(0,(i+1)*(numLength+1));
			}
			return cutcode;
		}
		
	}
	public static void main(String[] args) {
		// org.jeecgframework.core.util.LogUtil.info(getNextZiMu('C'));
		// org.jeecgframework.core.util.LogUtil.info(getNextNum(8));
		org.jeecgframework.core.util.LogUtil.info(getSubYouBianCode("C99A01","B03"));
//		org.jeecgframework.core.util.LogUtil.info(cutYouBianCode("C99A01B01")[2]);
	}
}
