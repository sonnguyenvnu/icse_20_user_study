/** 
 * A lens that focuses on the keys of a map.
 * @param < K > the key type
 * @param < V > the value type
 * @return a lens that focuses on the keys of a map
 */
public static <K,V>Lens.Simple<Map<K,V>,Set<K>> keys(){
  return simpleLens(m -> new HashSet<>(m.keySet()),(m,ks) -> {
    HashSet<K> ksCopy=new HashSet<>(ks);
    Map<K,V> updated=new HashMap<>(m);
    Set<K> keys=updated.keySet();
    keys.retainAll(ksCopy);
    ksCopy.removeAll(keys);
    ksCopy.forEach(k -> updated.put(k,null));
    return updated;
  }
);
}
