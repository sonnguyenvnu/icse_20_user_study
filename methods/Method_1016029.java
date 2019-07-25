/** 
 * iterator implements the Iterable interface
 */
@Override public final Iterator<Map.Entry<K,V>> iterator(){
  return entrySet().iterator();
}
