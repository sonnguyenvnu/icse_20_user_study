/** 
 * Finds very first index of given element or negative value if element is not found.
 */
public int findFirst(final E o){
  return findFirst(o,0,getLastIndex());
}
