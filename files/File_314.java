package com.crossoverjie.actual;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 2018/10/13 20:00
 * @since JDK 1.8
 */

import org.junit.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Function:
 *
 ä¸€ä¸ªâ€œ.â€?ä»£è¡¨ä¸€ä¸ªä»»æ„?å­—æ¯?ã€‚

 æ³¨æ„?äº‹é¡¹ï¼šå?¯ä»¥å?‡è®¾æ‰€æœ‰çš„å?•è¯?å?ªåŒ…å?«å°?å†™å­—æ¯?â€œa-zâ€?

 æ ·ä¾‹ï¼š
 addWord(â€œbadâ€?);
 addWord(â€œdadâ€?);
 addWord(â€œmadâ€?);
 search(â€œpadâ€?);  // return false;
 search(â€œbadâ€?);  // return true;
 search(â€œ.adâ€?); // return true;
 search(â€œb..â€?); // return true;


 å¦‚æžœæœ‰å¹¶å?‘çš„æƒ…å†µä¸‹ï¼Œaddword() æ€Žä¹ˆå¤„ç?†ï¼Ÿ
 *
 * @author crossoverJie
 * @since JDK 1.8
 */
public class Search {

    private static Map<String,String> ALL_MAP = new ConcurrentHashMap<>(50000) ;


    /**
     * æ?¢æˆ? asciiç ? æ›´çœ?äº‹
     */
    private static final char[] dictionary = {'a','b','c','d','m','p'} ;

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    addWord(i + "ad");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    addWord(i + "bd");
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    addWord(i + "cd");
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    addWord(i + "dd");
                }
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    addWord(i + "ed");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        System.out.println(ALL_MAP.size());
        Assert.assertEquals(50000,ALL_MAP.size());


        addWord("bad");
        addWord("dad");
        addWord("mad");
        boolean pad = search("pad");
        System.out.println(pad);
        Assert.assertFalse(pad);

        boolean bad = search("bad");
        System.out.println(bad);
        Assert.assertTrue(bad);


        boolean ad = search(".ad");
        System.out.println(ad);
        Assert.assertTrue(ad);


        boolean bsearch = search("b..");
        System.out.println(bsearch);
        Assert.assertTrue(bsearch);

        boolean asearch = search(".a.");
        System.out.println(asearch);


        boolean search = search(".af");
        System.out.println(search);


        boolean search1 = search(null);
        System.out.println(search1);

    }

    public static boolean search(String keyWord){
        boolean result = false ;
        if (null ==  keyWord || keyWord.trim().equals("")){
            return result ;
        }

        //å?šä¸€æ¬¡å®Œæ•´åŒ¹é…?
        String whole = ALL_MAP.get(keyWord) ;
        if (whole != null){
            return true ;
        }

        char[] wordChars = keyWord.toCharArray() ;

        for (int i = 0; i < wordChars.length; i++) {
            char wordChar = wordChars[i] ;

            if (46 != (int)wordChar){
                continue ;
            }

            for (char dic : dictionary) {
                wordChars[i] = dic ;
                boolean search = search(String.valueOf(wordChars));

                if (search){
                    return search ;
                }

                String value = ALL_MAP.get(String.valueOf(wordChars));
                if (value != null){
                    return true ;
                }
            }

        }


        return result ;
    }


    public static void addWord(String word){
        ALL_MAP.put(word,word) ;
    }
}
