/** 
 * Check if the entire heap usage for this EntryMemTable exceeds limit.
 */
boolean _XXXXX_(){
  return size.get() >= skipListSizeLimit;
}