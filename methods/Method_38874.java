/** 
 * Inserts several child nodes after referent node.
 */
public void insertAfter(final Node[] newChilds,final Node refChild){
  if (newChilds.length == 0) {
    return;
  }
  int siblingIndex=refChild.getSiblingIndex() + 1;
  if (siblingIndex == refChild.parentNode.getChildNodesCount()) {
    refChild.parentNode.addChild(newChilds);
  }
 else {
    refChild.parentNode.insertChild(newChilds,siblingIndex);
  }
}
