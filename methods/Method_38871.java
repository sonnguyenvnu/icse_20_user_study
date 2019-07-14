/** 
 * Inserts node before provided node.
 */
public void insertBefore(final Node newChild,final Node refChild){
  int siblingIndex=refChild.getSiblingIndex();
  refChild.parentNode.insertChild(newChild,siblingIndex);
}
