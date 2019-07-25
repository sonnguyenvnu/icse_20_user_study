/** 
 * @param map another map
 * @return a copy of the map, with the same equality semantics
 */
public static <K,V>LinearMap<K,V> from(IMap<K,V> map){
  if (map instanceof LinearMap) {
    return ((LinearMap<K,V>)map).clone();
  }
 else {
    LinearMap<K,V> result=new LinearMap<K,V>((int)map.size(),map.keyHash(),map.keyEquality());
    map.forEach(e -> result.put(e.key(),e.value()));
    return result;
  }
}
