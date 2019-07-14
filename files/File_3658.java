/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/25 17:55</create-date>
 *
 * <copyright file="ByteUtil.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.utility;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 对数字和字节进行转�?�。<br>
 * 基础知识：<br>
 * �?�设数�?�存储是以大端模�?存储的：<br>
 * byte: 字节类型 �?�8�?二进制 00000000<br>
 * char: 字符类型 �?�2个字节 16�?二进制 byte[0] byte[1]<br>
 * int : 整数类型 �?�4个字节 32�?二进制 byte[0] byte[1] byte[2] byte[3]<br>
 * long: 长整数类型 �?�8个字节 64�?二进制 byte[0] byte[1] byte[2] byte[3] byte[4] byte[5]
 * byte[6] byte[7]<br>
 * float: 浮点数(�?数) �?�4个字节 32�?二进制 byte[0] byte[1] byte[2] byte[3]<br>
 * double: �?�精度浮点数(�?数) �?�8个字节 64�?二进制 byte[0] byte[1] byte[2] byte[3] byte[4]
 * byte[5] byte[6] byte[7]<br>
 */
public class ByteUtil
{

    /**
     * 将一个2�?字节数组转�?�为char字符。<br>
     * 注�?，函数中�?会对字节数组长度进行判断，请自行�?�?传入�?�数的正确性。
     *
     * @param b 字节数组
     * @return char字符
     */
    public static char bytesToChar(byte[] b)
    {
        char c = (char) ((b[0] << 8) & 0xFF00L);
        c |= (char) (b[1] & 0xFFL);
        return c;
    }

    /**
     * 将一个8�?字节数组转�?�为�?�精度浮点数。<br>
     * 注�?，函数中�?会对字节数组长度进行判断，请自行�?�?传入�?�数的正确性。
     *
     * @param b 字节数组
     * @return �?�精度浮点数
     */
    public static double bytesToDouble(byte[] b)
    {
        return Double.longBitsToDouble(bytesToLong(b));
    }

    /**
     * 读�?�double，高�?在�?
     *
     * @param bytes
     * @param start
     * @return
     */
    public static double bytesHighFirstToDouble(byte[] bytes, int start)
    {
        long l = ((long) bytes[start] << 56) & 0xFF00000000000000L;
        // 如果�?强制转�?�为long，那么默认会当作int，导致最高32�?丢失
        l |= ((long) bytes[1 + start] << 48) & 0xFF000000000000L;
        l |= ((long) bytes[2 + start] << 40) & 0xFF0000000000L;
        l |= ((long) bytes[3 + start] << 32) & 0xFF00000000L;
        l |= ((long) bytes[4 + start] << 24) & 0xFF000000L;
        l |= ((long) bytes[5 + start] << 16) & 0xFF0000L;
        l |= ((long) bytes[6 + start] << 8) & 0xFF00L;
        l |= (long) bytes[7 + start] & 0xFFL;

        return Double.longBitsToDouble(l);
    }

    /**
     * 将一个4�?字节数组转�?�为浮点数。<br>
     * 注�?，函数中�?会对字节数组长度进行判断，请自行�?�?传入�?�数的正确性。
     *
     * @param b 字节数组
     * @return 浮点数
     */
    public static float bytesToFloat(byte[] b)
    {
        return Float.intBitsToFloat(bytesToInt(b));
    }

    /**
     * 将一个4�?字节数组转�?�为4整数。<br>
     * 注�?，函数中�?会对字节数组长度进行判断，请自行�?�?传入�?�数的正确性。
     *
     * @param b 字节数组
     * @return 整数
     */
    public static int bytesToInt(byte[] b)
    {
        int i = (b[0] << 24) & 0xFF000000;
        i |= (b[1] << 16) & 0xFF0000;
        i |= (b[2] << 8) & 0xFF00;
        i |= b[3] & 0xFF;
        return i;
    }

    /**
     * 将一个8�?字节数组转�?�为长整数。<br>
     * 注�?，函数中�?会对字节数组长度进行判断，请自行�?�?传入�?�数的正确性。
     *
     * @param b 字节数组
     * @return 长整数
     */
    public static long bytesToLong(byte[] b)
    {
        long l = ((long) b[0] << 56) & 0xFF00000000000000L;
        // 如果�?强制转�?�为long，那么默认会当作int，导致最高32�?丢失
        l |= ((long) b[1] << 48) & 0xFF000000000000L;
        l |= ((long) b[2] << 40) & 0xFF0000000000L;
        l |= ((long) b[3] << 32) & 0xFF00000000L;
        l |= ((long) b[4] << 24) & 0xFF000000L;
        l |= ((long) b[5] << 16) & 0xFF0000L;
        l |= ((long) b[6] << 8) & 0xFF00L;
        l |= (long) b[7] & 0xFFL;
        return l;
    }

