/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hankcs.hanlp.collection.dartsclone.details;

/**
 * key set，其实也包�?�值（�?个key都有一个整型数）
 * @author manabe
 */
public class Keyset
{
    /**
     * 构造一个KeySet
     * @param keys 字节类型的key
     * @param values �?个key对应的值
     */
    public Keyset(byte[][] keys, int[] values)
    {
        _keys = keys;
        _values = values;
    }

    /**
     * keyset的容�?
     * @return
     */
    int numKeys()
    {
        return _keys.length;
    }

    /**
     * 根�?�id获�?�key
     * @param id
     * @return
     */
    byte[] getKey(int id)
    {
        return _keys[id];
    }

    /**
     * 获�?��?个key的�?一个字节
     * @param keyId key的id
     * @param byteId 字节的下标（第几个字节）
     * @return 字节，返回0表示越界了
     */
    byte getKeyByte(int keyId, int byteId)
    {
        if (byteId >= _keys[keyId].length)
        {
            return 0;
        }
        return _keys[keyId][byteId];
    }

    /**
     * 是�?��?�有值
     * @return
     */
    boolean hasValues()
    {
        return _values != null;
    }

    /**
     * 根�?�下标获�?�值
     * @param id
     * @return
     */
    int getValue(int id)
    {
        if (hasValues())
        {
            return _values[id];
        }
        return id;
    }

    /**
     * 键
     */
    private byte[][] _keys;
    /**
     * 值
     */
    private int _values[];
}
