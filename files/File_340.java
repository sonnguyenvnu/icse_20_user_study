package com.crossoverjie.gc;

/**
 * Function: Eden区�?够分�?时，�?�生minorGC
 *
 * @author crossoverJie
 *         Date: 17/01/2018 22:57
 * @since JDK 1.8
 */
public class MinorGC {

    /**
     * 1M
     */
    private static final int SIZE = 1024 * 1024 ;

    /**
     *
     -XX:+PrintGCDetails
     -Xms20M
     -Xmx20M
     -Xmn10M
     -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        byte[] one ;
        byte[] four ;

        one = new byte[2 * SIZE] ;


        //�?分�?一个 5M 内存时，Eden区�?够了，
        four = new byte[5 * SIZE] ;
    }
}
