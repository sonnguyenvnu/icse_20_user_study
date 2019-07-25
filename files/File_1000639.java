package org.nutz.lang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.io.Writer;

import org.nutz.lang.stream.VoidInputStream;
import org.nutz.resource.NutResource;
import org.nutz.resource.Scans;

/**
 * �??供了一组创建 Reader/Writer/InputStream/OutputStream 的便利函数
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author Wendal(wendal1985@gmail.com)
 * @author bonyfish(mc02cxj@gmail.com)
 */
public abstract class Streams {

    private static final int BUF_SIZE = 8192;

    /**
     * 判断两个输入�?是�?�严格相等
     */
    public static boolean equals(InputStream sA, InputStream sB) throws IOException {
        int dA;
        while ((dA = sA.read()) != -1) {
            int dB = sB.read();
            if (dA != dB)
                return false;
        }
        return sB.read() == -1;
    }

    /**
     * 将一段文本全部写入一个writer。
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输出�?
     * 
     * @param writer
     * 
     * @param cs
     *            文本
     * @throws IOException
     */
    public static void write(Writer writer, CharSequence cs) throws IOException {
        if (null != cs && null != writer) {
            writer.write(cs.toString());
            writer.flush();
        }
    }

    /**
     * 将一段文本全部写入一个writer。
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输出�?
     * 
     * @param writer
     *            输出�?
     * @param cs
     *            文本
     */
    public static void writeAndClose(Writer writer, CharSequence cs) {
        try {
            write(writer, cs);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(writer);
        }
    }

    /**
     * 将输入�?写入一个输出�?。�?�大�?为 8192
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输入/出�?
     * 
     * @param ops
     *            输出�?
     * @param ins
     *            输入�?
     * 
     * @return 写入的字节数
     * @throws IOException
     */
    public static long write(OutputStream ops, InputStream ins) throws IOException {
        return write(ops, ins, BUF_SIZE);
    }

    /**
     * 将输入�?写入一个输出�?。
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输入/出�?
     * 
     * @param ops
     *            输出�?
     * @param ins
     *            输入�?
     * @param bufferSize
     *            缓冲�?�大�?
     * 
     * @return 写入的字节数
     * 
     * @throws IOException
     */
    public static long write(OutputStream ops, InputStream ins, int bufferSize) throws IOException {
        if (null == ops || null == ins)
            return 0;

        byte[] buf = new byte[bufferSize];
        int len;
        long bytesCount = 0;
        while (-1 != (len = ins.read(buf))) {
            bytesCount += len;
            ops.write(buf, 0, len);
        }
        // 啥都没写，强制触�?�一下写
        // 这是考虑到 walnut 的输出�?实现，比如你写一个空文件
        // 那么输入�?就是空的，但是 walnut 的包裹输出�?并�?知�?�你写过了
        // 它人你就是打开一个输出�?，然�?��?关上，所以自然�?会对内容�?�改动
        // 所以这里触�?�一个写，它就知�?�，喔你�?写个空喔。
        if (0 == bytesCount) {
            ops.write(buf, 0, 0);
        }
        ops.flush();
        return bytesCount;
    }

    /**
     * 将输入�?写入一个输出�?。�?�大�?为 8192
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输入/出�?
     * 
     * @param ops
     *            输出�?
     * @param ins
     *            输入�?
     * @return 写入的字节数
     */
    public static long writeAndClose(OutputStream ops, InputStream ins) {
        try {
            return write(ops, ins);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(ops);
            safeClose(ins);
        }
    }

    /**
     * 将文本输入�?写入一个文本输出�?。�?�大�?为 8192
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输入/出�?
     * 
     * @param writer
     *            输出�?
     * @param reader
     *            输入�?
     * @throws IOException
     */
    public static long write(Writer writer, Reader reader) throws IOException {
        if (null == writer || null == reader)
            return 0;

        char[] cbuf = new char[BUF_SIZE];
        int len, count = 0;
        while (true) {
            len = reader.read(cbuf);
            if (len == -1)
                break;
            writer.write(cbuf, 0, len);
            count += len;
        }
        return count;
    }

