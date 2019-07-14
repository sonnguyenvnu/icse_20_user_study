/**
 * DoubleArrayTrie: Java implementation of Darts (Double-ARray Trie System)
 * <p/>
 * <p>
 * Copyright(C) 2001-2007 Taku Kudo &lt;taku@chasen.org&gt;<br />
 * Copyright(C) 2009 MURAWAKI Yugo &lt;murawaki@nlp.kuee.kyoto-u.ac.jp&gt;
 * Copyright(C) 2012 KOMIYA Atsushi &lt;komiya.atsushi@gmail.com&gt;
 * </p>
 * <p/>
 * <p>
 * The contents of this file may be used under the terms of either of the GNU
 * Lesser General Public License Version 2.1 or later (the "LGPL"), or the BSD
 * License (the "BSD").
 * </p>
 */
package com.hankcs.hanlp.collection.trie;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.ByteArrayStream;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.ByteUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import static com.hankcs.hanlp.HanLP.Config.IOAdapter;

/**
 * �?�数组Trie树
 */
public class DoubleArrayTrie<V> implements Serializable, ITrie<V>
{
    private final static int BUF_SIZE = 16384;
    private final static int UNIT_SIZE = 8; // size of int + int

    private static class Node
    {
        int code;
        int depth;
        int left;
        int right;

