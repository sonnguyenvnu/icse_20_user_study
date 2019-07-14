/** 
 * Offloaded version of get() to look up null keys. Null keys map to index 0. This null case is split out into separate methods for the sake of performance in the two most commonly used operations (get and put), but incorporated with conditionals in others.
 */
private V getForNullKey(){
  for (Entry<K,V> e=table[0]; e != null; e=e.next) {
    if (e.key == null)     return e.value;
  }
  return null;
}
