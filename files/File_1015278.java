/**
 * 文   件  �??：  RandomUtil.java
 * 工   程  �??：  MainServer
 * 创建日期：  2015年2月5日 下�?�2:38:48
 * 创建作者：  �?�  强 <281455776@qq.com>
 */
package info.xiaomo.core.untils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * �?机工具类
 *
 * @author : xiaomo
 */
public class RandomUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtil.class);
    private static final String NUM_S = "0123456789";
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * �?机产生min到max之间的一个整数值，包�?�min和max
     */
    public static int random(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("传入的范围�?�?�法!最�?值�?能大于最大值�?");
        }
        return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
    }

    /**
     * 根�?�几率计算是�?�生�?，�?功几率是sucRange/maxRange
     *
     * @param maxRange 最大范围，�?机范围是[1,maxRange]
     * @param sucRange �?功范围，�?功范围是[1,sucRange]
     * @return �?功true失败false
     */
    public static boolean isGenerate(int maxRange, int sucRange) {
        return maxRange == sucRange || sucRange != 0 && random(1, maxRange) <= sucRange;
    }

    /**
     * 从指定的的元素集中�?机一个元素
     *
     * @param collection 元素集
     */
    public static <T> T randomElement(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("元素集�?能为空�?");
        }
        int index = random(0, collection.size() - 1);
        Iterator<T> it = collection.iterator();
        for (int i = 0; i <= index && it.hasNext(); i++) {
            T t = it.next();
            if (i == index) {
                return t;
            }
        }
        return null;
    }


    /**
     * 生�?一个10�?的tonken用于http cache(纯数字)
     *
     * @return String    返回类型(纯数字)
     */
    public static String getTonken() {
        return RandomStringUtils.random(10, NUM_S);
    }

    /**
     * 生�?�?机数
     *
     * @return String    返回类型
     */
    public static String randomPwd(int count) {
        return RandomStringUtils.random(count, STR_S);
    }

    /**
     * 生�?�?机数
     *
     * @return String    返回类型
     */
    public static String randomPwd() {
        return RandomStringUtils.random(10, STR_S);
    }

    /**
     * 从指定的元素数组中�?机出一个元素
     *
     * @param array 元素数组
     */
    public static <T> T randomElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("元素数组�?能为空�?");
        }
        return randomElement(Arrays.asList(array));
    }

    /**
     * 根�?��?个几率返回�?机的一个索引
     *
     * @return -1失败or�?机的索引
     */
    public static int randomIndexByProb(List<Integer> probs) {
        LinkedList<Integer> newProbs = new LinkedList<Integer>();
        int lastTotalProb = 0;
        for (Integer prob : probs) {
            int cuttentTotalProb = lastTotalProb + prob;
            newProbs.add(cuttentTotalProb);
            lastTotalProb = cuttentTotalProb;
        }
        if (newProbs.isEmpty()) {
            return -1;
        }
        int totalProb = newProbs.getLast();
        // 总概率为0
        if (totalProb == 0) {
            return -1;
        }
        int random = random(0, totalProb - 1);
        for (int i = 0; i < newProbs.size(); i++) {
            int cuttentTotalProb = newProbs.get(i);
            if (cuttentTotalProb > random) {
                return i;
            }
        }
        LOGGER.error("计算概率错误{}", probs.toString());
        return -1;
    }

    /**
     * 根�?��?个几率返回�?机的一个索引
     *
     * @return -1失败or�?机的索引
     */
    public static int randomIndexByProb(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("元素数组�?能为空�?");
        }
        List<Integer> list;
        list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        return randomIndexByProb(list);
    }

    /**
     * 生�?�?值
     *
     * @return
     */
    public static String createSalt() {
        return randomPwd(10);
    }


    /**
     * 生�?�?值
     *
     * @param count
     * @return
     */
    public static String createSalt(int count) {
        return randomPwd(count);
    }

    public static void main(String[] args) {
        String salt = createSalt();
        System.out.println(salt);
        System.out.println(Md5Util.encode("xiaomo", salt));
    }
}
