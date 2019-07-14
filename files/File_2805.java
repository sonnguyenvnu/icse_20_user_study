/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/8 23:04</create-date>
 *
 * <copyright file="Util.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.io;


import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.utility.LexiconUtility;
import com.hankcs.hanlp.utility.TextUtility;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;
import static com.hankcs.hanlp.HanLP.Config.IOAdapter;

/**
 * 一些常用的IO�?作
 *
 * @author hankcs
 */
public class IOUtil
{
    /**
     * �?列化对象
     *
     * @param o
     * @param path
     * @return
     */
    public static boolean saveObjectTo(Object o, String path)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(IOUtil.newOutputStream(path));
            oos.writeObject(o);
            oos.close();
        }
        catch (IOException e)
        {
            logger.warning("在�?存对象" + o + "到" + path + "时�?�生异常" + e);
            return false;
        }

        return true;
    }

    /**
     * �??�?列化对象
     *
     * @param path
     * @return
     */
    public static Object readObjectFrom(String path)
    {
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(IOUtil.newInputStream(path));
            Object o = ois.readObject();
            ois.close();
            return o;
        }
        catch (Exception e)
        {
            logger.warning("在从" + path + "读�?�对象时�?�生异常" + e);
        }

        return null;
    }

    /**
     * 一次性读入纯文本
     *
     * @param path
     * @return
     */
    public static String readTxt(String path)
    {
        if (path == null) return null;
        try
        {
            InputStream in = IOAdapter == null ? new FileInputStream(path) :
                    IOAdapter.open(path);
            byte[] fileContent = new byte[in.available()];
            int read = readBytesFromOtherInputStream(in, fileContent);
            in.close();
            // 处�?� UTF-8 BOM
            if (read >= 3 && fileContent[0] == -17 && fileContent[1] == -69 && fileContent[2] == -65)
                return new String(fileContent, 3, fileContent.length - 3, Charset.forName("UTF-8"));
            return new String(fileContent, Charset.forName("UTF-8"));
        }
        catch (FileNotFoundException e)
        {
            logger.warning("找�?到" + path + e);
            return null;
        }
        catch (IOException e)
        {
            logger.warning("读�?�" + path + "�?�生IO异常" + e);
            return null;
        }
    }

    public static LinkedList<String[]> readCsv(String path)
    {
        LinkedList<String[]> resultList = new LinkedList<String[]>();
        LinkedList<String> lineList = readLineList(path);
        for (String line : lineList)
        {
            resultList.add(line.split(","));
        }
        return resultList;
    }

    /**
     * 快速�?存
     *
     * @param path
     * @param content
     * @return
     */
    public static boolean saveTxt(String path, String content)
    {
        try
        {
            FileChannel fc = new FileOutputStream(path).getChannel();
            fc.write(ByteBuffer.wrap(content.getBytes()));
            fc.close();
        }
        catch (Exception e)
        {
            logger.throwing("IOUtil", "saveTxt", e);
            logger.warning("IOUtil saveTxt 到" + path + "失败" + e.toString());
            return false;
        }
        return true;
    }

    public static boolean saveTxt(String path, StringBuilder content)
    {
        return saveTxt(path, content.toString());
    }

    public static <T> boolean saveCollectionToTxt(Collection<T> collection, String path)
    {
        StringBuilder sb = new StringBuilder();
        for (Object o : collection)
        {
            sb.append(o);
            sb.append('\n');
        }
        return saveTxt(path, sb.toString());
    }

    /**
     * 将整个文件读�?�为字节数组
     *
     * @param path
     * @return
     */
    public static byte[] readBytes(String path)
    {
        try
        {
            if (IOAdapter == null) return readBytesFromFileInputStream(new FileInputStream(path));

            InputStream is = IOAdapter.open(path);
            if (is instanceof FileInputStream)
                return readBytesFromFileInputStream((FileInputStream) is);
            else
                return readBytesFromOtherInputStream(is);
        }
        catch (Exception e)
        {
            logger.warning("读�?�" + path + "时�?�生异常" + e);
        }

        return null;
    }

    public static String readTxt(String file, String charsetName) throws IOException
    {
        InputStream is = IOAdapter.open(file);
        byte[] targetArray = new byte[is.available()];
        int len;
        int off = 0;
        while ((len = is.read(targetArray, off, targetArray.length - off)) != -1 && off < targetArray.length)
        {
            off += len;
        }
        is.close();

        return new String(targetArray, charsetName);
    }

    public static String baseName(String path)
    {
        if (path == null || path.length() == 0)
            return "";
        path = path.replaceAll("[/\\\\]+", "/");
        int len = path.length(),
                upCount = 0;
        while (len > 0)
        {
            //remove trailing separator
            if (path.charAt(len - 1) == '/')
            {
                len--;
                if (len == 0)
                    return "";
            }
            int lastInd = path.lastIndexOf('/', len - 1);
            String fileName = path.substring(lastInd + 1, len);
            if (fileName.equals("."))
            {
                len--;
            }
            else if (fileName.equals(".."))
            {
                len -= 2;
                upCount++;
            }
            else
            {
                if (upCount == 0)
                    return fileName;
                upCount--;
                len -= fileName.length();
            }
        }
        return "";
    }

    private static byte[] readBytesFromFileInputStream(FileInputStream fis) throws IOException
    {
        FileChannel channel = fis.getChannel();
        int fileSize = (int) channel.size();
        ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
        channel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = byteBuffer.array();
        byteBuffer.clear();
        channel.close();
        fis.close();
        return bytes;
    }

    /**
     * 将�?�FileInputStream的�?InputStream中的全部数�?�读入到字节数组中
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] readBytesFromOtherInputStream(InputStream is) throws IOException
    {
        ByteArrayOutputStream data = new ByteArrayOutputStream();

        int readBytes;
        byte[] buffer = new byte[Math.max(is.available(), 4096)]; // 最低4KB的缓冲区

        while ((readBytes = is.read(buffer, 0, buffer.length)) != -1)
        {
            data.write(buffer, 0, readBytes);
        }

        data.flush();

        return data.toByteArray();
    }

    /**
     * 从InputStream读�?�指定长度的字节出�?�
     * @param is �?
     * @param targetArray output
     * @return 实际读�?�了多少字节，返回0表示�?�到了文件尾部
     * @throws IOException
     */
    public static int readBytesFromOtherInputStream(InputStream is, byte[] targetArray) throws IOException
    {
        assert targetArray != null;
        if (targetArray.length == 0) return 0;
        int len;
        int off = 0;
        while (off < targetArray.length && (len = is.read(targetArray, off, targetArray.length - off)) != -1)
        {
            off += len;
        }
        return off;
    }

    public static LinkedList<String> readLineList(String path)
    {
        LinkedList<String> result = new LinkedList<String>();
        String txt = readTxt(path);
        if (txt == null) return result;
        StringTokenizer tokenizer = new StringTokenizer(txt, "\n");
        while (tokenizer.hasMoreTokens())
        {
            result.add(tokenizer.nextToken());
        }

        return result;
    }

    /**
     * 用�?内存的方�?读�?�大文件
     *
     * @param path
     * @return
     */
    public static LinkedList<String> readLineListWithLessMemory(String path)
    {
        LinkedList<String> result = new LinkedList<String>();
        String line = null;
        boolean first = true;
        try
        {
            BufferedReader bw = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            while ((line = bw.readLine()) != null)
            {
                if (first)
                {
                    first = false;
                    if (!line.isEmpty() && line.charAt(0) == '\uFEFF')
                        line = line.substring(1);
                }
                result.add(line);
            }
            bw.close();
        }
        catch (Exception e)
        {
            logger.warning("加载" + path + "失败，" + e);
        }

        return result;
    }

    public static boolean saveMapToTxt(Map<Object, Object> map, String path)
    {
        return saveMapToTxt(map, path, "=");
    }

    public static boolean saveMapToTxt(Map<Object, Object> map, String path, String separator)
    {
        map = new TreeMap<Object, Object>(map);
        return saveEntrySetToTxt(map.entrySet(), path, separator);
    }

    public static boolean saveEntrySetToTxt(Set<Map.Entry<Object, Object>> entrySet, String path, String separator)
    {
        StringBuilder sbOut = new StringBuilder();
        for (Map.Entry<Object, Object> entry : entrySet)
        {
            sbOut.append(entry.getKey());
            sbOut.append(separator);
            sbOut.append(entry.getValue());
            sbOut.append('\n');
        }
        return saveTxt(path, sbOut.toString());
    }

    /**
     * 获�?�文件所在目录的路径
     * @param path
     * @return
     */
    public static String dirname(String path)
    {
        int index = path.lastIndexOf('/');
        if (index == -1) return path;
        return path.substring(0, index + 1);
    }

    public static LineIterator readLine(String path)
    {
        return new LineIterator(path);
    }

    /**
     * 删除本地文件
     * @param path
     * @return
     */
    public static boolean deleteFile(String path)
    {
        return new File(path).delete();
    }

    /**
     * 去除文件第一行中的UTF8 BOM<br>
     *     这是Java的bug，且官方�?会修�?。�?�考 https://stackoverflow.com/questions/4897876/reading-utf-8-bom-marker
     * @param line 文件第一行
     * @return 去除BOM的部分
     */
    public static String removeUTF8BOM(String line)
    {
        if (line != null && line.startsWith("\uFEFF")) // UTF-8 byte order mark (EF BB BF)
        {
            line = line.substring(1);
        }
        return line;
    }

    /**
     * 递归�??历获�?�目录下的所有文件
     *
     * @param path 根目录
     * @return 文件列表
     */
    public static List<File> fileList(String path)
    {
        List<File> fileList = new LinkedList<File>();
        File folder = new File(path);
        if (folder.isDirectory())
            enumerate(folder, fileList);
        else
            fileList.add(folder); // 兼容路径为文件的情况
        return fileList;
    }

    /**
     * 递归�??历目录
     *
     * @param folder   目录
     * @param fileList 储存文件
     */
    private static void enumerate(File folder, List<File> fileList)
    {
        File[] fileArray = folder.listFiles();
        if (fileArray != null)
        {
            for (File file : fileArray)
            {
                if (file.isFile() && !file.getName().startsWith(".")) // 过滤�?�?文件
                {
                    fileList.add(file);
                }
                else
                {
                    enumerate(file, fileList);
                }
            }
        }
    }

    /**
     * 方便读�?�按行读�?�大文件
     */
    public static class LineIterator implements Iterator<String>, Iterable<String>
    {
        BufferedReader bw;
        String line;

        public LineIterator(BufferedReader bw)
        {
            this.bw = bw;
            try
            {
                line = bw.readLine();
                line = IOUtil.removeUTF8BOM(line);
            }
            catch (IOException e)
            {
                logger.warning("在读�?�过程中�?�生错误" + TextUtility.exceptionToString(e));
                bw = null;
            }
        }

        public LineIterator(String path)
        {
            try
            {
                bw = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
                line = bw.readLine();
                line = IOUtil.removeUTF8BOM(line);
            }
            catch (FileNotFoundException e)
            {
                logger.warning("文件" + path + "�?存在，接下�?�的调用会返回null\n" + TextUtility.exceptionToString(e));
                bw = null;
            }
            catch (IOException e)
            {
                logger.warning("在读�?�过程中�?�生错误" + TextUtility.exceptionToString(e));
                bw = null;
            }
        }

        public void close()
        {
            if (bw == null) return;
            try
            {
                bw.close();
                bw = null;
            }
            catch (IOException e)
            {
                logger.warning("关闭文件失败" + TextUtility.exceptionToString(e));
            }
            return;
        }

        @Override
        public boolean hasNext()
        {
            if (bw == null) return false;
            if (line == null)
            {
                try
                {
                    bw.close();
                    bw = null;
                }
                catch (IOException e)
                {
                    logger.warning("关闭文件失败" + TextUtility.exceptionToString(e));
                }
                return false;
            }

            return true;
        }

        @Override
        public String next()
        {
            String preLine = line;
            try
            {
                if (bw != null)
                {
                    line = bw.readLine();
                    if (line == null && bw != null)
                    {
                        try
                        {
                            bw.close();
                            bw = null;
                        }
                        catch (IOException e)
                        {
                            logger.warning("关闭文件失败" + TextUtility.exceptionToString(e));
                        }
                    }
                }
                else
                {
                    line = null;
                }
            }
            catch (IOException e)
            {
                logger.warning("在读�?�过程中�?�生错误" + TextUtility.exceptionToString(e));
            }
            return preLine;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("�?�读，�?�?�写�?");
        }

        @Override
        public Iterator<String> iterator()
        {
            return this;
        }
    }

    /**
     * 创建一个BufferedWriter
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static BufferedWriter newBufferedWriter(String path) throws IOException
    {
        return new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path), "UTF-8"));
    }

    /**
     * 创建一个BufferedReader
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static BufferedReader newBufferedReader(String path) throws IOException
    {
        return new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
    }

    public static BufferedWriter newBufferedWriter(String path, boolean append) throws FileNotFoundException, UnsupportedEncodingException
    {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, append), "UTF-8"));
    }

    /**
     * 创建输入�?（�?过IO适�?器创建）
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream newInputStream(String path) throws IOException
    {
        if (IOAdapter == null) return new FileInputStream(path);
        return IOAdapter.open(path);
    }

    /**
     * 创建输出�?（�?过IO适�?器创建）
     * @param path
     * @return
     * @throws IOException
     */
    public static OutputStream newOutputStream(String path) throws IOException
    {
        if (IOAdapter == null) return new FileOutputStream(path);
        return IOAdapter.create(path);
    }

    /**
     * 获�?�最�?�一个分隔符的�?�缀
     * @param name
     * @param delimiter
     * @return
     */
    public static String getSuffix(String name, String delimiter)
    {
        return name.substring(name.lastIndexOf(delimiter) + 1);
    }

    /**
     * 写数组，用制表符分割
     * @param bw
     * @param params
     * @throws IOException
     */
    public static void writeLine(BufferedWriter bw, String... params) throws IOException
    {
        for (int i = 0; i < params.length - 1; i++)
        {
            bw.write(params[i]);
            bw.write('\t');
        }
        bw.write(params[params.length - 1]);
    }

    /**
     * 加载�?典，�?典必须�?�守HanLP核心�?典格�?
     * @param pathArray �?典路径，�?�以有任�?个。�?个路径支�?用空格表示默认�?性，比如“全国地�??大全.txt ns�?
     * @return 一个储存了�?�?�的map
     * @throws IOException 异常表示加载失败
     */
    public static TreeMap<String, CoreDictionary.Attribute> loadDictionary(String... pathArray) throws IOException
    {
        TreeMap<String, CoreDictionary.Attribute> map = new TreeMap<String, CoreDictionary.Attribute>();
        for (String path : pathArray)
        {
            File file = new File(path);
            String fileName = file.getName();
            int natureIndex = fileName.lastIndexOf(' ');
            Nature defaultNature = Nature.n;
            if (natureIndex > 0)
            {
                String natureString = fileName.substring(natureIndex + 1);
                path = file.getParent() + File.separator + fileName.substring(0, natureIndex);
                if (natureString.length() > 0 && !natureString.endsWith(".txt") && !natureString.endsWith(".csv"))
                {
                    defaultNature = Nature.create(natureString);
                }
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            loadDictionary(br, map, path.endsWith(".csv"), defaultNature);
        }

        return map;
    }

    /**
     * 将一个BufferedReader中的�?�?�加载到�?典
     * @param br �?
     * @param storage 储存�?置
     * @throws IOException 异常表示加载失败
     */
    public static void loadDictionary(BufferedReader br, TreeMap<String, CoreDictionary.Attribute> storage, boolean isCSV, Nature defaultNature) throws IOException
    {
        String splitter = "\\s";
        if (isCSV)
        {
            splitter = ",";
        }
        String line;
        boolean firstLine = true;
        while ((line = br.readLine()) != null)
        {
            if (firstLine)
            {
                line = IOUtil.removeUTF8BOM(line);
                firstLine = false;
            }
            String param[] = line.split(splitter);

            int natureCount = (param.length - 1) / 2;
            CoreDictionary.Attribute attribute;
            if (natureCount == 0)
            {
                attribute = new CoreDictionary.Attribute(defaultNature);
            }
            else
            {
                attribute = new CoreDictionary.Attribute(natureCount);
                for (int i = 0; i < natureCount; ++i)
                {
                    attribute.nature[i] = LexiconUtility.convertStringToNature(param[1 + 2 * i]);
                    attribute.frequency[i] = Integer.parseInt(param[2 + 2 * i]);
                    attribute.totalFrequency += attribute.frequency[i];
                }
            }
            storage.put(param[0], attribute);
        }
        br.close();
    }

    public static void writeCustomNature(DataOutputStream out, LinkedHashSet<Nature> customNatureCollector) throws IOException
    {
        if (customNatureCollector.size() == 0) return;
        out.writeInt(-customNatureCollector.size());
        for (Nature nature : customNatureCollector)
        {
            TextUtility.writeString(nature.toString(), out);
        }
    }

    /**
     * 本地文件是�?�存在
     * @param path
     * @return
     */
    public static boolean isFileExisted(String path)
    {
        File file = new File(path);
        return file.isFile() && file.exists();
    }
}
