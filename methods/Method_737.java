/** 
 * Special version of remove for EntrySet.
 */
final Entry<K,V> removeMapping(Object o){
  if (!(o instanceof Map.Entry))   return null;
  Map.Entry<K,V> entry=(Map.Entry<K,V>)o;
  Object key=entry.getKey();
  int hash=(key == null) ? 0 : (key instanceof String) ? hash(hashString((String)key)) : hash(key.hashCode());
  int i=indexFor(hash,table.length);
  Entry<K,V> prev=table[i];
  Entry<K,V> e=prev;
  while (e != null) {
    Entry<K,V> next=e.next;
    if (e.hash == hash && e.equals(entry)) {
      modCount++;
      size--;
      if (prev == e)       table[i]=next;
 else       prev.next=next;
      return e;
    }
    prev=e;
    e=next;
  }
  return e;
}
