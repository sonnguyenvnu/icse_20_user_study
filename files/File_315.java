package com.crossoverjie.actual;

/**
 * Function: 两个线程交替执行打�?� 1~100
 * <p>
 * non blocking 版：
 * 两个线程轮询volatile�?��?(flag) 
 * 线程一"看到"flag值为1时执行代�?并将flag设置为0,
 * 线程二"看到"flag值为0时执行代�?并将flag设置未1,
 * 2个线程�?断轮询直到满足�?�件退出
 *
 * @author twoyao
 * Date: 05/07/2018
 * @since JDK 1.8
 */

public class TwoThreadNonBlocking implements Runnable {

    /**
     * 当flag为1时�?�有奇数线程�?�以执行，并将其置为0
     * 当flag为0时�?�有�?�数线程�?�以执行，并将其置为1
     */
    private volatile static int flag = 1;

    private int start;
    private int end;
    private String name;

    private TwoThreadNonBlocking(int start, int end, String name) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        while (start <= end) {
            int f = flag;
            if ((start & 0x01) == f) {
                System.out.println(name + "+-+" + start);
                start += 2;
                // 因为�?��?�能�?�时存在一个线程修改该值，所以�?会存在竞争
                flag ^= 0x1;
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new TwoThreadNonBlocking(1, 100, "t1")).start();
        new Thread(new TwoThreadNonBlocking(2, 100, "t2")).start();
    }
}
