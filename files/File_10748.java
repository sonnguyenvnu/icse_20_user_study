package com.vondear.rxtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.vondear.rxtool.RxDataTool.bytes2HexString;
import static com.vondear.rxtool.RxDataTool.hexString2Bytes;

/**
 *
 * @author vondear
 * @date 2016/1/24
 * 加密解密相关的工具类
 */
public class RxEncryptTool {

    /*********************** 哈希加密相关 ***********************/
    private static final String DES_Algorithm = "DES";
    private static final String TripleDES_Algorithm = "DESede";
    private static final String AES_Algorithm = "AES";
    /**
     * DES转�?�
     * <p>法算法�??称/加密模�?/填充方�?</p>
     * <p>加密模�?有：电�?密�?本模�?ECB�?加密�?�链模�?CBC�?加密�??馈模�?CFB�?输出�??馈模�?OFB</p>
     * <p>填充方�?有：NoPadding�?ZerosPadding�?PKCS5Padding</p>
     */
    public static String DES_Transformation = "DES/ECB/NoPadding";
    /**
     * 3DES转�?�
     * <p>法算法�??称/加密模�?/填充方�?</p>
     * <p>加密模�?有：电�?密�?本模�?ECB�?加密�?�链模�?CBC�?加密�??馈模�?CFB�?输出�??馈模�?OFB</p>
     * <p>填充方�?有：NoPadding�?ZerosPadding�?PKCS5Padding</p>
     */
    public static String TripleDES_Transformation = "DESede/ECB/NoPadding";
    /**
     * AES转�?�
     * <p>法算法�??称/加密模�?/填充方�?</p>
     * <p>加密模�?有：电�?密�?本模�?ECB�?加密�?�链模�?CBC�?加密�??馈模�?CFB�?输出�??馈模�?OFB</p>
     * <p>填充方�?有：NoPadding�?ZerosPadding�?PKCS5Padding</p>
     */
    public static String AES_Transformation = "AES/ECB/NoPadding";

