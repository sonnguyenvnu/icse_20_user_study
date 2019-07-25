/** 
 * Find the "best guess" middle value among comparables. If there is an even number of total values, the lower of the two middle values will be returned.
 * @param < T > type of values processed by this method
 * @param comparator to use for comparisons
 * @param items to compare
 * @return T at middle position
 * @throws NullPointerException if items or comparator is {@code null}
 * @throws IllegalArgumentException if items is empty or contains {@code null} values
 * @since 3.0.1
 */
public static <T>T median(Comparator<T> comparator,T... items){
  Validate.notEmpty(items,"null/empty items");
  Validate.noNullElements(items);
  Validate.notNull(comparator,"null comparator");
  TreeSet<T> sort=new TreeSet<T>(comparator);
  Collections.addAll(sort,items);
  @SuppressWarnings("unchecked") T result=(T)sort.toArray()[(sort.size() - 1) / 2];
  return result;
}
