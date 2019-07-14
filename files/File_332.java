package com.crossoverjie.concurrent;

/**
 * Function:å?•ä¾‹æ¨¡å¼?-å?Œé‡?æ£€æŸ¥é”?
 *
 * @author crossoverJie
 *         Date: 09/03/2018 01:14
 * @since JDK 1.8
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    //é˜²æ­¢æŒ‡ä»¤é‡?æŽ’
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
