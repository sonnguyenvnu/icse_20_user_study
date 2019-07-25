/** 
 * @return an iterator over the list which repeatedly calls nth()
 */
public static <V>Iterator<V> iterator(IList<V> list){
  return Iterators.range(list.size(),list::nth);
}
