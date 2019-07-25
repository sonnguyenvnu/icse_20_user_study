/// Leetcode 380. Insert Delete GetRandom O(1)
/// https://leetcode.com/problems/insert-delete-getrandom-o1/description/
///
/// 使用380�?�问题测试我们的代�?
/// 该版本代�?使用Java内置的HashMap，作为算法实现的基准

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;


public class RandomizedSet_HashMap {

    HashMap<String, Integer> map;
    ArrayList<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedSet_HashMap() {
        map = new HashMap();
        nums = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {

        String key = Integer.toString(val);
        if(map.containsKey(key))
            return false;

        nums.add(val);
        int index = nums.size() - 1;
        map.put(key, index);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {

        String key = Integer.toString(val);
        if(!map.containsKey(key))
            return false;

        int index = map.get(key);
        map.remove(key);

        int num = nums.get(nums.size() - 1);
        nums.remove(nums.size() - 1);

        if(num != val) {
            nums.set(index, num);
            map.put(Integer.toString(num), index);
        }

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {

        Random random = new Random();
        int index = random.nextInt(nums.size());
        return nums.get(index);
    }

    public static void main(String[] args){

        RandomizedSet_TrieR rs = new RandomizedSet_TrieR();
        rs.insert(0);
        rs.remove(0);
        rs.insert(-1);
        rs.remove(0);
    }
}
