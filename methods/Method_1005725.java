/** 
 * Find the "best guess" middle value among comparables. If there is an even number of total values, the lower of the two middle values will be returned.
 * @param < T > type of values processed by this method
 * @param items to compare
 * @return T at middle position
 * @throws NullPointerException if items is {@code null}
 * @throws IllegalArgumentException if items is empty or contains {@code null} values
 * @since 3.0.1
 */
public static <T extends Comparable<? super T>>T median(T... items){
  Validate.notEmpty(items);
  Validate.noNullElements(items);
  TreeSet<T> sort=new TreeSet<T>();
  Collections.addAll(sort,items);
  @SuppressWarnings("unchecked") T result=(T)sort.toArray()[(sort.size() - 1) / 2];
  return result;
}
