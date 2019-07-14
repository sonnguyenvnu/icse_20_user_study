package com.geekq.common.utils.MD5;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * @author 邱润泽
 */
public class MD5Utils {


    public  static final  String  getSaltT (){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        return salt;
    }
    private static  String getSalt = getSaltT();

    public static String MD5( String keyName ){
        /**
         * 返回16
         */
        return DigestUtils.md5Hex(keyName);

    }


    /**
     * 测试使用
     * @param inputPass
     * @return
     */
    public static String inputPassFormPass ( String inputPass ){
        String str = "" + getSalt.charAt(0) + getSalt.charAt(2) + inputPass + getSalt.charAt(4) + getSalt.charAt(6) ;
        return MD5(str);
    }

    /**
     * �?值salt �?机 二次加密
     * @param inputPass
     * @return
     */
    public static String formPassFormPass ( String inputPass ){
        String str = "" + getSalt.charAt(0) + getSalt.charAt(2) +inputPass + getSalt.charAt(4) + getSalt.charAt(6) ;
        return MD5(str);
    }

    /**
     * 第二次md5--�??解密 用户登录验�? ---　salt　�?��?机　
     * @param formPass
     * @param salt
     * @return
     */
    public static  String formPassToDBPass ( String  formPass ,String salt ) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ formPass + salt.charAt(4) + salt.charAt(6) ;
        return MD5(str);
    }

}
