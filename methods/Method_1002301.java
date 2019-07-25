/** 
 * Atomically do a <code>put(key,val)</code> if-and-only-if the key is mapped to some value already.
 * @throws NullPointerException if the specified value is null 
 */
public TypeV replace(long key,TypeV val){
  return putIfMatch(key,val,MATCH_ANY);
}
