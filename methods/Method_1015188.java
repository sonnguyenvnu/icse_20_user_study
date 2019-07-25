/** 
 * @param iterator an iterator
 * @return a {@code LinearSet} containing the remaining elements in the iterator
 */
public static <V>LinearSet<V> from(Iterator<V> iterator){
  LinearSet<V> set=new LinearSet<V>();
  iterator.forEachRemaining(set::add);
  return set;
}
