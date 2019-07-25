/** 
 * @param map a {@code java.util.Map}
 * @return a forked map with the same entries
 */
public static <K,V>Map<K,V> from(java.util.Map<K,V> map){
  return map.entrySet().stream().collect(Maps.collector(java.util.Map.Entry::getKey,java.util.Map.Entry::getValue));
}
