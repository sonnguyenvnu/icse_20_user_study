package org.nutz.lang.random;

/**
 * @author zozohtnt
 * @author wendal(wendal1985@gmail.com)
 */
public class StringGenerator {

    /**
     * 
     * @param max
     *            必须大于0
     */
    public StringGenerator(int max) {
        maxLen = max;
        minLen = 1;
    }

    /**
     * 
     * @param min
     *            必须大于0
     * @param max
     *            必须�?�?于min
     */
    public StringGenerator(int min, int max) {
        maxLen = max;
        minLen = min;
    }

    /**
     * min length of the string
     */
    private int maxLen;

    /**
     * max length of the string
     */
    private int minLen;

    /**
     * 
     * @param min
     *            必须大于0
     * @param max
     *            必须�?�?于min
     */
    public void setup(int min, int max) {
        minLen = min;
        maxLen = max;
    }

    /**
     * 根�?�设置的max和min的长度,生�?�?机字符串.
     * <p/>
     * 若max或min�?于0,则返回null
     * 
     * @return 生�?的字符串
     */
    public String next() {
        if (maxLen <= 0 || minLen <= 0 || minLen > maxLen)
            return null;
        char[] buf = new char[R.random(minLen, maxLen)];
        for (int i = 0; i < buf.length; i++)
            buf[i] = CharGenerator.next();
        return new String(buf);
    }

}
