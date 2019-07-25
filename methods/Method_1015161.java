/** 
 * @param collection a collection of {@code java.util.map.Entry} objects
 * @return an {@code IntMap} representing the entries in the collection
 */
public static <V>IntMap<V> from(Collection<Map.Entry<Number,V>> collection){
  IntMap<V> map=new IntMap<V>().linear();
  for (  Map.Entry<Number,V> entry : collection) {
    map=map.put((long)entry.getKey(),entry.getValue());
  }
  return map.forked();
}
