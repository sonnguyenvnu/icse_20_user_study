/** 
 * @return the entry whose key is either equal to {@code key}, or just below it. If  {@code key} is less than theminimum value in the map, returns  {@code null}.
 */
public IEntry<Long,V> floor(long key){
  if (key < 0) {
    return neg.floor(key);
  }
 else {
    IEntry<Long,V> entry=pos.floor(key);
    if (entry != null) {
      return entry;
    }
 else {
      return neg.size() > 0 ? neg.nth(neg.size() - 1) : null;
    }
  }
}
