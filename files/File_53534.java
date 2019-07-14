package com.lzw.data.util;

import com.sun.istack.internal.NotNull;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @desp 数�?�工具类 （�?�上最完整的工具类，值得收�?）
 * <p>byte int short long float double char String 8�?类型相互转�?� 以�?��?�?�进制相关转�?�的工具类</p>
 * <p>版�?�归作者LZW所有，转载或使用请注明GitHub链接，谢谢�?�作�?</p>
 * @author LZW
 * @version 1.0
 * @date 2019-05-01
 * @website https://github.com/AweiLoveAndroid/CommonDevKnowledge
 * @mail lzw20099002@126.com 有任何疑问欢迎�?�邮件 或者 加微信咨询(本开�?库README.md有介�?)
 *
 */
public class DataUtil implements CharsetNameTypes {

    /**
     * 用于建立�??六进制字符的输出的�?写字符数组
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 用于建立�??六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// byte 和 byte 数组转�?��?其它类型
    /// byte �?�值范围：-128（-2^7） 到 127(2^7-1)
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * byte 转�?int
     *
     * @param byteNumber
     * @return <p>{@link #byte2Int2(byte)}</p>
     */
    public static int byte2Int(@NotNull byte byteNumber) {
        // Java的byte是有符�?�，通过 &0xFF转为无符�?�
        return byteNumber & 0xFF;
    }


    /**
     * byte 转�?int
     *
     * @param byteNumber
     * @return <p>{@link #byte2Int(byte)}</p>
     */
    public static int byte2Int2(@NotNull byte byteNumber) {
        return Integer.parseInt(byte2String(byteNumber));
    }


    /**
     * byte数组转�?short
     *
     * @param byteNumber byte数组
     * @return short类型
     * 该方法在�?些情况会有精度丢失以�?�bug, 请谨慎使用。
     */
    public static Short byte2Short(@NotNull byte byteNumber) {
        return (short) byteNumber;
    }


    public static long byte2Long(@NotNull byte byteNumber) {
        return string2Long(byte2String(byteNumber));
    }

    public static float byte2Float(@NotNull byte byteNumber) {
        return string2Float(byte2String(byteNumber));
    }

    public static double byte2Double(@NotNull byte byteNumber) {
        return string2Double(byte2String(byteNumber));
    }

    //    public static char byte2Char(@NotNull byte byteNumber) {
    ////        return string2c(byte2String(byteNumber));
    ////    }


    public static String byte2String(@NotNull byte byteNumber) {
        return String.valueOf(byteNumber);
    }

    /**
     * byte数组转�?short
     *
     * @param byteNumber byte数组
     * @return short类型
     */
    public static short byte2Short(@NotNull byte[] byteNumber) {
        short result = 0;
        for (int i = 0; i < 2; i++) {
            result <<= 8; // �?�?就是 l = l << 8
            result |= (byteNumber[i] & 0xff); // l = l | (b[i]&0xff)
        }
        return result;
    }

    /**
     * byte数组转int
     * 10进制
     *
     * @param byteArr byte 数组
     * @return int类型
     * {@link #byteArr2Int2(byte[])} 这是�?�一�?写法，其实效果是一样的
     */
    public static int byteArr2Int(@NotNull byte[] byteArr) {
        return (byteArr[0] & 0xff) << 24
                | (byteArr[1] & 0xff) << 16
                | (byteArr[2] & 0xff) << 8
                | (byteArr[3] & 0xff);
    }


    /**
     * byte数组转�?int
     *
     * @param byteArr byte 数组
     * @return int类型
     * {@link #byteArr2Int(byte[])} 这是�?�一�?写法，其实效果是一样的
     */
    public static int byteArr2Int2(@NotNull byte[] byteArr) {
        // �?�一�?写法，其实效果是一样的
        int num = byteArr[3] & 0xFF;
        num |= ((byteArr[2] << 8) & 0xFF00);
        num |= ((byteArr[1] << 16) & 0xFF00);
        num |= ((byteArr[0] << 24) & 0xFF00);
        return num;
    }

