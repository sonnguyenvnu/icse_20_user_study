/** 
 * Returns <tt>true</tt> if this scope contains the specified element. Invariant: contains(node) == getAvailableElements(null).contains(node)
 * @param node element to check presence for
 * @return <tt>true</tt> if this scope contains the specified element
 */
public boolean contains(SNode node){
  return Sequence.fromIterable(getAvailableElements(null)).contains(node);
}
