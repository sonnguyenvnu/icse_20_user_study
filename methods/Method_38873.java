/** 
 * Inserts node after provided node.
 */
public void insertAfter(final Node newChild,final Node refChild){
  int siblingIndex=refChild.getSiblingIndex() + 1;
  if (siblingIndex == refChild.parentNode.getChildNodesCount()) {
    refChild.parentNode.addChild(newChild);
  }
 else {
    refChild.parentNode.insertChild(newChild,siblingIndex);
  }
}