    /**
     * byte[] 转 char
     *
     * @param byteArr byte数组
     * @return char字符
     */
    public static char byteArr2Char(@NotNull byte[] byteArr) {
        char c = (char) (((byteArr[0] & 0xFF) << 8) | (byteArr[1] & 0xFF));
        return c;
    }


    /**
     * byte[] 转 char[]  （10进制）
     *
     * @param byteArr byte数组
     * @return char数组
     */
    public static char[] byteArr2CharArr(@NotNull byte[] byteArr) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(byteArr.length);
        bb.put(byteArr);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }


    /**
     * byte数组 转�? 16进制char数组（默认转�?��?�?写形�?）
     *
     * @param byteArr byte数组
     * @return 16进制char数组
     */
    public static char[] byteArr2CharArrHex(@NotNull byte[] byteArr) {
        return byteArr2CharArrHex(byteArr, true);
    }

    /**
     * byte数组 转�? 16进制char数组
     *
     * @param byteArr     byte数组
     * @param toLowerCase true：转�?��?�?写格�? ，false：转�?��?大写格�?
     * @return 16进制char数组
     */
    public static char[] byteArr2CharArrHex(@NotNull byte[] byteArr, @NotNull boolean toLowerCase) {
        return byteArr2CharArrHex(byteArr, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * byte数组 转�? 16进制char数组（默认转�?��?�?写形�?）
     *
     * @param byteArr  byte数组
     * @param toDigits 用于控制输出的char[]
     * @return 16进制char数组
     */
    private static char[] byteArr2CharArrHex(@NotNull byte[] byteArr, @NotNull char[] toDigits) {
        int index = 0;
        char[] hexChar = new char[byteArr.length * 2];//相当于： char[] out = new char[len << 1];
        for (int i = 0; i < byteArr.length; i++) {
            hexChar[index++] = toDigits[byteArr[i] >> 4 & 0xF];
            hexChar[index++] = toDigits[byteArr[i] & 0xF];
            // 无符�?��?移
            hexChar[index++] = toDigits[(0xF0 & byteArr[i]) >>> 4];
            // 有符�?�的�?移
            // hexChar[j++] = toDigits[byteArr[i] >> 4 & 0xF];
            hexChar[index++] = toDigits[0x0F & byteArr[i]];
        }
        return hexChar;
    }


    /**
     * byte 数组 转�?��?String字符串
     * 10进制
     *
     * @param byteArr byte 数组
     * @return String字符串
     */
    public static String byteArr2String(@NotNull byte[] byteArr) {
        try {
            return new String(byteArr, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * byte 数组 转�?��? 16进制的String字符串
     *
     * @param byteArr byte 数组
     * @return 16进制的String字符串
     * @deprecated 该方法在�?些情况会有bug, 请使用{@link #byteArr2StringHex2(byte[])} 替代。
     */
    @Deprecated
    public static String byteArr2StringHex(@NotNull byte[] byteArr) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (byteArr == null || byteArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < byteArr.length; i++) {
            int v = byteArr[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 将字节数组转�?�为�??六进制字符串（默认转�?��?�?写形�?）
     *
     * @param byteArr byte 数组
     * @return 16进制的String字符串
     */
    public static String byteArr2StringHex2(@NotNull byte[] byteArr) {
        return byteArr2StringHex2(byteArr, true);
    }


    /**
     * 将字节数组转�?�为�??六进制字符串
     *
     * @param data        byte[]
     * @param toLowerCase 是�?�转�?��?�?写形�?  <code>true</code> 转�?��?�?写格�? ， <code>false</code> 转�?��?大写格�?
     * @return �??六进制String
     */
    public static String byteArr2StringHex2(@NotNull byte[] data, @NotNull boolean toLowerCase) {
        return byteArr2StringHex2(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转�?�为�??六进制字符串
     *
     * @param data     byte[]
     * @param toDigits 用于控制输出的char[]
     * @return �??六进制String
     */
    private static String byteArr2StringHex2(@NotNull byte[] data, @NotNull char[] toDigits) {
        return charArr2StringHex(byteArr2CharArrHex(data, toDigits));
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// short 和 short 数组转�?��?其它类型
    /// short �?�值范围：-32768（-2^15）到 32767（2^15 - 1）
    ////////////////////////////////////////////////////////////////////////////////


    /**
     * short类型转byte数组
     *
     * @param shortNumber
     * @return
     */
    public static byte[] short2Byte(@NotNull short shortNumber) {
        byte[] b = new byte[2];
        for (int i = 0;
                i < 2;
                i++) {
            int offset = 16 - (i + 1) * 8; //因为byte�?�4个字节，所以�?计算�??移�?
            b[i] = (byte) ((shortNumber >> offset) & 0xff); //把16�?分为2个8�?进行分别存储
        }
        return b;
    }

    public static int short2Int(@NotNull short shortNumber) {
        return Integer.parseInt(short2String(shortNumber));
    }

    public static long short2Long(@NotNull short shortNumber) {
        return Long.parseLong(short2String(shortNumber));
    }

    public static float short2Float(@NotNull short shortNumber) {
        return Float.parseFloat(short2String(shortNumber));
    }

    public static double short2Double(@NotNull short shortNumber) {
        return Double.parseDouble(short2String(shortNumber));
    }

    public static String short2String(@NotNull short shortNumber) {
        return String.valueOf(shortNumber);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// int 和 int 数组转�?��?其它类型
    /// int �?�值范围：-2,147,483,648（-2^31） 到 2,147,483,647（2^31 - 1）
    ////////////////////////////////////////////////////////////////////////////////

    public static byte int2Byte(@NotNull int intNumber) {
        // 强转会�?�失精度
        return (byte) intNumber;
    }


    /**
     * int类型 转�?��? String类型
     * 此方法过时，请使用 {@link #int2String2(int)} 或者 {@link #int2String3(int)}  这个方法替代
     * <ul>
     * int类型 转�?��? String类型的�?�外两�?方�?请查看：
     * <li><p>{@link #int2String(int)}</p> </li>
     * <li><p>{@link #int2String2(int)}</p> </li>
     * </ul>
     *
     * @param intNumber int类型
     * @return String类型
     */
    @Deprecated
    public static String int2String(@NotNull int intNumber) {
        return intNumber + "";
    }

    /**
     * int类型 转�?��? String类型
     * <p>也�?�以把 �??六进制数字的基本数�?�类型 转�? String，例如：int2String2(0xFF);</p>
     * <p>
     * <ul>
     * int类型 转�?��? String类型的�?�外两�?方�?请查看：
     * <li><p>{@link #int2String(int)}</p> </li>
     * <li><p>{@link #int2String3(int)}</p> </li>
     * </ul>
     * <ul>�?�外两�?16进制转10进制的方�?请查看：
     * <li><p>{@link #stringHex2StringDecimal(String)}</p> </li>
     * <li><p>{@link #stringHex2IntDecimal(String)}</p> </li>
     * </ul>
     * </p>
     *
     * @param intNumber int类型
     * @return String类型
     */
    public static String int2String2(@NotNull int intNumber) {
        return Integer.toString(intNumber);
    }


    /**
     * 10进制 转 2进制
     *
     * @param data 10进制 int类型
     * @return 16进制 字符串
     */
    public static String intDecimal2StringBinary(@NotNull int data) {
        return Integer.toBinaryString(data);
    }

    /**
     * 10进制 转 8进制
     *
     * @param data 10进制 int类型
     * @return 16进制 字符串
     */
    public static String intDecimal2StringOctal(@NotNull int data) {
        return Integer.toOctalString(data);
    }

    /**
     * 10进制 转 16进制
     *
     * @param data 10进制 int类型
     * @return 16进制 字符串
     */
    public static String intDecimal2StringHex(@NotNull int data) {
        return Integer.toHexString(data);
    }



    /**
     * int类型 转�?��? String类型
     * <ul>
     * int类型 转�?��? String类型的�?�外两�?方�?请查看：
     * <li><p>{@link #int2String(int)}</p> </li>
     * <li><p>{@link #int2String2(int)}</p> </li>
     * </ul>
     *
     * @param intNumber int类型
     * @return String类型
     */
    public static String int2String3(@NotNull int intNumber) {
        return String.valueOf(intNumber);
    }

    /**
     * int转byte数组
     *
     * @param intNumber int类型
     * @return byte数组
     * <p>{@link #int2ByteArr2(int)} 这是�?�一�?写法，其实效果是一样的</p>
     */
    public static byte[] int2ByteArr(@NotNull int intNumber) {
        // int是4个字节 所以�?4个byte
        byte[] b = new byte[4];
        b[0] = (byte) (intNumber & 0xff);
        b[1] = (byte) ((intNumber >> 8) & 0xff);
        b[2] = (byte) ((intNumber >> 16) & 0xff);
        b[3] = (byte) ((intNumber >> 24) & 0xff);
        return b;
    }

    /**
     * int转byte数组
     *
     * @param intNumber int类型
     * @return byte数组
     * {@link #int2ByteArr(int)} 这是�?�一�?写法，其实效果是一样的
     */
    public static byte[] int2ByteArr2(@NotNull int intNumber) {
        // �?�一�?写法 效果是一样的
        byte[] b = new byte[4];
        b[0] = (byte) ((intNumber >> 24) & 0xFF);
        b[1] = (byte) ((intNumber >> 16) & 0xFF);
        b[2] = (byte) ((intNumber >> 8) & 0xFF);
        b[3] = (byte) (intNumber & 0xFF);
        return b;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// long 和 long 数组转�?��?其它类型
    /// long �?�值范围：-9,223,372,036,854,775,808（-2^63） 到 9,223,372,036,854,775,807（2^63 -1）
    //////////////////////////////////////////////////////////////////////////////////////////////


    public static byte long2Byte(@NotNull long longNumber) {
        return string2Byte(long2String(longNumber));
    }

    public static short long2Short(@NotNull long longNumber) {
        return string2Short(long2String(longNumber));
    }

    public static int long2Int(@NotNull long longNumber) {
        return Integer.parseInt(long2String(longNumber));
    }

    public static float long2Float(@NotNull long longNumber) {
        return string2Float(long2String(longNumber));
    }

    public static double long2Double(@NotNull long longNumber) {
        return Double.parseDouble(long2String(longNumber));
    }

    public static String long2String(@NotNull long longNumber) {
        return String.valueOf(longNumber);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// float 和 float 数组转�?��?其它类型
    /// �?�精度�?32�?
    ////////////////////////////////////////////////////////////////////////////////


    public static byte float2Byte(@NotNull float floatNumber) {
        return 0;
    }

    public static int float2Short(@NotNull float floatNumber) {
        return 0;
    }

    public static long float2Int(@NotNull float floatNumber) {
        return 0;
    }

    public static long float2Long(@NotNull float floatNumber) {
        return Long.parseLong(float2String(floatNumber));
    }

    public static double float2Double(@NotNull float floatNumber) {
        return Double.parseDouble(float2String(floatNumber));
    }

    public static String float2String(@NotNull float floatNumber) {
        return String.valueOf(floatNumber);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// double 和 double 数组转�?��?其它类型
    /// �?�精度�?64�?
    ////////////////////////////////////////////////////////////////////////////////


    public static byte double2Byte(@NotNull short doubleNumber) {
        return 0;
    }

    public static int double2Short(@NotNull short doubleNumber) {
        return 0;
    }

    public static long double2Int(@NotNull short doubleNumber) {
        return 0;
    }

    public static long double2Long(@NotNull short doubleNumber) {
        return Long.parseLong(double2String(doubleNumber));
    }

    public static float double2Float(@NotNull short doubleNumber) {
        return 0;
    }

    public static String double2String(@NotNull short doubleNumber) {
        return String.valueOf(doubleNumber);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// String 和 String 数组转�?��?其它类型
    ///
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * String 转�? byte
     * [tips]: 1.
     *
     * @param data
     * @return
     */
    public static byte string2Byte(@NotNull String data) {
        return string2Byte(data, 10);
    }

    /**
     * String 转�? byte
     * [tips]: 1.�?能超过byte�?�值范围
     * byte�?�值范围（10进制）是：-128 到 127
     * �?�算�?2进制是：-10000000 到  1111111
     * �?�算�?16进制是：-80 到 7f
     *
     * @param data
     * @param radix 进制 比如2�?10�?16 默认为10进制
     * @return
     */
    public static byte string2Byte(@NotNull String data, @NotNull int radix) {
        return Byte.parseByte(data, radix);
    }

    /**
     * String 转�? byte
     * 将字符串解�?为字节。
     * 接�?��??进制�?�??六进制和八进制数:
     * �??六进制：0x  0X
     * 八进制：0
     * �??进制：-  +
     *
     * @param data
     * @return
     */
    public static byte string2Byte2(@NotNull String data) {
        return Byte.decode(data);
    }

    public static short string2Short(@NotNull String data) {
        return Short.parseShort(data);
    }

    /**
     * String转int类型
     * �?注�?】： 中文�?英文�?中英文混�?��?标点符�?��?甚至超过byte范围都会报错。
     * unicode�?也�?行 ，例如：u+4E00�?u+4E00�?4E00
     *
     * @param data
     * @return 转�?无符�?�的 int 类型
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    public static int string2Int(@NotNull String data) {
        return string2Int(data, false);
    }


    /**
     * String转int类型
     * �?注�?】： 中文�?英文�?中英文混�?��?标点符�?��?甚至超过byte范围都会报错。
     * unicode�?也�?行 ，例如：u+4E00�?u+4E00�?4E00
     *
     * @param data
     * @param isUnsignedInt 是�?��?转�?无符�?�的int类型
     * @return int
     * <p>{@link #string2Int(String)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    public static int string2Int(@NotNull String data, @NotNull boolean isUnsignedInt) {
        return string2Int(data, isUnsignedInt, 10);
    }

    /**
     * String转int类型（2进制）
     *
     * @param data
     * @param isUnsignedInt
     * @return String字符串
     * {@link #string2Int(String)}
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    public static int string2IntBinary(@NotNull String data, @NotNull boolean isUnsignedInt) {
        return string2Int(data, isUnsignedInt, 2);
    }

    /**
     * String转int类型（8进制）
     *
     * @param data
     * @param isUnsignedInt
     * @return String字符串
     * <p>{@link #string2Int(String)}</p>
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    public static int string2IntOctal(@NotNull String data, @NotNull boolean isUnsignedInt) {
        return string2Int(data, isUnsignedInt, 8);
    }

    /**
     * String转int类型（16进制）
     *
     * @param data
     * @param isUnsignedInt
     * @return int类型
     * <p>{@link #string2Int(String)}</p>
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    public static int string2IntHexadecimal(@NotNull String data, @NotNull boolean isUnsignedInt) {
        return string2Int(data, isUnsignedInt, 16);
    }

    /**
     * String转int类型
     *
     * @param data
     * @param isUnsignedInt
     * @param radix         进制 比如2（Binary）�?8（Octal）�?10（Decimal）�?16（Hexadecimal） ，默认为10进制
     * @return int
     * <p>{@link #string2Int(String)}</p>
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     * <p>{@link #string2Int2(String)}</p>
     */
    private static int string2Int(@NotNull String data, @NotNull boolean isUnsignedInt, @NotNull int radix) {
        if (isUnsignedInt == false) {
            return Integer.parseInt(data, radix);
        } else {
            return Integer.parseUnsignedInt(data, radix);
        }
    }


    /**
     * String转int类型
     *
     * @param data String类型
     * @return int类型
     * <p>{@link #string2Int(String)}</p>
     * <p>{@link #string2Int(String, boolean)}</p>
     * <p>{@link #string2IntBinary(String, boolean)}</p>
     * <p>{@link #string2IntOctal(String, boolean)}</p>
     * <p>{@link #string2IntHexadecimal(String, boolean)}</p>
     */
    public static int string2Int2(@NotNull String data) {
        return Integer.valueOf(data).intValue();
    }


    /**
     * String转long类型
     *
     * @param data
     * @return 转�?无符�?�的 long 类型
     */
    public static long string2Long(@NotNull String data) {
        return Long.parseLong(data);
    }

    /**
     * String转 long 类型
     *
     * @param data
     * @param isUnsignedLong 是�?��?转�?无符�?�的 long 类型
     * @return 转�?的 long 类型
     */
    public static long string2Long(@NotNull String data, @NotNull boolean isUnsignedLong) {
        if (isUnsignedLong == false) {
            return Long.parseLong(data);
        } else {
            return Long.parseUnsignedLong(data);
        }
    }

    public static float string2Float(@NotNull String data) {
        return Float.parseFloat(data);
    }

    public static double string2Double(@NotNull String data) {
        return Double.parseDouble(data);
    }

    public static boolean string2Boolean(@NotNull String data) {
        return Boolean.parseBoolean(data);
    }

    //////////////////////////////////////////////////


    //�??六进制转�??进制，例如：0xFFFF

    //    Integer.valueOf("FFFF",16).toString();　　//valueOf()方法返回Integer类型，调用toString()返回字符串
    //    Integer.parseInt("FFFF",16);　　//返回int基本数�?�类型
    //    Integer.toString(0xFFFF);　　//该方法�?�直接传入表示�??六进制数字的基本数�?�类型，方法返回字符串
    //
    //    //八进制转�??进制，例如：017
    //    Integer.valueOf("17",8).toString();　　//valueOf()方法返回Integer类型，调用toString()返回字符串
    //    Integer.parseInt("17",8);　　//返回int基本数�?�类型
    //    Integer.toString(017);　　//该方法�?�直接传入表示八进制数字的基本数�?�类型，方法返回字符串
    //
    //    //二进制转�??进制，例如：0101
    //    Integer.valueOf("0101",2).toString();　　//valueOf()方法返回Integer类型，调用toString()返回字符串
    //    Integer.parseInt("0101",2);　　//返回int基本数�?�类型

    // 2（Binary）�?8（Octal）�?10（Decimal）�?16（Hexadecimal）

    //�??进制转其他进制

//Integer.toHexString(10);　　//将10转�?�为�??六进制，返回字符串类型
//
//            Integer.toOctalString(10);　　//将10转为八进制，返回字符串类型
//
//            Integer.toBinaryString(10);　　//将10转为二进制，返回字符串类型







    /**
     * 16进制 转 10进制
     *
     * <p>示例：stringHex2StringDecimal("FFFF");</p>
     *
     * <ul>�?�外两�?16进制转10进制的方�?请查看：
     * <li>@see #stringHex2IntDecimal(String){@link #stringHex2IntDecimal(String)}</li>
     * <li>@see #int2String2(int){@link #int2String2(int)}</li>
     * </ul>
     *
     * @param data 10进制 字符串
     * @return 16进制 字符串
     */
    public static String stringHex2StringDecimal(@NotNull String data) {
        return Integer.valueOf(data, 16).toString();
    }

    /**
     * 16进制 转 10进制
     * 示例：stringHex2IntDecimal("FFFF");
     *
     * @param data 10进制 字符串
     * @return 16进制 int类型
     * �?�外两�?16进制转10进制的方�?请查看：
     * <p>{@link #stringHex2StringDecimal(String)}</p>
     * <p>{@link #stringHex2IntDecimal(String)}</p>
     * <p>{@link #int2String2(int)}</p>
     */
    public static int stringHex2IntDecimal(@NotNull String data) {
        return Integer.parseInt(data, 16);
    }





    /**
     * 8进制 转 10进制
     *
     * <p>示例：stringOctal2StringDecimal("121");</p>
     *
     * @param data 8进制 字符串
     * @return 10进制 字符串
     */
    public static String stringOctal2StringDecimal(@NotNull String data) {
        return Integer.valueOf(data, 8).toString();
    }

    /**
     * 8进制 转 10进制
     * 示例：stringOctal2IntDecimal("121");
     *
     * @param data 8进制 字符串
     * @return 10进制 int类型
     */
    public static int stringOctal2IntDecimal(@NotNull String data) {
        return Integer.parseInt(data, 8);
    }


    /**
     * 2进制 转 10进制
     *
     * @param data 10进制 字符串
     * @return 16进制 字符串
     */
    public static String stringBinary2StringDecimal(@NotNull String data) {
        return Integer.valueOf(data, 2).toString();
    }

    /**
     * 2进制 转 10进制
     *
     * @param data 10进制 字符串
     * @return 16进制 int类型
     */
    public static int stringBinary2IntDecimal(@NotNull String data) {
        return  Integer.parseInt(data,2);
    }


    ///////////////////////////////////////////////////

    /**
     * String字符串转�? byte 数组
     * 默认为utf-8编�?格�?
     *
     * @param data String字符串
     * @return byte 数组
     * 例如：DataUtil.stringHex2ByteArr("好");
     */
    public static byte[] string2ByteArr(@NotNull String data) {
        try {
            return string2ByteArr(data, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String字符串转�? byte 数组
     *
     * @param data             String字符串
     * @param charsetNametypes 编�?格�? 默认为utf-8
     * @return byte 数组
     * 例如：DataUtil.string2ByteArrWithCharset("好","utf-16");
     */
    public static byte[] string2ByteArr(@NotNull String data, @NotNull String charsetNametypes) throws Exception {
        if (charsetNametypes == null || charsetNametypes.equals("")) {
            return data.getBytes("utf-8");
        } else {
            if (charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF8) ||
                    charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF16)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF32)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UNICODE)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GBK)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GB2312)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GB18030)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_ASCII)
                    || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_ISO_8859_1)) {
                return data.getBytes(charsetNametypes);
            } else {
                throw new IllegalAccessException("编�?格�?�?正确，请检查编�?格�?");
            }
        }
    }


    /**
     * 16进制的String字符串 转�?��?  byte 数组
     *
     * @param data 16进制的String字符串
     * @return byte 数组
     * 例如：DataUtil.stringHex2ByteArr("e4bda0e5a5bd");
     */
    public static byte[] stringHex2ByteArr(@NotNull String data) {
        try {
            if(isHexadecimal(data)!=true){
                throw new IllegalAccessException("该方法�?支�?传入的此类型字符串，请使用 string2ByteArr(String)方法替代");
            }
            int len = data.length();
            byte[] d = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                // 两�?一组，表示一个字节,把这样表示的16进制字符串，还原�?一个进制字节
                d[i / 2] = (byte) ((Character.digit(data.charAt(i), 16) << 4) + Character
                        .digit(data.charAt(i + 1), 16));
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// char 和 char 数组 数组转�?��?其它类型
    ///
    ////////////////////////////////////////////////////////////////////////////////


    public static String char2String(@NotNull char charNumber) {
        return String.valueOf(charNumber);
    }

    /**
     * char 转�?��? byte[] 数组
     *
     * @param charNumber
     * @return
     */
    public static byte[] char2ByteArr(@NotNull char charNumber) {
        byte[] b = new byte[2];
        b[0] = (byte) ((charNumber & 0xFF00) >> 8);
        b[1] = (byte) (charNumber & 0xFF);
        return b;
    }

    /**
     * 将�??六进制字符转�?��?一个整数
     *
     * @param ch    �??六进制char
     * @param index �??六进制字符在字符数组中的�?置
     * @return 一个整数
     * @throws RuntimeException 当ch�?是一个�?�法的�??六进制字符时，抛出�?行时异常
     */
    public static int charHex2Int(@NotNull char ch, @NotNull int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }


    /**
     * char[] 转�? byte[]  （10进制）
     *
     * @param charArr char数组
     * @return byte数组
     */
    public static byte[] charArr2ByteArr(@NotNull char[] charArr) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(charArr.length);
        cb.put(charArr);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /**
     * 将�??六进制字符数组转�?�为字节数组
     *
     * @param charArr �??六进制char[]
     * @return byte[] 字节数组
     * @throws RuntimeException 如果�?�??六进制字符数组是一个奇怪的长度，将抛出�?行时异常
     */
    public static byte[] charArrHex2ByteArr(@NotNull char[] charArr) {
        int len = charArr.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = charHex2Int(charArr[j], j) << 4;
            j++;
            f = f | charHex2Int(charArr[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * 将char数组转�?��?�??六进制字符串
     *
     * @return
     */
    public static String charArr2StringHex(@NotNull char[] charArr) {
        return new String(charArr);
    }

    ////////////////////////////////////////////////////////////////////////////////
    ///
    /// 判断是�?�是2进制�?8进制�?16进制
    /// 2（Binary）�?8（Octal）�?10（Decimal）�?16（Hexadecimal）
    /// 2进制：0和1
    /// 8进制：0开头，例如：0123 （8进制没有8和9）
    /// 16进制：0x开头，例如：0x23
    ////////////////////////////////////////////////////////////////////////////////


    /**
     * 判断是�?�是2进制
     *
     * @param data
     * @return
     */
    public static boolean isBinary(String data) {
        String regex = "[0-1]+$";
        if (data.matches(regex)) {
            System.out.println(data.toUpperCase() + "是2进制数");
            return true;
        } else {
            System.out.println(data.toUpperCase() + "�?是2进制数");
            return false;
        }
    }

    /**
     * 判断是�?�是8进制
     *
     * @param data
     * @return
     */
    public static boolean isOctal(String data) {
        String substring;
        String regex = "[0-7]+$";
        if (data.startsWith("0")) {
            substring = data.substring(1);
            if (substring.matches(regex)) {
                System.out.println(data + "是8进制数");
                return true;
            } else {
                System.out.println(data + "�?是8进制数");
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断是�?�是10进制
     *
     * @param data
     * @return
     */
    public static boolean isDecimal(String data) {
        if (data.startsWith("0") || data.startsWith("0x") || data.startsWith("0X")) {
            System.out.println(data + "�?是10进制数");
            return false;
        }
        String regex = "[0-9]+$";
        if (data.matches(regex)) {
            System.out.println(data + "是10进制数");
            return true;
        } else {
            System.out.println(data + "�?是10进制数");
            return false;
        }
    }


    /**
     * 判断是�?�是16进制
     *
     * @param data
     * @return
     */
    public static boolean isHexadecimal(String data) {
        String regex = "^[A-Fa-f0-9]+$";
        if (data.matches(regex)) {
            System.out.println(data + "是16进制数");
            return true;
        } else {
            System.out.println(data + "�?是16进制数");
            return false;
        }
    }
}
