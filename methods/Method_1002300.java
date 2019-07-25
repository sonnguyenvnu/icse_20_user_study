/** 
 * Atomically do a  {@link #remove(long)} if-and-only-if the key is mappedto a value which is <code>equals</code> to the given value.
 * @throws NullPointerException if the specified value is null 
 */
public boolean remove(long key,Object val){
  return putIfMatch(key,TOMBSTONE,val) == val;
}
