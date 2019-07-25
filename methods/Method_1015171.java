/** 
 * @param iterator an {@code java.util.Iterator}
 * @return a list containing all remaining elements of the iterator
 */
public static <V>LinearList<V> from(Iterator<V> iterator){
  LinearList<V> list=new LinearList<V>();
  iterator.forEachRemaining(list::addLast);
  return list;
}
