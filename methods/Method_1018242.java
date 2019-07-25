/** 
 * Find the integer value associated with this key, or nothing if this key is not in the keep.
 * @param key An object.
 * @return An integer
 */
public int find(Object key){
  Object o=this.map.get(key);
  return o instanceof Integer ? ((Integer)o).intValue() : none;
}
