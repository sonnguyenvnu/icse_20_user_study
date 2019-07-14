/** 
 * Removes an indexed range from a List. <p>Does nothing if the provided range is valid and  {@code fromIndex == toIndex}.
 * @param list The List to remove the range from.
 * @param fromIndex The first index to be removed (inclusive).
 * @param toIndex The last index to be removed (exclusive).
 * @throws IllegalArgumentException If {@code fromIndex} &lt; 0, {@code toIndex} &gt; {@code list.size()}, or  {@code fromIndex} &gt; {@code toIndex}.
 */
public static <T>void removeRange(List<T> list,int fromIndex,int toIndex){
  if (fromIndex < 0 || toIndex > list.size() || fromIndex > toIndex) {
    throw new IllegalArgumentException();
  }
 else   if (fromIndex != toIndex) {
    list.subList(fromIndex,toIndex).clear();
  }
}
