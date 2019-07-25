/** 
 * @return the entry whose key is either equal to {@code key}, or just above it. If  {@code key} is greater than themaximum value in the map, returns  {@code null}.
 */
public IEntry<Long,V> ceil(long key){
  if (key >= 0) {
    return pos.ceil(key);
  }
 else {
    IEntry<Long,V> entry=neg.ceil(key);
    if (entry != null) {
      return entry;
    }
 else {
      return pos.size() > 0 ? pos.nth(0) : null;
    }
  }
}