    /**
     * 将文本输入�?写入一个文本输出�?。�?�大�?为 8192
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输入/出�?
     * 
     * @param writer
     *            输出�?
     * @param reader
     *            输入�?
     */
    public static long writeAndClose(Writer writer, Reader reader) {
        try {
            return write(writer, reader);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(writer);
            safeClose(reader);
        }
    }

    /**
     * 将一个字节数组写入一个输出�?。
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输出�?
     * 
     * @param ops
     *            输出�?
     * @param bytes
     *            字节数组
     * @throws IOException
     */
    public static void write(OutputStream ops, byte[] bytes) throws IOException {
        if (null == ops || null == bytes || bytes.length == 0)
            return;
        ops.write(bytes);
    }

    /**
     * 将一个字节数组写入一个输出�?。
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输出�?
     * 
     * @param ops
     *            输出�?
     * @param bytes
     *            字节数组
     */
    public static void writeAndClose(OutputStream ops, byte[] bytes) {
        try {
            write(ops, bytes);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(ops);
        }
    }

    /**
     * 从一个文本�?中读�?�全部内容并返回
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输出�?
     * 
     * @param reader
     *            文本输出�?
     * @return 文本内容
     * @throws IOException
     */
    public static StringBuilder read(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        read(reader, sb);
        return sb;
    }

    /**
     * 从一个文本�?中读�?�全部内容并返回
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输入�?
     * 
     * @param reader
     *            文本输入�?
     * @return 文本内容
     * @throws IOException
     */
    public static String readAndClose(Reader reader) {
        try {
            return read(reader).toString();
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(reader);
        }
    }

    /**
     * 从一个文本�?中读�?�全部内容并写入缓冲
     * <p>
     * <b style=color:red>注�?</b>，它并�?会关闭输出�?
     * 
     * @param reader
     *            文本输出�?
     * @param sb
     *            输出的文本缓冲
     * @return 读�?�的字符数�?
     * @throws IOException
     */
    public static int read(Reader reader, StringBuilder sb) throws IOException {
        char[] cbuf = new char[BUF_SIZE];
        int count = 0;
        int len;
        while (-1 != (len = reader.read(cbuf))) {
            sb.append(cbuf, 0, len);
            count += len;
        }
        return count;
    }

    /**
     * 从一个文本�?中读�?�全部内容并写入缓冲
     * <p>
     * <b style=color:red>注�?</b>，它会关闭输出�?
     * 
     * @param reader
     *            文本输出�?
     * @param sb
     *            输出的文本缓冲
     * @return 读�?�的字符数�?
     */
    public static int readAndClose(InputStreamReader reader, StringBuilder sb) {
        try {
            return read(reader, sb);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(reader);
        }
    }

