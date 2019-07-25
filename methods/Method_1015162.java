/** 
 * @param list a list of {@code IEntry} objects
 * @return an {@code IntMap} representing the entries in the list
 */
public static <V>IntMap<V> from(IList<IEntry<Number,V>> list){
  IntMap<V> map=new IntMap<V>().linear();
  for (  IEntry<Number,V> entry : list) {
    map=map.put((long)entry.key(),entry.value());
  }
  return map.forked();
}
