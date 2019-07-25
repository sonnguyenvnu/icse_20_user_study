package utils;

import java.util.Random;

/**
 * 生�?�?机数模�?�
 */
public class SysRandom {

    private static Random random = new Random();   //伪�?机数�?�生器

    private static double random(){
        return random.nextDouble();
    }

    /**
     * �?机返回 [0,n)之间的一个int值
     * @param n
     * @return
     */
    public static int random(int n) {
        if (n <= 0) throw new IllegalArgumentException("Parameter N must be positive");
        return random.nextInt(n);
    }

    /**
     * �?机返回 [a,b)之间的一个double值
     * @param a
     * @param b
     * @return
     */
    public static double random(double a, double b) {
        if (!(a < b)) throw new IllegalArgumentException("Invalid range");
        return a + random() * (b-a);
    }

    /**
     * �?机返回 [a,b)之间的一个int值
     * @param a
     * @param b
     * @return
     */
    public static int random(int a, int b) {
        if (b <= a) throw new IllegalArgumentException("Invalid range");
        if ((long) b - a >= Integer.MAX_VALUE) throw new IllegalArgumentException("Invalid range");
        return a + random(b - a);
    }

    /**
     * 返回一个[0,100)范围的�?机int型数组
     * @param a
     * @return
     */
    public static int[] random(int[] a){
        for (int i = 0; i < a.length; i++) {
            a[i] = random(100);
        }
        return a;
    }
/*    public static int[] random(int[] a){
        int length = a.length;
        IntStream intStream = random.ints(0,100);   //创建一个int型 [0,100)范围的数字�?

        List<Integer> list = intStream.limit(length).boxed().collect(Collectors.toList());
        for (int i = 0; i < length; i++) {
            a[i] = list.get(i);
        }
        return a;
    }*/
}
