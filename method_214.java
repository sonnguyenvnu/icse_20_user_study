/** 
 * Get whether use bytes to throttle garbage collector compaction or not.
 * @return true  - use Bytes,false - use Entries.
 */
public boolean _XXXXX_(){
  return getBoolean(IS_THROTTLE_BY_BYTES,false);
}