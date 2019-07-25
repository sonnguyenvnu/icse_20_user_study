package com.cg.baseproject.encryption;

import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * 编�?工具类 
 * 实现aes加密�?解密 
 */
public class EncryptUtils {

    /**
     * 加密用的Key �?�以用26个字�?和数字组�? 此处使用AES-128-CBC加密模�?，key需�?为16�?。
     */
    private static final String KEY = "!@cdabcdabc8751%";//秘钥长度定长,16

    /**
     * 算法 
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        System.out.println("key length：" + KEY.length());
        String content = "我爱你";
        System.out.println("加密�?：" + content);

        System.out.println("加密密钥和解密密钥：" + KEY);

        String encrypt = aesEncrypt(content, KEY);
        System.out.println("加密�?�：" + encrypt);

        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("解密�?�：" + decrypt);
    }

    /**
     * aes解密 
     * @param encrypt   内容 
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) throws Exception {
        return aesDecrypt(encrypt, KEY);
    }

    /**
     * aes加密 
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, KEY);
    }

    /**
     * 将byte[]转为�?��?进制的字符串 
     * @param bytes byte[] 
     * @param radix �?�以转�?�进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围�?��?�为10进制 
     * @return 转�?��?�的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }

    /**
     * base 64 encode 
     * @param bytes 待编�?的byte[] 
     * @return 编�?�?�的base 64 code 
     */
    public static String base64Encode(byte[] bytes){
        return Base64Utils.encodeToString(bytes,true);
    }

    /**
     * base 64 decode 
     * @param base64Code 待解�?的base 64 code 
     * @return 解�?�?�的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : Base64Utils.decode(base64Code);
    }


    /**
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密�?�的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(256);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密�?�的base 64 code 
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密�?�的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密�?�的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

}  