    /**
     * 读�?�一个输入�?中所有的字节
     * 
     * @param ins
     *            输入�?，必须支�? available()
     * @return 一个字节数组
     * @throws IOException
     */
    public static byte[] readBytes(InputStream ins) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out, ins);
        return out.toByteArray();
    }

    /**
     * 读�?�一个输入�?中所有的字节，并关闭输入�?
     * 
     * @param ins
     *            输入�?，必须支�? available()
     * @return 一个字节数组
     * @throws IOException
     */
    public static byte[] readBytesAndClose(InputStream ins) {
        byte[] bytes = null;
        try {
            bytes = readBytes(ins);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            Streams.safeClose(ins);
        }
        return bytes;
    }

    /**
     * 关闭一个�?�关闭对象，�?�以接�?� null。如果�?功关闭，返回 true，�?�生异常 返回 false
     * 
     * @param cb
     *            �?�关闭对象
     * @return 是�?��?功关闭
     */
    public static boolean safeClose(Closeable cb) {
        if (null != cb)
            try {
                cb.close();
            }
            catch (IOException e) {
                return false;
            }
        return true;
    }

    /**
     * 安全刷新一个�?�刷新的对象，�?�接�?� null
     * 
     * @param fa
     *            �?�刷新对象
     */
    public static void safeFlush(Flushable fa) {
        if (null != fa)
            try {
                fa.flush();
            }
            catch (IOException e) {}
    }

    /**
     * 为一个输入�?包裹一个缓冲�?。如果这个输入�?本身就是缓冲�?，则直接返回
     * 
     * @param ins
     *            输入�?。
     * @return 缓冲输入�?
     */
    public static BufferedInputStream buff(InputStream ins) {
        if (ins == null)
            throw new NullPointerException("ins is null!");
        if (ins instanceof BufferedInputStream)
            return (BufferedInputStream) ins;
        // BufferedInputStream的构造方法,竟然是�?许null�?�数的!! 我&$#^$&%
        return new BufferedInputStream(ins);
    }

    /**
     * 为一个输出�?包裹一个缓冲�?。如果这个输出�?本身就是缓冲�?，则直接返回
     * 
     * @param ops
     *            输出�?。
     * @return 缓冲输出�?
     */
    public static BufferedOutputStream buff(OutputStream ops) {
        if (ops == null)
            throw new NullPointerException("ops is null!");
        if (ops instanceof BufferedOutputStream)
            return (BufferedOutputStream) ops;
        return new BufferedOutputStream(ops);
    }

    /**
     * 为一个文本输入�?包裹一个缓冲�?。如果这个输入�?本身就是缓冲�?，则直接返回
     * 
     * @param reader
     *            文本输入�?。
     * @return 缓冲文本输入�?
     */
    public static BufferedReader buffr(Reader reader) {
        if (reader instanceof BufferedReader)
            return (BufferedReader) reader;
        return new BufferedReader(reader);
    }

    /**
     * 为一个文本输出�?包裹一个缓冲�?。如果这个文本输出�?本身就是缓冲�?，则直接返回
     * 
     * @param ops
     *            文本输出�?。
     * @return 缓冲文本输出�?
     */
    public static BufferedWriter buffw(Writer ops) {
        if (ops instanceof BufferedWriter)
            return (BufferedWriter) ops;
        return new BufferedWriter(ops);
    }

    /**
     * 根�?�一个文件路径建立一个输入�?
     * 
     * @param path
     *            文件路径
     * @return 输入�?
     */
    public static InputStream fileIn(String path) {
        InputStream ins = Files.findFileAsStream(path);
        if (null == ins) {
            File f = Files.findFile(path);
            if (null != f)
                try {
                    ins = Streams._input(f);
                }
                catch (IOException e) {}
        }
        if (null == ins) {
            // TODO 考虑一下,应该抛异常呢?还是返回null呢?

            throw new RuntimeException(new FileNotFoundException(path));
            // return null;
        }
        return buff(ins);
    }

    /**
     * 根�?�一个文件路径建立一个输入�?
     * 
     * @param file
     *            文件
     * @return 输入�?
     */
    public static InputStream fileIn(File file) {
        try {
            return buff(Streams._input(file));
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 根�?�一个文件路径建立一个 UTF-8文本输入�? <b>警告!! 本方法会预先读�?�3个字节以判断该文件是�?�存在BOM头</b>
     * <p/>
     * <b>警告!! 如果存在BOM头,则自动跳过</b>
     * <p/>
     * 
     * @param path
     *            文件路径
     * @return 文本输入�?
     */
    public static Reader fileInr(String path) {
        return utf8r(fileIn(path));
    }

    /**
     * 根�?�一个文件路径建立一个 UTF-8 文本输入�? <b>警告!! 本方法会预先读�?�3个字节以判断该文件是�?�存在BOM头</b>
     * <p/>
     * <b>警告!! 如果存在BOM头,则自动跳过</b>
     * <p/>
     * 
     * @param file
     *            文件
     * @return 文本输入�?
     */
    public static Reader fileInr(File file) {
        return utf8r(fileIn(file));
    }

    private static final byte[] UTF_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    /**
     * 判断并移除UTF-8的BOM头
     */
    public static InputStream utf8filte(InputStream in) {
        try {
            if (in.available() == -1)
                return in;
            PushbackInputStream pis = new PushbackInputStream(in, 3);
            byte[] header = new byte[3];
            int len = pis.read(header, 0, 3);
            if (len < 1)
                return in;
            if (header[0] != UTF_BOM[0] || header[1] != UTF_BOM[1] || header[2] != UTF_BOM[2]) {
                pis.unread(header, 0, len);
            }
            return pis;
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 根�?�一个文件路径建立一个输出�?
     * 
     * @param path
     *            文件路径
     * @return 输出�?
     */
    public static OutputStream fileOut(String path) {
        return fileOut(Files.findFile(path));
    }

    /**
     * 根�?�一个文件建立一个输出�?
     * 
     * @param file
     *            文件
     * @return 输出�?
     */
    public static OutputStream fileOut(File file) {
        try {
            return buff(new FileOutputStream(file));
        }
        catch (FileNotFoundException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 根�?�一个文件路径建立一个 UTF-8 文本输出�?
     * 
     * @param path
     *            文件路径
     * @return 文本输出�?
     */
    public static Writer fileOutw(String path) {
        return fileOutw(Files.findFile(path));
    }

    /**
     * 根�?�一个文件建立一个 UTF-8 文本输出�?
     * 
     * @param file
     *            文件
     * @return 输出�?
     */
    public static Writer fileOutw(File file) {
        return utf8w(fileOut(file));
    }

    public static Reader utf8r(InputStream is) {
        return new InputStreamReader(utf8filte(is), Encoding.CHARSET_UTF8);
    }

    public static Writer utf8w(OutputStream os) {
        return new OutputStreamWriter(os, Encoding.CHARSET_UTF8);
    }

    public static InputStream nullInputStream() {
        return new VoidInputStream();
    }

    public static InputStream wrap(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 对一个文本输入�?迭代�?一行，并将其关闭
     * 
     * @param r
     *            文本输入�?
     * @param callback
     *            回调
     * @return 迭代的行数
     */
    public static int eachLine(Reader r, Each<String> callback) {
        if (null == callback || null == r)
            return 0;
        BufferedReader br = null;
        try {
            br = Streams.buffr(r);
            String line;
            int index = 0;
            while (null != (line = br.readLine())) {
                try {
                    callback.invoke(index++, line, -1);
                }
                catch (ExitLoop e) {
                    break;
                }
                catch (ContinueLoop e) {
                    continue;
                }
            }
            return index;
        }
        catch (IOException e2) {
            throw Lang.wrapThrow(e2);
        }
        finally {
            Streams.safeClose(br);
        }
    }

    /**
     * 获�?�File对象输入�?,�?�使在Jar文件中一样工作良好!! <b>强烈推�??</b>
     * 
     */
    protected static InputStream _input(File file) throws IOException {
        if (file.exists())
            return new FileInputStream(file);
        if (Scans.isInJar(file)) {
            NutResource nutResource = Scans.makeJarNutResource(file);
            if (nutResource != null)
                return nutResource.getInputStream();
        }
        throw new FileNotFoundException(file.toString());
    }

    public static void appendWriteAndClose(File f, String text) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(f, true);
            fw.write(text);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(fw);
        }

    }

    public static String nextLineTrim(BufferedReader br) throws IOException {
        String line = null;
        while (br.ready()) {
            line = br.readLine();
            if (line == null)
                break;
            if (Strings.isBlank(line))
                continue;
            return line.trim();
        }
        return line;
    }

    public static long writeAndClose(OutputStream ops, InputStream ins, int buf) {
        try {
            return write(ops, ins, buf);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            safeClose(ops);
            safeClose(ins);
        }
    }
}
