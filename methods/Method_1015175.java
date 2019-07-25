/** 
 * @param entries an iterator of {@code IEntry} objects
 * @return a {@code LinearMap} representing the entries remaining in the iterator
 */
public static <K,V>LinearMap<K,V> from(Iterator<IEntry<K,V>> entries){
  LinearMap<K,V> m=new LinearMap<>();
  entries.forEachRemaining(e -> m.put(e.key(),e.value()));
  return m;
}
