/** 
 * @param list a list of {@code IEntry} objects
 * @return an {@code IntMap} representing the entries in the list
 */
public static <V>FloatMap<V> from(IList<IEntry<Number,V>> list){
  FloatMap<V> map=new FloatMap<V>().linear();
  for (  IEntry<Number,V> entry : list) {
    map=map.put(entry.key().doubleValue(),entry.value());
  }
  return map.forked();
}
