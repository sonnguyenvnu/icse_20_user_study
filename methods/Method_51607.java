/** 
 * Returns a map based on the source but with the key &amp; values swapped.
 * @param source Map
 * @return Map
 */
public static <K,V>Map<V,K> invertedMapFrom(Map<K,V> source){
  Map<V,K> map=new HashMap<>(source.size());
  for (  Map.Entry<K,V> entry : source.entrySet()) {
    map.put(entry.getValue(),entry.getKey());
  }
  return map;
}
