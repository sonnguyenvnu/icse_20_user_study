/** 
 * Creates and returns a map populated with the keyValuesSets where the value held by the tuples are they key and value in that order.
 * @param keys K[]
 * @param values V[]
 * @return Map
 */
public static <K,V>Map<K,V> mapFrom(K[] keys,V[] values){
  if (keys.length != values.length) {
    throw new RuntimeException("mapFrom keys and values arrays have different sizes");
  }
  Map<K,V> map=new HashMap<>(keys.length);
  for (int i=0; i < keys.length; i++) {
    map.put(keys[i],values[i]);
  }
  return map;
}
