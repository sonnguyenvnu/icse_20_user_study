/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/30 14:33</create-date>
 *
 * <copyright file="ByteArray.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.io;

import com.hankcs.hanlp.utility.ByteUtil;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 对字节数组进行�?装，�??供方便的读�?��?作
 *
 * @author hankcs
 */
public class ByteArray
{
    /**
     * 当�?字节数组，�?一定是全部字节，�?�能�?�是一个片段
     */
    byte[] bytes;
    /**
     * 当�?已读�?�的字节数，或下一个字节的指针
     */
    int offset;

    public ByteArray(byte[] bytes)
    {
        this.bytes = bytes;
    }

    /**
     * 从文件读�?�一个字节数组
     *
     * @param path
     * @return
     */
    public static ByteArray createByteArray(String path)
    {
        byte[] bytes = IOUtil.readBytes(path);
        if (bytes == null) return null;
        return new ByteArray(bytes);
    }

    /**
     * 获�?�全部字节
     * @return
     */
    public byte[] getBytes()
    {
        return bytes;
    }

    /**
     * 读�?�一个int
     *
     * @return
     */
    public int nextInt()
    {
        int result = ByteUtil.bytesHighFirstToInt(bytes, offset);
        offset += 4;
        return result;
    }

    public double nextDouble()
    {
        double result = ByteUtil.bytesHighFirstToDouble(bytes, offset);
        offset += 8;
        return result;
    }

    /**
     * 读�?�一个char，对应于writeChar
     *
     * @return
     */
    public char nextChar()
    {
        char result = ByteUtil.bytesHighFirstToChar(bytes, offset);
        offset += 2;
        return result;
    }

    /**
     * 读�?�一个字节
     *
     * @return
     */
    public byte nextByte()
    {
        return bytes[offset++];
    }

    /**
     * 读�?�一个布尔值
     * @return
     */
    public boolean nextBoolean()
    {
        return nextByte() == 1;
    }

    public boolean hasMore()
    {
        return offset < bytes.length;
    }

    /**
     * 读�?�一个String，注�?这个String是�?�字节版的，在字符之�?有一个整型表示长度
     *
     * @return
     */
    public String nextString()
    {
        char[] buffer = new char[nextInt()];
        for (int i = 0; i < buffer.length; ++i)
        {
            buffer[i] = nextChar();
        }
        return new String(buffer);
    }

    public float nextFloat()
    {
        float result = ByteUtil.bytesHighFirstToFloat(bytes, offset);
        offset += 4;
        return result;
    }

    /**
     * 读�?�一个无符�?�短整型
     * @return
     */
    public int nextUnsignedShort()
    {
        byte a = nextByte();
        byte b = nextByte();
        return (((a & 0xff) << 8) | (b & 0xff));
    }

    /**
     * 读�?�一个UTF字符串
     * @return
     */
    public String nextUTF()
    {
        int utflen = nextUnsignedShort();
        byte[] bytearr = null;
        char[] chararr = null;
        bytearr = new byte[utflen];
        chararr = new char[utflen];

        int c, char2, char3;
        int count = 0;
        int chararr_count = 0;

        for (int i = 0; i < utflen; ++i)
        {
            bytearr[i] = nextByte();
        }

        while (count < utflen)
        {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) break;
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while (count < utflen)
        {
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4)
            {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen)
                        logger.severe(
                                "malformed input: partial character at end");
                    char2 = (int) bytearr[count - 1];
                    if ((char2 & 0xC0) != 0x80)
                        logger.severe(
                                "malformed input around byte " + count);
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen)
                        logger.severe(
                                "malformed input: partial character at end");
                    char2 = (int) bytearr[count - 2];
                    char3 = (int) bytearr[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                        logger.severe(
                                "malformed input around byte " + (count - 1));
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    logger.severe(
                            "malformed input around byte " + count);
            }
        }
        // The number of chars produced may be less than utflen
        return new String(chararr, 0, chararr_count);
    }

    public int getOffset()
    {
        return offset;
    }

    public int getLength()
    {
        return bytes.length;
    }

    /**
     * 通知执行关闭/销�?�?作
     */
    public void close()
    {
        bytes = null;
    }

    @Override
    protected void finalize() throws Throwable
    {
        // 如果忘记close，则在垃圾回收器释放内存的时候close，总好过完全�?close
        close();
    }
}
