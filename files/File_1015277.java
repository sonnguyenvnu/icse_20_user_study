package info.xiaomo.core.untils;

import java.security.MessageDigest;

/**
 * │＼＿＿╭╭╭╭╭＿＿�?│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　�?　　　　　　�?│
 * │≡　　　　�?　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　�?　　｜
 * ｜╭�?�?�?╮把今天最好的表现当作明天最新的起点．．～
 * �?��?� 最高�?�表�?� �?��?��?� 明日最新�?�始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/3 10:03
 * Description: md5加密解密
 * Copyright(©) 2015 by xiaomo.
 */
public class Md5Util {

    private final static String[] HEX_DIGITS = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转�?�字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            //若使用本函数转�?�则�?�得到加密结果的16进制表示，�?�数字字�?混�?�的形�?
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToNumString(byte b) {

        int tempB = b;
        if (tempB < 0) {
            tempB = 256 + tempB;
        }

        return String.valueOf(tempB);
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String encode(String password, String salt) {
        String resultString = null;
        try {
            resultString = password + salt;
            MessageDigest md = MessageDigest.getInstance("md5");
            resultString = byteArrayToString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static void main(String[] args) {
        String password = "xiaomo";
        String salt = RandomUtil.createSalt();
        System.out.println("原数�?�：" + password);
        System.out.println("�?值：" + salt);
        System.out.println("MD5�?�：" + encode(password, salt));
    }

}
