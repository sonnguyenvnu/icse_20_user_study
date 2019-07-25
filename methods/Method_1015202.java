/** 
 * @param entries an iterator of {@code IEntry} objects
 * @return a forked map containing the remaining entries
 */
public static <K,V>Map<K,V> from(Iterator<IEntry<K,V>> entries){
  Map<K,V> m=new Map<K,V>().linear();
  entries.forEachRemaining(e -> m.put(e.key(),e.value()));
  return m.forked();
}