        @Override
        public String toString()
        {
            return "Node{" +
                    "code=" + code +
                    ", depth=" + depth +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    ;

    protected int check[];
    protected int base[];

    /**
     * base 和 check 的大�?
     */
    protected int size;
    private int allocSize;
    private List<String> key;
    private int keySize;
    private int length[];
    private int value[];
    protected V[] v;
    private int progress;
    private int nextCheckPos;
    // boolean no_delete_;
    int error_;

    // int (*progressfunc_) (size_t, size_t);

    // inline _resize expanded

    /**
     * 拓展数组
     *
     * @param newSize
     * @return
     */
    private int resize(int newSize)
    {
        int[] base2 = new int[newSize];
        int[] check2 = new int[newSize];
        if (allocSize > 0)
        {
            System.arraycopy(base, 0, base2, 0, allocSize);
            System.arraycopy(check, 0, check2, 0, allocSize);
        }

        base = base2;
        check = check2;

        return allocSize = newSize;
    }

    /**
     * 获�?�直接相连的�?节点
     *
     * @param parent   父节点
     * @param siblings （�?）兄弟节点
     * @return 兄弟节点个数
     */
    private int fetch(Node parent, List<Node> siblings)
    {
        if (error_ < 0)
            return 0;

        int prev = 0;

        for (int i = parent.left; i < parent.right; i++)
        {
            if ((length != null ? length[i] : key.get(i).length()) < parent.depth)
                continue;

            String tmp = key.get(i);

            int cur = 0;
            if ((length != null ? length[i] : tmp.length()) != parent.depth)
                cur = (int) tmp.charAt(parent.depth) + 1;

            if (prev > cur)
            {
                error_ = -3;
                return 0;
            }

            if (cur != prev || siblings.size() == 0)
            {
                Node tmp_node = new Node();
                tmp_node.depth = parent.depth + 1;
                tmp_node.code = cur;
                tmp_node.left = i;
                if (siblings.size() != 0)
                    siblings.get(siblings.size() - 1).right = i;

                siblings.add(tmp_node);
            }

            prev = cur;
        }

        if (siblings.size() != 0)
            siblings.get(siblings.size() - 1).right = parent.right;

        return siblings.size();
    }

    /**
     * �?�入节点
     *
     * @param siblings 等待�?�入的兄弟节点
     * @return �?�入�?置
     */
    private int insert(List<Node> siblings, BitSet used)
    {
        if (error_ < 0)
            return 0;

        int begin = 0;
        int pos = Math.max(siblings.get(0).code + 1, nextCheckPos) - 1;
        int nonzero_num = 0;
        int first = 0;

        if (allocSize <= pos)
            resize(pos + 1);

        outer:
        // 此循环体的目标是找出满足base[begin + a1...an]  == 0的n个空闲空间,a1...an是siblings中的n个节点
        while (true)
        {
            pos++;

            if (allocSize <= pos)
                resize(pos + 1);

            if (check[pos] != 0)
            {
                nonzero_num++;
                continue;
            }
            else if (first == 0)
            {
                nextCheckPos = pos;
                first = 1;
            }

            begin = pos - siblings.get(0).code; // 当�?�?置离第一个兄弟节点的�?离
            if (allocSize <= (begin + siblings.get(siblings.size() - 1).code))
            {
                resize(begin + siblings.get(siblings.size() - 1).code + Character.MAX_VALUE);
            }

            //if (used[begin])
             //   continue;
            if(used.get(begin)){
            	continue;
            }

            for (int i = 1; i < siblings.size(); i++)
                if (check[begin + siblings.get(i).code] != 0)
                    continue outer;

            break;
        }

        // -- Simple heuristics --
        // if the percentage of non-empty contents in check between the
        // index
        // 'next_check_pos' and 'check' is greater than some constant value
        // (e.g. 0.9),
        // new 'next_check_pos' index is written by 'check'.
        if (1.0 * nonzero_num / (pos - nextCheckPos + 1) >= 0.95)
            nextCheckPos = pos; // 从�?置 next_check_pos 开始到 pos 间，如果已�?�用的空间在95%以上，下次�?�入节点时，直接从 pos �?置处开始查找

        //used[begin] = true;
        used.set(begin);
        
        size = (size > begin + siblings.get(siblings.size() - 1).code + 1) ? size
                : begin + siblings.get(siblings.size() - 1).code + 1;

        for (int i = 0; i < siblings.size(); i++)
        {
            check[begin + siblings.get(i).code] = begin;
//            System.out.println(this);
        }

        for (int i = 0; i < siblings.size(); i++)
        {
            List<Node> new_siblings = new ArrayList<Node>();

            if (fetch(siblings.get(i), new_siblings) == 0)  // 一个�?的终止且�?为其他�?的�?缀
            {
                base[begin + siblings.get(i).code] = (value != null) ? (-value[siblings
                        .get(i).left] - 1) : (-siblings.get(i).left - 1);
//                System.out.println(this);

                if (value != null && (-value[siblings.get(i).left] - 1) >= 0)
                {
                    error_ = -2;
                    return 0;
                }

                progress++;
                // if (progress_func_) (*progress_func_) (progress,
                // keySize);
            }
            else
            {
                int h = insert(new_siblings, used);   // dfs
                base[begin + siblings.get(i).code] = h;
//                System.out.println(this);
            }
        }
        return begin;
    }

    public DoubleArrayTrie()
    {
        check = null;
        base = null;
        size = 0;
        allocSize = 0;
        // no_delete_ = false;
        error_ = 0;
    }

    /**
     * 从TreeMap构造
     * @param buildFrom
     */
    public DoubleArrayTrie(TreeMap<String, V> buildFrom)
    {
        this();
        if (build(buildFrom) != 0)
        {
            throw new IllegalArgumentException("构造失败");
        }
    }

    // no deconstructor

    // set_result omitted
    // the search methods returns (the list of) the value(s) instead
    // of (the list of) the pair(s) of value(s) and length(s)

    // set_array omitted
    // array omitted

    void clear()
    {
        // if (! no_delete_)
        check = null;
        base = null;
        allocSize = 0;
        size = 0;
        // no_delete_ = false;
    }

    public int getUnitSize()
    {
        return UNIT_SIZE;
    }

    public int getSize()
    {
        return size;
    }

    public int getTotalSize()
    {
        return size * UNIT_SIZE;
    }

    public int getNonzeroSize()
    {
        int result = 0;
        for (int i = 0; i < check.length; ++i)
            if (check[i] != 0)
                ++result;
        return result;
    }

    public int build(List<String> key, List<V> value)
    {
        assert key.size() == value.size() : "键的个数与值的个数�?一样�?";
        assert key.size() > 0 : "键值个数为0�?";
        v = (V[]) value.toArray();
        return build(key, null, null, key.size());
    }

    public int build(List<String> key, V[] value)
    {
        assert key.size() == value.length : "键的个数与值的个数�?一样�?";
        assert key.size() > 0 : "键值个数为0�?";
        v = value;
        return build(key, null, null, key.size());
    }

    /**
     * 构建DAT
     *
     * @param entrySet 注�?此entrySet一定�?是字典�?的�?�?�则会失败
     * @return
     */
    public int build(Set<Map.Entry<String, V>> entrySet)
    {
        List<String> keyList = new ArrayList<String>(entrySet.size());
        List<V> valueList = new ArrayList<V>(entrySet.size());
        for (Map.Entry<String, V> entry : entrySet)
        {
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }

        return build(keyList, valueList);
    }

    /**
     * 方便地构造一个�?�数组trie树
     *
     * @param keyValueMap �?��?键值对map
     * @return 构造结果
     */
    public int build(TreeMap<String, V> keyValueMap)
    {
        assert keyValueMap != null;
        Set<Map.Entry<String, V>> entrySet = keyValueMap.entrySet();
        return build(entrySet);
    }

    /**
     * 唯一的构建方法
     *
     * @param _key     值set，必须字典�?
     * @param _length  对应�?个key的长度，留空动�?获�?�
     * @param _value   �?个key对应的值，留空使用key的下标作为值
     * @param _keySize key的长度，应该设为_key.size
     * @return 是�?�出错
     */
    public int build(List<String> _key, int _length[], int _value[],
                     int _keySize)
    {
        if (_key == null || _keySize > _key.size())
            return 0;

        // progress_func_ = progress_func;
        key = _key;
        length = _length;
        keySize = _keySize;
        value = _value;
        progress = 0;
        allocSize = 0;

        resize(65536 * 32); // 32个�?�字节

        base[0] = 1;
        nextCheckPos = 0;

        Node root_node = new Node();
        root_node.left = 0;
        root_node.right = keySize;
        root_node.depth = 0;

        List<Node> siblings = new ArrayList<Node>();
        fetch(root_node, siblings);
        insert(siblings, new BitSet());
        shrink();

        // size += (1 << 8 * 2) + 1; // ???
        // if (size >= allocSize) resize (size);

        key = null;
        length = null;

        return error_;
    }

    public void open(String fileName) throws IOException
    {
        File file = new File(fileName);
        size = (int) file.length() / UNIT_SIZE;
        check = new int[size];
        base = new int[size];

        DataInputStream is = null;
        try
        {
            is = new DataInputStream(new BufferedInputStream(
                    IOUtil.newInputStream(fileName), BUF_SIZE));
            for (int i = 0; i < size; i++)
            {
                base[i] = is.readInt();
                check[i] = is.readInt();
            }
        }
        finally
        {
            if (is != null)
                is.close();
        }
    }

    public boolean save(String fileName)
    {
        DataOutputStream out;
        try
        {
            out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(fileName)));
            out.writeInt(size);
            for (int i = 0; i < size; i++)
            {
                out.writeInt(base[i]);
                out.writeInt(check[i]);
            }
            out.close();
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * 将base和check�?存下�?�
     *
     * @param out
     * @return
     */
    public boolean save(DataOutputStream out)
    {
        try
        {
            out.writeInt(size);
            for (int i = 0; i < size; i++)
            {
                out.writeInt(base[i]);
                out.writeInt(check[i]);
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    public void save(ObjectOutputStream out) throws IOException
    {
        out.writeObject(base);
        out.writeObject(check);
    }

    /**
     * 从�?盘加载，需�?�?外�??供值
     *
     * @param path
     * @param value
     * @return
     */
    public boolean load(String path, List<V> value)
    {
        if (!loadBaseAndCheck(path)) return false;
        v = (V[]) value.toArray();
        return true;
    }

    /**
     * 从�?盘加载，需�?�?外�??供值
     *
     * @param path
     * @param value
     * @return
     */
    public boolean load(String path, V[] value)
    {
        if (!(IOAdapter == null ? loadBaseAndCheckByFileChannel(path) :
        load(ByteArrayStream.createByteArrayStream(path), value)
        )) return false;
        v = value;
        return true;
    }

    public boolean load(ByteArray byteArray, V[] value)
    {
        if (byteArray == null) return false;
        size = byteArray.nextInt();
        base = new int[size + 65535];   // 多留一些，防止越界
        check = new int[size + 65535];
        for (int i = 0; i < size; i++)
        {
            base[i] = byteArray.nextInt();
            check[i] = byteArray.nextInt();
        }
        v = value;
        return true;
    }

    /**
     * 从字节数组加载（�?�现在MacOS上，此方法比ByteArray更快）
     * @param bytes
     * @param offset
     * @param value
     * @return
     */
    public boolean load(byte[] bytes, int offset, V[] value)
    {
        if (bytes == null) return false;
        size = ByteUtil.bytesHighFirstToInt(bytes, offset);
        offset += 4;
        base = new int[size + 65535];   // 多留一些，防止越界
        check = new int[size + 65535];
        for (int i = 0; i < size; i++)
        {
            base[i] = ByteUtil.bytesHighFirstToInt(bytes, offset);
            offset += 4;
            check[i] = ByteUtil.bytesHighFirstToInt(bytes, offset);
            offset += 4;
        }
        v = value;
        return true;
    }

    /**
     * 载入�?�数组，但是�?�??供值，此时本trie相当于一个set
     *
     * @param path
     * @return
     */
    public boolean load(String path)
    {
        return loadBaseAndCheckByFileChannel(path);
    }

    /**
     * 从�?盘加载�?�数组
     *
     * @param path
     * @return
     */
    private boolean loadBaseAndCheck(String path)
    {
        try
        {
            DataInputStream in = new DataInputStream(new BufferedInputStream(IOAdapter == null ?
                                                                                     new FileInputStream(path) :
                    IOAdapter.open(path)
            ));
            size = in.readInt();
            base = new int[size + 65535];   // 多留一些，防止越界
            check = new int[size + 65535];
            for (int i = 0; i < size; i++)
            {
                base[i] = in.readInt();
                check[i] = in.readInt();
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    private boolean loadBaseAndCheckByFileChannel(String path)
    {
        try
        {
            FileInputStream fis = new FileInputStream(path);
            // 1.从FileInputStream对象获�?�文件通�?�FileChannel
            FileChannel channel = fis.getChannel();
            int fileSize = (int) channel.size();

            // 2.从通�?�读�?�文件内容
            ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);

            // channel.read(ByteBuffer) 方法就类似于 inputstream.read(byte)
            // �?次read都将读�?� allocate 个字节到ByteBuffer
            channel.read(byteBuffer);
            // 注�?先调用flip方法�??转Buffer,�?从Buffer读�?�数�?�
            byteBuffer.flip();
            // 有几�?方�?�?�以�?作ByteBuffer
            // �?�以将当�?Buffer包�?�的字节数组全部读�?�出�?�
            byte[] bytes = byteBuffer.array();
            byteBuffer.clear();
            // 关闭通�?�和文件�?
            channel.close();
            fis.close();

            int index = 0;
            size = ByteUtil.bytesHighFirstToInt(bytes, index);
            index += 4;
            base = new int[size + 65535];   // 多留一些，防止越界
            check = new int[size + 65535];
            for (int i = 0; i < size; i++)
            {
                base[i] = ByteUtil.bytesHighFirstToInt(bytes, index);
                index += 4;
                check[i] = ByteUtil.bytesHighFirstToInt(bytes, index);
                index += 4;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 将自己�?列化到
     *
     * @param path
     * @return
     */
    public boolean serializeTo(String path)
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(IOUtil.newOutputStream(path));
            out.writeObject(this);
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> DoubleArrayTrie<T> unSerialize(String path)
    {
        ObjectInputStream in;
        try
        {
            in = new ObjectInputStream(IOAdapter == null ? new FileInputStream(path) : IOAdapter.open(path));
            return (DoubleArrayTrie<T>) in.readObject();
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 精确匹�?
     *
     * @param key 键
     * @return 值
     */
    public int exactMatchSearch(String key)
    {
        return exactMatchSearch(key, 0, 0, 0);
    }

    public int exactMatchSearch(String key, int pos, int len, int nodePos)
    {
        if (len <= 0)
            len = key.length();
        if (nodePos <= 0)
            nodePos = 0;

        int result = -1;

        int b = base[nodePos];
        int p;

        for (int i = pos; i < len; i++)
        {
            p = b + (int) (key.charAt(i)) + 1;
            if (b == check[p])
                b = base[p];
            else
                return result;
        }

        p = b;
        int n = base[p];
        if (b == check[p] && n < 0)
        {
            result = -n - 1;
        }
        return result;
    }

    /**
     * 精确查询
     *
     * @param keyChars 键的char数组
     * @param pos      char数组的起始�?置
     * @param len      键的长度
     * @param nodePos  开始查找的�?置（本�?�数�?许从�?�根节点查询）
     * @return 查到的节点代表的value ID，负数表示�?存在
     */
    public int exactMatchSearch(char[] keyChars, int pos, int len, int nodePos)
    {
        int result = -1;

        int b = base[nodePos];
        int p;

        for (int i = pos; i < len; i++)
        {
            p = b + (int) (keyChars[i]) + 1;
            if (b == check[p])
                b = base[p];
            else
                return result;
        }

        p = b;
        int n = base[p];
        if (b == check[p] && n < 0)
        {
            result = -n - 1;
        }
        return result;
    }

    public List<Integer> commonPrefixSearch(String key)
    {
        return commonPrefixSearch(key, 0, 0, 0);
    }

    /**
     * �?缀查询
     *
     * @param key     查询字串
     * @param pos     字串的开始�?置
     * @param len     字串长度
     * @param nodePos base中的开始�?置
     * @return 一个�?�有所有下标的list
     */
    public List<Integer> commonPrefixSearch(String key, int pos, int len, int nodePos)
    {
        if (len <= 0)
            len = key.length();
        if (nodePos <= 0)
            nodePos = 0;

        List<Integer> result = new ArrayList<Integer>();

        char[] keyChars = key.toCharArray();

        int b = base[nodePos];
        int n;
        int p;

        for (int i = pos; i < len; i++)
        {
            p = b + (int) (keyChars[i]) + 1;    // 状�?转移 p = base[char[i-1]] + char[i] + 1
            if (b == check[p])                  // base[char[i-1]] == check[base[char[i-1]] + char[i] + 1]
                b = base[p];
            else
                return result;
            p = b;
            n = base[p];
            if (b == check[p] && n < 0)         // base[p] == check[p] && base[p] < 0 查到一个�?
            {
                result.add(-n - 1);
            }
        }

        return result;
    }

    /**
     * �?缀查询，包�?�值
     *
     * @param key 键
     * @return 键值对列表
     * @deprecated 最好用优化版的
     */
    public LinkedList<Map.Entry<String, V>> commonPrefixSearchWithValue(String key)
    {
        int len = key.length();
        LinkedList<Map.Entry<String, V>> result = new LinkedList<Map.Entry<String, V>>();
        char[] keyChars = key.toCharArray();
        int b = base[0];
        int n;
        int p;

        for (int i = 0; i < len; ++i)
        {
            p = b;
            n = base[p];
            if (b == check[p] && n < 0)         // base[p] == check[p] && base[p] < 0 查到一个�?
            {
                result.add(new AbstractMap.SimpleEntry<String, V>(new String(keyChars, 0, i), v[-n - 1]));
            }

            p = b + (int) (keyChars[i]) + 1;    // 状�?转移 p = base[char[i-1]] + char[i] + 1
            // 下�?�这�?��?�能产生下标越界，�?如改为if (p < size && b == check[p])，或者多分�?一些内存
            if (b == check[p])                  // base[char[i-1]] == check[base[char[i-1]] + char[i] + 1]
                b = base[p];
            else
                return result;
        }

        p = b;
        n = base[p];

        if (b == check[p] && n < 0)
        {
            result.add(new AbstractMap.SimpleEntry<String, V>(key, v[-n - 1]));
        }

        return result;
    }

    /**
     * 优化的�?缀查询，�?�以�?用字符数组
     *
     * @param keyChars
     * @param begin
     * @return
     */
    public LinkedList<Map.Entry<String, V>> commonPrefixSearchWithValue(char[] keyChars, int begin)
    {
        int len = keyChars.length;
        LinkedList<Map.Entry<String, V>> result = new LinkedList<Map.Entry<String, V>>();
        int b = base[0];
        int n;
        int p;

        for (int i = begin; i < len; ++i)
        {
            p = b;
            n = base[p];
            if (b == check[p] && n < 0)         // base[p] == check[p] && base[p] < 0 查到一个�?
            {
                result.add(new AbstractMap.SimpleEntry<String, V>(new String(keyChars, begin, i - begin), v[-n - 1]));
            }

            p = b + (int) (keyChars[i]) + 1;    // 状�?转移 p = base[char[i-1]] + char[i] + 1
            // 下�?�这�?��?�能产生下标越界，�?如改为if (p < size && b == check[p])，或者多分�?一些内存
            if (b == check[p])                  // base[char[i-1]] == check[base[char[i-1]] + char[i] + 1]
                b = base[p];
            else
                return result;
        }

        p = b;
        n = base[p];

        if (b == check[p] && n < 0)
        {
            result.add(new AbstractMap.SimpleEntry<String, V>(new String(keyChars, begin, len - begin), v[-n - 1]));
        }

        return result;
    }

    @Override
    public String toString()
    {
//        String infoIndex    = "i    = ";
//        String infoChar     = "char = ";
//        String infoBase     = "base = ";
//        String infoCheck    = "check= ";
//        for (int i = 0; i < base.length; ++i)
//        {
//            if (base[i] != 0 || check[i] != 0)
//            {
//                infoChar  += "    " + (i == check[i] ? " ×" : (char)(i - check[i] - 1));
//                infoIndex += " " + String.format("%5d", i);
//                infoBase  += " " +  String.format("%5d", base[i]);
//                infoCheck += " " + String.format("%5d", check[i]);
//            }
//        }
        return "DoubleArrayTrie{" +
//                "\n" + infoChar +
//                "\n" + infoIndex +
//                "\n" + infoBase +
//                "\n" + infoCheck + "\n" +
//                "check=" + Arrays.toString(check) +
//                ", base=" + Arrays.toString(base) +
//                ", used=" + Arrays.toString(used) +
                "size=" + size +
                ", allocSize=" + allocSize +
                ", key=" + key +
                ", keySize=" + keySize +
//                ", length=" + Arrays.toString(length) +
//                ", value=" + Arrays.toString(value) +
                ", progress=" + progress +
                ", nextCheckPos=" + nextCheckPos +
                ", error_=" + error_ +
                '}';
    }

    /**
     * 树�?��?节点个数
     *
     * @return
     */
    public int size()
    {
        return v.length;
    }

    /**
     * 获�?�check数组引用，�?�?修改check
     *
     * @return
     */
    public int[] getCheck()
    {
        return check;
    }

    /**
     * 获�?�base数组引用，�?�?修改base
     *
     * @return
     */
    public int[] getBase()
    {
        return base;
    }

    /**
     * 获�?�index对应的值
     *
     * @param index
     * @return
     */
    public V getValueAt(int index)
    {
        return v[index];
    }

    /**
     * 精确查询
     *
     * @param key 键
     * @return 值
     */
    public V get(String key)
    {
        int index = exactMatchSearch(key);
        if (index >= 0)
        {
            return getValueAt(index);
        }

        return null;
    }

    public V get(char[] key)
    {
        int index = exactMatchSearch(key, 0, key.length, 0);
        if (index >= 0)
        {
            return getValueAt(index);
        }

        return null;
    }

    public V[] getValueArray(V[] a)
    {
        // I hate this but just have to
        int size = v.length;
        if (a.length < size)
            a = (V[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        System.arraycopy(v, 0, a, 0, size);
        return a;
    }

    public boolean containsKey(String key)
    {
        return exactMatchSearch(key) >= 0;
    }

    /**
     * 沿�?�路径转移状�?
     *
     * @param path
     * @return
     */
    protected int transition(String path)
    {
        return transition(path.toCharArray());
    }

    /**
     * 沿�?�节点转移状�?
     *
     * @param path
     * @return
     */
    protected int transition(char[] path)
    {
        int b = base[0];
        int p;

        for (int i = 0; i < path.length; ++i)
        {
            p = b + (int) (path[i]) + 1;
            if (b == check[p])
                b = base[p];
            else
                return -1;
        }

        p = b;
        return p;
    }

    /**
     * 沿�?�路径转移状�?
     *
     * @param path 路径
     * @param from 起点（根起点为base[0]=1）
     * @return 转移�?�的状�?（�?�数组下标）
     */
    public int transition(String path, int from)
    {
        int b = from;
        int p;

        for (int i = 0; i < path.length(); ++i)
        {
            p = b + (int) (path.charAt(i)) + 1;
            if (b == check[p])
                b = base[p];
            else
                return -1;
        }

        p = b;
        return p;
    }

    /**
     * 转移状�?
     * @param c
     * @param from
     * @return
     */
    public int transition(char c, int from)
    {
        int b = from;
        int p;

        p = b + (int) (c) + 1;
        if (b == check[p])
            b = base[p];
        else
            return -1;

        return b;
    }

    /**
     * 检查状�?是�?�对应输出
     *
     * @param state �?�数组下标
     * @return 对应的值，null表示�?输出
     */
    public V output(int state)
    {
        if (state < 0) return null;
        int n = base[state];
        if (state == check[state] && n < 0)
        {
            return v[-n - 1];
        }
        return null;
    }

    /**
     * 一个�?�索工具（注�?，当调用next()返回false�?��?应该继续调用next()，除�?�reset状�?）
     */
    public class Searcher
    {
        /**
         * key的起点
         */
        public int begin;
        /**
         * key的长度
         */
        public int length;
        /**
         * key的字典�?�??标
         */
        public int index;
        /**
         * key对应的value
         */
        public V value;
        /**
         * 传入的字符数组
         */
        private char[] charArray;
        /**
         * 上一个node�?置
         */
        private int last;
        /**
         * 上一个字符的下标
         */
        private int i;
        /**
         * charArray的长度，效率起�?，开个�?��?
         */
        private int arrayLength;

        /**
         * 构造一个�?�数组�?�索工具
         *
         * @param offset    �?�索的起始�?置
         * @param charArray �?�索的目标字符数组
         */
        public Searcher(int offset, char[] charArray)
        {
            this.charArray = charArray;
            i = offset;
            last = base[0];
            arrayLength = charArray.length;
            // A trick，如果文本长度为0的�?，调用next()时，会带�?�越界的问题。
            // 所以我�?在第一次调用next()的时候触�?�begin == arrayLength进而返回false。
            // 当然也�?�以改�?begin >= arrayLength，�?过我觉得�?作符>=的效率低于==
            if (arrayLength == 0) begin = -1;
            else begin = offset;
        }

        /**
         * �?�出下一个命中输出
         *
         * @return 是�?�命中，当返回false表示�?�索结�?�，�?�则使用公开的�?员读�?�命中的详细信�?�
         */
        public boolean next()
        {
            int b = last;
            int n;
            int p;

            for (; ; ++i)
            {
                if (i == arrayLength)               // 指针到头了，将起点往�?挪一个，�?新开始，状�?归零
                {
                    ++begin;
                    if (begin == arrayLength) break;
                    i = begin;
                    b = base[0];
                }
                p = b + (int) (charArray[i]) + 1;   // 状�?转移 p = base[char[i-1]] + char[i] + 1
                if (b == check[p])                  // base[char[i-1]] == check[base[char[i-1]] + char[i] + 1]
                    b = base[p];                    // 转移�?功
                else
                {
                    i = begin;                      // 转移失败，也将起点往�?挪一个，�?新开始，状�?归零
                    ++begin;
                    if (begin == arrayLength) break;
                    b = base[0];
                    continue;
                }
                p = b;
                n = base[p];
                if (b == check[p] && n < 0)         // base[p] == check[p] && base[p] < 0 查到一个�?
                {
                    length = i - begin + 1;
                    index = -n - 1;
                    value = v[index];
                    last = b;
                    ++i;
                    return true;
                }
            }

            return false;
        }
    }

    public Searcher getSearcher(String text)
    {
        return getSearcher(text, 0);
    }

    public Searcher getSearcher(String text, int offset)
    {
        return new Searcher(offset, text.toCharArray());
    }

    public Searcher getSearcher(char[] text, int offset)
    {
        return new Searcher(offset, text);
    }

    /**
     * 一个最长�?�索工具（注�?，当调用next()返回false�?��?应该继续调用next()，除�?�reset状�?）
     */
    public class LongestSearcher
    {
        /**
         * key的起点
         */
        public int begin;
        /**
         * key的长度
         */
        public int length;
        /**
         * key的字典�?�??标
         */
        public int index;
        /**
         * key对应的value
         */
        public V value;
        /**
         * 传入的字符数组
         */
        private char[] charArray;
        /**
         * 上一个字符的下标
         */
        private int i;
        /**
         * charArray的长度，效率起�?，开个�?��?
         */
        private int arrayLength;

        /**
         * 构造一个�?�数组�?�索工具
         *
         * @param offset    �?�索的起始�?置
         * @param charArray �?�索的目标字符数组
         */
        public LongestSearcher(int offset, char[] charArray)
        {
            this.charArray = charArray;
            i = offset;
            arrayLength = charArray.length;
            begin = offset;
        }

        /**
         * �?�出下一个命中输出
         *
         * @return 是�?�命中，当返回false表示�?�索结�?�，�?�则使用公开的�?员读�?�命中的详细信�?�
         */
        public boolean next()
        {
            value = null;
            begin = i;
            int b = base[0];
            int n;
            int p;

            for (; ; ++i)
            {
                if (i >= arrayLength)               // 指针到头了，将起点往�?挪一个，�?新开始，状�?归零
                {
                    return value != null;
                }
                p = b + (int) (charArray[i]) + 1;   // 状�?转移 p = base[char[i-1]] + char[i] + 1
                if (b == check[p])                  // base[char[i-1]] == check[base[char[i-1]] + char[i] + 1]
                    b = base[p];                    // 转移�?功
                else
                {
                    if (begin == arrayLength) break;
                    if (value != null)
                    {
                        i = begin + length;         // 输出最长�?�?�，从该�?语的下一个�?置�?��?扫�??
                        return true;
                    }

                    i = begin;                      // 转移失败，也将起点往�?挪一个，�?新开始，状�?归零
                    ++begin;
                    b = base[0];
                }
                p = b;
                n = base[p];
                if (b == check[p] && n < 0)         // base[p] == check[p] && base[p] < 0 查到一个�?
                {
                    length = i - begin + 1;
                    index = -n - 1;
                    value = v[index];
                }
            }

            return false;
        }
    }

    /**
     * 全切分
     *
     * @param text      文本
     * @param processor 处�?�器
     */
    public void parseText(String text, AhoCorasickDoubleArrayTrie.IHit<V> processor)
    {
        Searcher searcher = getSearcher(text, 0);
        while (searcher.next())
        {
            processor.hit(searcher.begin, searcher.begin + searcher.length, searcher.value);
        }
    }

    public LongestSearcher getLongestSearcher(String text, int offset)
    {
        return getLongestSearcher(text.toCharArray(), offset);
    }

    public LongestSearcher getLongestSearcher(char[] text, int offset)
    {
        return new LongestSearcher(offset, text);
    }

    /**
     * 最长匹�?
     *
     * @param text      文本
     * @param processor 处�?�器
     */
    public void parseLongestText(String text, AhoCorasickDoubleArrayTrie.IHit<V> processor)
    {
        LongestSearcher searcher = getLongestSearcher(text, 0);
        while (searcher.next())
        {
            processor.hit(searcher.begin, searcher.begin + searcher.length, searcher.value);
        }
    }

    /**
     * 转移状�?
     *
     * @param current
     * @param c
     * @return
     */
    protected int transition(int current, char c)
    {
        int b = base[current];
        int p;

        p = b + c + 1;
        if (b == check[p])
            b = base[p];
        else
            return -1;

        p = b;
        return p;
    }

    /**
     * 更新�?个键对应的值
     *
     * @param key   键
     * @param value 值
     * @return 是�?��?功（失败的原因是没有这个键）
     */
    public boolean set(String key, V value)
    {
        int index = exactMatchSearch(key);
        if (index >= 0)
        {
            v[index] = value;
            return true;
        }

        return false;
    }

    /**
     * 从值数组中�??�?�下标为index的值<br>
     * 注�?为了效率，此处�?进行�?�数校验
     *
     * @param index 下标
     * @return 值
     */
    public V get(int index)
    {
        return v[index];
    }

    /**
     * 释放空闲的内存
     */
    private void shrink()
    {
//        if (HanLP.Config.DEBUG)
//        {
//            System.err.printf("释放内存 %d bytes\n", base.length - size - 65535);
//        }
        int nbase[] = new int[size + 65535];
        System.arraycopy(base, 0, nbase, 0, size);
        base = nbase;

        int ncheck[] = new int[size + 65535];
        System.arraycopy(check, 0, ncheck, 0, size);
        check = ncheck;
    }


    /**
     * 打�?�统计信�?�
     */
//    public void report()
//    {
//        System.out.println("size: " + size);
//        int nonZeroIndex = 0;
//        for (int i = 0; i < base.length; i++)
//        {
//            if (base[i] != 0) nonZeroIndex = i;
//        }
//        System.out.println("BaseUsed: " + nonZeroIndex);
//        nonZeroIndex = 0;
//        for (int i = 0; i < check.length; i++)
//        {
//            if (check[i] != 0) nonZeroIndex = i;
//        }
//        System.out.println("CheckUsed: " + nonZeroIndex);
//    }
}
