/** 
 * Determines if the given key value exists in this tree map.
 * @param k The key value to look for in this tree map.
 * @return <code>true</code> if this tree map contains the given key, <code>false</code> otherwise.
 */
public boolean contains(final K k){
  return tree.member(p(k,Option.none()));
}
