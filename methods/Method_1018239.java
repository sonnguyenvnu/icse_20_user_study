/** 
 * When an item ages, its use count is reduced by at least half.
 * @param ticks The current use count of an item.
 * @return The new use count for that item.
 */
public static long age(long ticks){
  return ticks >= 32 ? 16 : ticks / 2;
}
