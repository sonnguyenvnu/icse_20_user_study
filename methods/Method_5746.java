/** 
 * Returns an existing or new id assigned to the given key. 
 */
public int assignIdForKey(String key){
  return getOrAdd(key).id;
}
