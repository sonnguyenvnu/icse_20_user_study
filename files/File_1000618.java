package org.nutz.lang.random;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.nutz.img.Colors;
import org.nutz.img.Fonts;
import org.nutz.lang.Strings;

/**
 * 对�?机数�?作的�?装
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public abstract class R {

    static Random r = new Random();

    /**
     * 根�?�一个范围，生�?一个�?机的整数
     * 
     * @param min
     *            最�?值（包括）
     * @param max
     *            最大值（包括）
     * @return �?机数
     */
    public static int random(int min, int max) {
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * 根�?�一个长度范围，生�?一个�?机的字符串，字符串内容为 [0-9a-zA-Z_]
     * 
     * @param min
     *            最�?值（包括）
     * @param max
     *            最大值（包括）
     * @return �?机字符串
     */

    public static StringGenerator sg(int min, int max) {
        return new StringGenerator(min, max);
    }

    /**
     * 生�?一个确定长度的�?机字符串，字符串内容为 [0-9a-zA-Z_]
     * 
     * @param len
     *            字符串长度
     * @return �?机字符串
     */
    public static StringGenerator sg(int len) {
        return new StringGenerator(len, len);
    }

    private static final char[] _UU64 = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] _UU32 = "0123456789abcdefghijklmnopqrstuv".toCharArray();

    /**
     * @return 64进制表示的紧凑格�?的 UUID
     * @see org.nutz.lang.random.R#UU64(UUID)
     */
    public static String UU64() {
        return UU64(UUID.randomUUID());
    }

    /**
     * 返回一个 UUID ，并用 64 进制转�?��?紧凑形�?的字符串，内容为 [\\-0-9a-zA-Z_]
     * <p>
     * 比如一个类似下�?�的 UUID:
     * 
     * <pre>
     * a6c5c51c-689c-4525-9bcd-c14c1e107c80
     * 一共 128 �?，分�?�L64 和 R64，分为为两个 64�?数（两个 long）
     *    > L = uu.getLeastSignificantBits();
     *    > R = uu.getMostSignificantBits();
     * 而一个 64 进制数，是 6 �?，因此我们�?�值的顺�?是
     * 1. 从L64�?�?�10次，�?次�?�6�?
     * 2. 从L64�?�?�最�?�的4�? ＋ R64�?头2�?拼上
     * 3. 从R64�?�?�10次，�?次�?�6�?
     * 4. 剩下的两�?最�?��?�
     * 这样，就能用一个 22 长度的字符串表示一个 32 长度的UUID，压缩了 1/3
     * </pre>
     * 
     * @param uu
     *            UUID 对象
     * @return 64进制表示的紧凑格�?的 UUID
     */
    public static String UU64(UUID uu) {
        int index = 0;
        char[] cs = new char[22];
        long L = uu.getMostSignificantBits();
        long R = uu.getLeastSignificantBits();
        long mask = 63;
        // 从L64�?�?�10次，�?次�?�6�?
        for (int off = 58; off >= 4; off -= 6) {
            long hex = (L & (mask << off)) >>> off;
            cs[index++] = _UU64[(int) hex];
        }
        // 从L64�?�?�最�?�的4�? ＋ R64�?头2�?拼上
        int l = (int) (((L & 0xF) << 2) | ((R & (3 << 62)) >>> 62));
        cs[index++] = _UU64[l];
        // 从R64�?�?�10次，�?次�?�6�?
        for (int off = 56; off >= 2; off -= 6) {
            long hex = (R & (mask << off)) >>> off;
            cs[index++] = _UU64[(int) hex];
        }
        // 剩下的两�?最�?��?�
        cs[index++] = _UU64[(int) (R & 3)];
        // 返回字符串
        return new String(cs);
    }

    /**
     * 从一个 UU64 �?��?回一个 UUID 对象
     * 
     * @param uu64
     *            64进制表示的 UUID, 内容为 [\\-0-9a-zA-Z_]
     * @return UUID 对象
     */
    public static UUID fromUU64(String uu64) {
        String uu16 = UU16FromUU64(uu64);
        return UUID.fromString(UU(uu16));
    }

    public static String UU32(UUID uu) {
        StringBuilder sb = new StringBuilder();
        long m = uu.getMostSignificantBits();
        long l = uu.getLeastSignificantBits();
        for (int i = 0; i < 13; i++) {
            sb.append(_UU32[(int) (m >> ((13 - i - 1) * 5)) & 31]);
        }
        for (int i = 0; i < 13; i++) {
            sb.append(_UU32[(int) (l >> ((13 - i - 1)) * 5) & 31]);
        }
        return sb.toString();
    }

    public static String UU32() {
        return UU32(UUID.randomUUID());
    }

    public static UUID fromUU32(String u32) {
        return new UUID(parseUnsignedLong(u32.substring(0, 13), 32),
                        parseUnsignedLong(u32.substring(13), 32));
    }

    public static long parseUnsignedLong(String s, int radix) {
        int len = s.length();
        long first = Long.parseLong(s.substring(0, len - 1), radix);
        int second = Character.digit(s.charAt(len - 1), radix);
        return first * radix + second;
    }

    /**
     * 将紧凑格�?的 UU16 字符串�?��?标准 UUID 格�?的字符串
     * 
     * @param uu16
     * @return 标准 UUID 字符串
     */
    public static String UU(String uu16) {
        StringBuilder sb = new StringBuilder();
        sb.append(uu16.substring(0, 8));
        sb.append('-');
        sb.append(uu16.substring(8, 12));
        sb.append('-');
        sb.append(uu16.substring(12, 16));
        sb.append('-');
        sb.append(uu16.substring(16, 20));
        sb.append('-');
        sb.append(uu16.substring(20));
        return sb.toString();
    }

    private static final char[] _UU16 = "0123456789abcdef".toCharArray();

    /**
     * 将一个 UU64 表示的紧凑字符串，�?��? UU16 表示的字符串
     * 
     * <pre>
     * �?次�?�2个字符，�?��?�?3个byte，�?�?10次， 最�?�一次，是用最�?�2个字符，�?��?回2个byte </prev>
     * 
     * @param uu64
     *            uu64 64进制表示的 UUID, 内容为 [\\-0-9a-zA-Z_]
     * @return 16进制表示的紧凑格�?的 UUID
     */
    public static String UU16FromUU64(String uu64) {
        byte[] bytes = new byte[32];
        char[] cs = uu64.toCharArray();
        int index = 0;
        // �?次�?�2个字符，�?��?�?3个byte，�?�?10次，
        for (int i = 0; i < 10; i++) {
            int off = i * 2;
            char cl = cs[off];
            char cr = cs[off + 1];
            int l = Arrays.binarySearch(_UU64, cl);
            int r = Arrays.binarySearch(_UU64, cr);
            int n = (l << 6) | r;
            bytes[index++] = (byte) ((n & 0xF00) >>> 8);
            bytes[index++] = (byte) ((n & 0xF0) >>> 4);
            bytes[index++] = (byte) (n & 0xF);
        }
        // 最�?�一次，是用最�?�2个字符，�?��?回2个byte
        char cl = cs[20];
        char cr = cs[21];
        int l = Arrays.binarySearch(_UU64, cl);
        int r = Arrays.binarySearch(_UU64, cr);
        int n = (l << 2) | r;
        bytes[index++] = (byte) ((n & 0xF0) >>> 4);
        bytes[index++] = (byte) (n & 0xF);

        // 返回 UUID 对象
        char[] names = new char[32];
        for (int i = 0; i < bytes.length; i++)
            names[i] = _UU16[bytes[i]];
        return new String(names);
    }

    /**
     * @return 16进制表示的紧凑格�?的 UUID
     */
    public static String UU16() {
        return UU16(UUID.randomUUID());
    }

    /**
     * @param uu
     *            UUID 对象
     * @return 16进制表示的紧凑格�?的 UUID
     */
    public static String UU16(UUID uu) {
        return Strings.alignRight(Long.toHexString(uu.getMostSignificantBits()), 16, '0')
               + Strings.alignRight(Long.toHexString(uu.getLeastSignificantBits()), 16, '0');
    }

    /**
     * 返回指定长度由�?机数字+�?写字�?组�?的字符串
     * 
     * @param length
     *            指定长度
     * @return �?机字符串
     */
    public static String captchaChar(int length) {
        return captchaChar(length, false);
    }

    /**
     * 返回指定长度�?机数字+字�?(大�?写�?感)组�?的字符串
     * 
     * @param length
     *            指定长度
     * @param caseSensitivity
     *            是�?�区分大�?写
     * @return �?机字符串
     */
    public static String captchaChar(int length, boolean caseSensitivity) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();// �?机用以下三个�?机生�?器
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(caseSensitivity ? 3 : 2);
            // 目的是�?机选择生�?数字，大�?写字�?
            switch (index) {
            case 0:
                data = randdata.nextInt(10);// 仅仅会生�?0~9, 0~9的ASCII为48~57
                sb.append(data);
                break;
            case 1:
                data = randdata.nextInt(26) + 97;// �?�?�?�会产生ASCII为97~122(a-z)之间的整数,
                sb.append((char) data);
                break;
            case 2: // caseSensitivity为true的时候, �?会有大写字�?
                data = randdata.nextInt(26) + 65;// �?�?�?�会产生ASCII为65~90(A~Z)之间的整数
                sb.append((char) data);
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 返回指定长度�?机数字组�?的字符串
     * 
     * @param length
     *            指定长度
     * @return �?机字符串
     */
    public static String captchaNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 设置�?机数生�?器的实例, 例如 SecureRandom
     * 
     * @param r
     *            �?机生�?器,�?�?�以是null
     */
    public static void setR(Random r) {
        if (r == null)
            throw new NullPointerException("Random MUST NOT NULL");
        R.r = r;
    }

    /**
     * 获�?�一个�?机颜色
     * 
     * @return 颜色
     */
    public static Color color() {
        return Colors.randomColor();
    }

    /**
     * 获�?�一个�?机颜色，格�?为“rgb(12, 25, 33)�?
     * 
     * @return rgb格�?字符串
     */
    public static String colorRGB() {
        return Colors.toRGB(Colors.randomColor());
    }

    /**
     * 获�?�一个�?机字体，�?能�?�?�?�英文字符产生乱�?问题
     * 
     * @param style
     *            字体样�? 支�?：Font.PLAIN Font.BOLD Font.ITALIC
     * @param size
     *            字体大�?
     * @return 字体
     */
    public static Font font(int style, int size) {
        return Fonts.random(style, size);
    }

    public static Random get() {
        return r;
    }
}