    /**
     * MD2加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD2ToString(String data) {
        return encryptMD2ToString(data.getBytes());
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD2ToString(byte[] data) {
        return bytes2HexString(encryptMD2(data));
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD2(byte[] data) {
        return encryptAlgorithm(data, "MD2");
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD5ToString(String data) {
        return encryptMD5ToString(data.getBytes());
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @param salt �?
     * @return 16进制加�?密文
     */
    public static String encryptMD5ToString(String data, String salt) {
        return bytes2HexString(encryptMD5((data + salt).getBytes()));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD5ToString(byte[] data) {
        return bytes2HexString(encryptMD5(data));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @param salt �?字节数组
     * @return 16进制加�?密文
     */
    public static String encryptMD5ToString(byte[] data, byte[] salt) {
        byte[] dataSalt = new byte[data.length + salt.length];
        System.arraycopy(data, 0, dataSalt, 0, data.length);
        System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
        return bytes2HexString(encryptMD5(dataSalt));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(byte[] data) {
        return encryptAlgorithm(data, "MD5");
    }

    /**
     * MD5加密文件
     *
     * @param filePath 文件路径
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(String filePath) {
        return encryptMD5File2String(new File(filePath));
    }

    /**
     * MD5加密文件
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验�?
     */
    public static byte[] encryptMD5File(String filePath) {
        return encryptMD5File(new File(filePath));
    }

    /**
     * MD5加密文件
     *
     * @param file 文件
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(File file) {
        return encryptMD5File(file) != null ? bytes2HexString(encryptMD5File(file)) : "";
    }

    /**
     * MD5加密文件
     *
     * @param file 文件
     * @return 文件的MD5校验�?
     */
    public static byte[] encryptMD5File(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            RxFileTool.closeIO(fis);
        }
        return null;
    }

    /**
     * SHA1加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(String data) {
        return encryptSHA1ToString(data.getBytes());
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(byte[] data) {
        return bytes2HexString(encryptSHA1(data));
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA1(byte[] data) {
        return encryptAlgorithm(data, "SHA-1");
    }

    /**
     * SHA224加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(String data) {
        return encryptSHA224ToString(data.getBytes());
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(byte[] data) {
        return bytes2HexString(encryptSHA224(data));
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA224(byte[] data) {
        return encryptAlgorithm(data, "SHA-224");
    }

    /**
     * SHA256加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(String data) {
        return encryptSHA256ToString(data.getBytes());
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(byte[] data) {
        return bytes2HexString(encryptSHA256(data));
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA256(byte[] data) {
        return encryptAlgorithm(data, "SHA-256");
    }

    /**
     * SHA384加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(String data) {
        return encryptSHA384ToString(data.getBytes());
    }

    /************************ DES加密相关 ***********************/

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(byte[] data) {
        return bytes2HexString(encryptSHA384(data));
    }

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA384(byte[] data) {
        return encryptAlgorithm(data, "SHA-384");
    }

    /**
     * SHA512加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(String data) {
        return encryptSHA512ToString(data.getBytes());
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(byte[] data) {
        return bytes2HexString(encryptSHA512(data));
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA512(byte[] data) {
        return encryptAlgorithm(data, "SHA-512");
    }

    /**
     * 对data进行algorithm算法加密
     *
     * @param data      明文字节数组
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] encryptAlgorithm(byte[] data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * @param data           数�?�
     * @param key            秘钥
     * @param algorithm      采用何�?DES算法
     * @param transformation 转�?�
     * @param isEncrypt      是�?�加密
     * @return 密文或者明文，适用于DES，3DES，AES
     */
    public static byte[] DESTemplet(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            SecureRandom random = new SecureRandom();
            cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密�?�转为Base64编�?
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptDES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encryptDES(data, key));
    }

    /**
     * DES加密�?�转为16进制
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 16进制密文
     */
    public static String encryptDES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptDES(data, key));
    }

    /************************ 3DES加密相关 ***********************/

    /**
     * DES加密
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 密文
     */
    public static byte[] encryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, true);
    }

    /**
     * DES解密Base64编�?密文
     *
     * @param data Base64编�?密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64DES(byte[] data, byte[] key) {
        return decryptDES(RxEncodeTool.base64Decode(data), key);
    }

    /**
     * DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringDES(String data, byte[] key) {
        return decryptDES(hexString2Bytes(data), key);
    }

    /**
     * DES解密
     *
     * @param data 密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, false);
    }

    /**
     * 3DES加密�?�转为Base64编�?
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return Base64密文
     */
    public static byte[] encrypt3DES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encrypt3DES(data, key));
    }

    /**
     * 3DES加密�?�转为16进制
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return 16进制密文
     */
    public static String encrypt3DES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encrypt3DES(data, key));
    }

    /**
     * 3DES加密
     *
     * @param data 明文
     * @param key  24字节密钥
     * @return 密文
     */
    public static byte[] encrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }

    /**
     * 3DES解密Base64编�?密文
     *
     * @param data Base64编�?密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64_3DES(byte[] data, byte[] key) {
        return decrypt3DES(RxEncodeTool.base64Decode(data), key);
    }

    /************************ AES加密相关 ***********************/

    /**
     * 3DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexString3DES(String data, byte[] key) {
        return decrypt3DES(hexString2Bytes(data), key);
    }

    /**
     * 3DES解密
     *
     * @param data 密文
     * @param key  24字节密钥
     * @return 明文
     */
    public static byte[] decrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, false);
    }

    /**
     * AES加密�?�转为Base64编�?
     *
     * @param data 明文
     * @param key  16�?24�?32字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
        return RxEncodeTool.base64Encode(encryptAES(data, key));
    }

    /**
     * AES加密�?�转为16进制
     *
     * @param data 明文
     * @param key  16�?24�?32字节秘钥
     * @return 16进制密文
     */
    public static String encryptAES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptAES(data, key));
    }

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  16�?24�?32字节秘钥
     * @return 密文
     */
    public static byte[] encryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, true);
    }

    /**
     * AES解密Base64编�?密文
     *
     * @param data Base64编�?密文
     * @param key  16�?24�?32字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64AES(byte[] data, byte[] key) {
        return decryptAES(RxEncodeTool.base64Decode(data), key);
    }

    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16�?24�?32字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringAES(String data, byte[] key) {
        return decryptAES(hexString2Bytes(data), key);
    }

    /**
     * AES解密
     *
     * @param data 密文
     * @param key  16�?24�?32字节秘钥
     * @return 明文
     */
    public static byte[] decryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, false);
    }
}
