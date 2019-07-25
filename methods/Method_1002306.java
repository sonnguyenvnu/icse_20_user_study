/** 
 * Atomically do a  {@link #remove(Object)} if-and-only-if the key is mappedto a value which is <code>equals</code> to the given value.
 * @throws NullPointerException if the specified key or value is null 
 */
public boolean remove(Object key,Object val){
  return putIfMatch(key,TOMBSTONE,val) == val;
}
