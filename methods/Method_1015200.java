/** 
 * @param map another map
 * @return an equivalent forked map, with the same equality semantics
 */
public static <K,V>Map<K,V> from(IMap<K,V> map){
  if (map instanceof Map) {
    return (Map<K,V>)map.forked();
  }
 else {
    Map<K,V> result=new Map<K,V>(map.keyHash(),map.keyEquality()).linear();
    map.forEach(e -> result.put(e.key(),e.value()));
    return result.forked();
  }
}
