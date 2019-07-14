package com.geekq.miasha.utils;
/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(�?部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1�?标识，由于long基本类型在Java中是带符�?�的，最高�?是符�?��?，正数是0，负数是1，所以id一般是正数，最高�?是0<br>
 * 41�?时间截(毫秒级)，注�?，41�?时间截�?是存储当�?时间的时间截，而是存储时间截的差值（当�?时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生�?器开始使用的时间，由我们程�?�?�指定的（如下下�?�程�?IdWorker类的startTime属性）。41�?的时间截，�?�以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10�?的数�?�机器�?，�?�以部署在1024个节点，包括5�?datacenterId和5�?workerId<br>
 * 12�?�?列，毫秒内的计数，12�?的计数顺�?�?�支�?�?个节点�?毫秒(�?�一机器，�?�一时间截)产生4096个ID�?�?�<br>
 * 加起�?�刚好64�?，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排�?，并且整个分布�?系统内�?会产生ID碰撞(由数�?�中心ID和机器ID作区分)，并且效率较高，�?测试，SnowFlake�?秒能够产生26万ID左�?�。
 */
public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1420041600000L;

    /** 机器id所�?�的�?数 */
    private final long workerIdBits = 5L;

    /** 数�?�标识id所�?�的�?数 */
    private final long datacenterIdBits = 5L;

    /** 支�?的最大机器id，结果是31 (这个移�?算法�?�以很快的计算出几�?二进制数所能表示的最大�??进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支�?的最大数�?�标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** �?列在id中�?�的�?数 */
    private final long sequenceBits = 12L;

    /** 机器ID�?�左移12�? */
    private final long workerIdShift = sequenceBits;

    /** 数�?�标识id�?�左移17�?(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截�?�左移22�?(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生�?�?列的掩�?，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数�?�中心ID(0~31) */
    private long datacenterId;

    /** 毫秒内�?列(0~4095) */
    private long sequence = 0L;

    /** 上次生�?ID的时间截 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数�?�中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 生�?订�?�唯一ID
     * @param workerId
     * @param datacenterId
     * @return
     */
    public static long getOrderId(long workerId, long datacenterId){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        return idWorker.nextId();
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当�?时间�?于上一次ID生�?的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是�?�一时间生�?的，则进行毫秒内�?列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内�?列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改�?�，毫秒内�?列�?置
        else {
            sequence = 0L;
        }

        //上次生�?ID的时间截
        lastTimestamp = timestamp;

        //移�?并通过或�?算拼到一起组�?64�?的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生�?ID的时间截
     * @return 当�?时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为�?��?的当�?时间
     * @return 当�?时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }







}
