package com.github.vole.common.validate;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * èº«ä»½è¯?è½¬æ?¢å·¥å…·ç±»
 */
public class IdCardUtil {
   /** 
     * æ ¹æ?®15ä½?çš„èº«ä»½è¯?å?·ç ?èŽ·å¾—18ä½?èº«ä»½è¯?å?·ç ? 
     * @param fifteenIDCard 15ä½?çš„èº«ä»½è¯?å?·ç ? 
     * @return å?‡çº§å?Žçš„18ä½?èº«ä»½è¯?å?·ç ? 
     * @throws Exception å¦‚æžœä¸?æ˜¯15ä½?çš„èº«ä»½è¯?å?·ç ?ï¼Œåˆ™æŠ›å‡ºå¼‚å¸¸ 
     */  
    public static String getEighteenIDCard(String fifteenIDCard) throws Exception{  
       if(fifteenIDCard != null && fifteenIDCard.length() == 15){  
           StringBuilder sb = new StringBuilder();  
           sb.append(fifteenIDCard.substring(0, 6))  
             .append("19")  
             .append(fifteenIDCard.substring(6));  
           sb.append(getVerifyCode(sb.toString()));  
            return sb.toString();  
       } else {  
            throw new Exception("ä¸?æ˜¯15ä½?çš„èº«ä»½è¯?");  
       }  
    }
    
    
    /** 
      * èŽ·å?–æ ¡éªŒç ? 
      * @param idCardNumber ä¸?å¸¦æ ¡éªŒä½?çš„èº«ä»½è¯?å?·ç ?ï¼ˆ17ä½?ï¼‰ 
      * @return æ ¡éªŒç ? 
      * @throws Exception å¦‚æžœèº«ä»½è¯?æ²¡æœ‰åŠ ä¸Š19ï¼Œåˆ™æŠ›å‡ºå¼‚å¸¸ 
      */  
       public static char getVerifyCode(String idCardNumber) throws Exception{
           if(idCardNumber == null || idCardNumber.length() < 17) {  
                throw new Exception("ä¸?å?ˆæ³•çš„èº«ä»½è¯?å?·ç ?");
            }  
            char[] Ai = idCardNumber.toCharArray();  
            int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};  
            char[] verifyCode = {'1','0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};  
            int S = 0;  
            int Y;  
            for(int i = 0; i < Wi.length; i++){  
                S += (Ai[i] - '0') * Wi[i];  
            }  
           Y = S % 11;  
            return verifyCode[Y];  
        }

   /**
    * æ ¡éªŒ18ä½?çš„èº«ä»½è¯?å?·ç ?çš„æ ¡éªŒä½?æ˜¯å?¦æ­£ç¡®
    * @param idCardNumber 18ä½?çš„èº«ä»½è¯?å?·ç ?
    * @return
    * @throws Exception
    */
      public static boolean verify(String idCardNumber) throws Exception{
           if(idCardNumber == null || idCardNumber.length() != 18) {
              throw new Exception("ä¸?æ˜¯18ä½?çš„èº«ä»½è¯?å?·ç ?");
           }
          return getVerifyCode(idCardNumber) == idCardNumber.charAt(idCardNumber.length() - 1);
     }

    /**
     * æ ¡éªŒ18ä½?çš„èº«ä»½è¯?å?·ç ?çš„æ ¡éªŒä½?æ˜¯å?¦æ­£ç¡®
     * @param idCardNumber 18ä½?çš„èº«ä»½è¯?å?·ç ?
     * @return
     * @throws Exception
     */
    public static boolean verifyValid(String idCardNumber) {
        if(idCardNumber == null || idCardNumber.length() != 18) {
           return false;
        }
        boolean flag = false;
        try {
            flag = getVerifyCode(idCardNumber) == idCardNumber.charAt(idCardNumber.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

      /**
       * æ ¹æ?®18ä½?èº«ä»½è¯?èŽ·å?–å¹´é¾„å’Œæ€§åˆ«
       * @param idCard
       * @return
       * @throws Exception
       */
      public static Map<String, Object> getCarInfo(String idCard) throws Exception {
    	      if(idCard.length()==15){
    	    	  idCard = getEighteenIDCard(idCard);
    	      }
    	      Map<String, Object> map = new HashMap<String, Object>();  
    	      if(verify(idCard)){
                  String year = idCard.substring(6).substring(0, 4);// å¾—åˆ°å¹´ä»½  
                  String yue = idCard.substring(10).substring(0, 2);// å¾—åˆ°æœˆä»½  
                  String sex;  
                  if (Integer.parseInt(idCard.substring(16).substring(0, 1)) % 2 == 0) {// åˆ¤æ–­æ€§åˆ«  
                     sex = "f";  
                  } else {  
                     sex = "m";  
                }  
                  Date date = new Date();// å¾—åˆ°å½“å‰?çš„ç³»ç»Ÿæ—¶é—´  
                  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
                  String fyear = format.format(date).substring(0, 4);// å½“å‰?å¹´ä»½  
                  String fyue = format.format(date).substring(5, 7);// æœˆä»½  
                  int age = 0;  
                  if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // å½“å‰?æœˆä»½å¤§äºŽç”¨æˆ·å‡ºèº«çš„æœˆä»½è¡¨ç¤ºå·²è¿‡ç”Ÿ  
                      age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;  
                  } else {// å½“å‰?ç”¨æˆ·è¿˜æ²¡è¿‡ç”Ÿ  
                      age = Integer.parseInt(fyear) - Integer.parseInt(year);  
                  }  
                  map.put("sex", sex);  
                  map.put("age", age);  
    	      }else {
    	    	   throw new Exception("èº«ä»½è¯?å?·ç ?éªŒè¯?ä¸?æ­£ç¡®");  
    	      }
    	      return map;   
          }  
      
      public static String idCardHide(String idCard){
    	  String idCardHide="";
    	  if(StringUtils.isNotBlank(idCard)){
    		  try{
    			  String str = idCard.substring(6, 14);
    			  idCardHide= idCard.replace(str, "******");
    		  }catch(Exception e){
    			  idCardHide = idCard;
    			 // System.out.println(e);
    		  }
    		  
    	  }
    	  return idCardHide;
      }
      
      
      
      
   public static void main(String[] args) {
	   String idCard = "421124198609086012";
	   String str =idCard.substring(6,14);
	   System.out.println(idCard.replace(str, "******")); 
	   
	   
//	  try {
//		 Map<String,Object> map = getCarInfo("421124198609086012");
//		System.out.println(map.get("sex").toString());
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}  

}
