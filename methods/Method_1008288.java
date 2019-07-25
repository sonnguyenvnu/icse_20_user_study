/** 
 * The relative time used to track time-based evictions.
 * @return the current relative time
 */
protected long now(){
  return entriesExpireAfterAccess || entriesExpireAfterWrite ? System.nanoTime() : 0;
}
