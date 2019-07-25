/** 
 * Shrinks the array to half of its current size if the current number of elements fit into half of the capacity.
 * @return true if the compaction succeeded, else false (e.g. when the current elements would not fit)
 */
@GuardedBy("lock") protected boolean _compact(){
  int new_cap=buffer.length >> 1;
  boolean compactable=this.buffer.length > 0 && high - low <= new_cap;
  if (!compactable)   return false;
  _copy(new_cap);
  return true;
}
