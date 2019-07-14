package com.crossoverjie.red;

import java.util.LinkedList;
import java.util.List;

/**
 * Function: 模拟微信红包生�?，以分为�?��?
 *
 * @author crossoverJie
 *         Date: 03/01/2018 16:52
 * @since JDK 1.8
 */
public class RedPacket {

    /**
     * 生�?红包最�?值 1分
     */
    private static final int MIN_MONEY = 1;

    /**
     * 生�?红包最大值 200人民�?
     */
    private static final int MAX_MONEY = 200 * 100;

    /**
     * �?于最�?值
     */
    private static final int LESS = -1;
    /**
     * 大于最大值
     */
    private static final int MORE = -2;

    /**
     * 正常值
     */
    private static final int OK = 1;

    /**
     * 最大的红包是平�?�值的 TIMES �?，防止�?一次分�?红包较大
     */
    private static final double TIMES = 2.1F;

    private int recursiveCount = 0;

    public List<Integer> splitRedPacket(int money, int count) {
        List<Integer> moneys = new LinkedList<>();

        //金�?检查，如果最大红包 * 个数 < 总金�?；则需�?调大最�?红包 MAX_MONEY
        if (MAX_MONEY * count <= money) {
            System.err.println("请调大最�?红包金�? MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys ;
        }


        //计算出最大红包
        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for (int i = 0; i < count; i++) {
            //�?机获�?�红包
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            //总金�?�?次�?少
            money -= redPacket;
        }

        return moneys;
    }

    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {
        //�?�有一个红包直接返回
        if (count == 1) {
            return totalMoney;
        }

        if (minMoney == maxMoney) {
            return minMoney;
        }

        //如果最大金�?大于了剩余金�? 则用剩余金�? 因为这个 money �?分�?一次都会�?�?
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        //在 minMoney到maxMoney 生�?一个�?机红包
        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count - 1);

        //正常金�?
        if (OK == status) {
            return redPacket;
        }

        //如果生�?的金�?�?�?�法 则递归�?新生�?
        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    /**
     * 校验剩余的金�?的平�?�值是�?�在 最�?值和最大值这个范围内
     *
     * @param lastMoney
     * @param count
     * @return
     */
    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }


    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        List<Integer> redPackets = redPacket.splitRedPacket(20000, 100);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }

}
