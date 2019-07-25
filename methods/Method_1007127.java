/** 
 * Does the priority k exist already?
 */
public boolean contains(final K k){
  return !ftree.split(equal.eq(k))._2().isEmpty();
}