    public static long bytesHighFirstToLong(byte[] b)
    {
        long l = ((long) b[0] << 56) & 0xFF00000000000000L;
        // 如果�?强制转�?�为long，那么默认会当作int，导致最高32�?丢失
        l |= ((long) b[1] << 48) & 0xFF000000000000L;
        l |= ((long) b[2] << 40) & 0xFF0000000000L;
        l |= ((long) b[3] << 32) & 0xFF00000000L;
        l |= ((long) b[4] << 24) & 0xFF000000L;
        l |= ((long) b[5] << 16) & 0xFF0000L;
        l |= ((long) b[6] << 8) & 0xFF00L;
        l |= (long) b[7] & 0xFFL;
        return l;
    }

    /**
     * 将一个char字符转�?��?字节数组（2个字节），b[0]存储高�?字符，大端
     *
     * @param c 字符（java char 2个字节）
     * @return 代表字符的字节数组
     */
    public static byte[] charToBytes(char c)
    {
        byte[] b = new byte[8];
        b[0] = (byte) (c >>> 8);
        b[1] = (byte) c;
        return b;
    }

    /**
     * 将一个�?�精度浮点数转�?��?字节数组（8个字节），b[0]存储高�?字符，大端
     *
     * @param d �?�精度浮点数
     * @return 代表�?�精度浮点数的字节数组
     */
    public static byte[] doubleToBytes(double d)
    {
        return longToBytes(Double.doubleToLongBits(d));
    }

    /**
     * 将一个浮点数转�?�为字节数组（4个字节），b[0]存储高�?字符，大端
     *
     * @param f 浮点数
     * @return 代表浮点数的字节数组
     */
    public static byte[] floatToBytes(float f)
    {
        return intToBytes(Float.floatToIntBits(f));
    }

    /**
     * 将一个整数转�?��?字节数组(4个字节)，b[0]存储高�?字符，大端
     *
     * @param i 整数
     * @return 代表整数的字节数组
     */
    public static byte[] intToBytes(int i)
    {
        byte[] b = new byte[4];
        b[0] = (byte) (i >>> 24);
        b[1] = (byte) (i >>> 16);
        b[2] = (byte) (i >>> 8);
        b[3] = (byte) i;
        return b;
    }

    /**
     * 将一个长整数转�?��?字节数组(8个字节)，b[0]存储高�?字符，大端
     *
     * @param l 长整数
     * @return 代表长整数的字节数组
     */
    public static byte[] longToBytes(long l)
    {
        byte[] b = new byte[8];
        b[0] = (byte) (l >>> 56);
        b[1] = (byte) (l >>> 48);
        b[2] = (byte) (l >>> 40);
        b[3] = (byte) (l >>> 32);
        b[4] = (byte) (l >>> 24);
        b[5] = (byte) (l >>> 16);
        b[6] = (byte) (l >>> 8);
        b[7] = (byte) (l);
        return b;
    }

    /**
     * 字节数组和整型的转�?�
     *
     * @param bytes 字节数组
     * @return 整型
     */
    public static int bytesToInt(byte[] bytes, int start)
    {
        int num = bytes[start] & 0xFF;
        num |= ((bytes[start + 1] << 8) & 0xFF00);
        num |= ((bytes[start + 2] << 16) & 0xFF0000);
        num |= ((bytes[start + 3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 字节数组和整型的转�?�，高�?在�?，适用于读�?�writeInt的数�?�
     *
     * @param bytes 字节数组
     * @return 整型
     */
    public static int bytesHighFirstToInt(byte[] bytes, int start)
    {
        int num = bytes[start + 3] & 0xFF;
        num |= ((bytes[start + 2] << 8) & 0xFF00);
        num |= ((bytes[start + 1] << 16) & 0xFF0000);
        num |= ((bytes[start] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 字节数组转char，高�?在�?，适用于读�?�writeChar的数�?�
     *
     * @param bytes
     * @param start
     * @return
     */
    public static char bytesHighFirstToChar(byte[] bytes, int start)
    {
        char c = (char) (((bytes[start] & 0xFF) << 8) | (bytes[start + 1] & 0xFF));
        return c;
    }

    /**
     * 读�?�float，高�?在�?
     *
     * @param bytes
     * @param start
     * @return
     */
    public static float bytesHighFirstToFloat(byte[] bytes, int start)
    {
        int l = bytesHighFirstToInt(bytes, start);
        return Float.intBitsToFloat(l);
    }

    /**
     * 无符�?�整型输出
     * @param out
     * @param uint
     * @throws IOException
     */
    public static void writeUnsignedInt(DataOutputStream out, int uint) throws IOException
    {
        out.writeByte((byte) ((uint >>> 8) & 0xFF));
        out.writeByte((byte) ((uint >>> 0) & 0xFF));
    }

    public static int convertTwoCharToInt(char high, char low)
    {
        int result = high << 16;
        result |= low;
        return result;
    }

    public static char[] convertIntToTwoChar(int n)
    {
        char[] result = new char[2];
        result[0] = (char) (n >>> 16);
        result[1] = (char) (0x0000FFFF & n);
        return result;
    }
}
