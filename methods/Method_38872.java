/** 
 * Inserts several child nodes before provided node.
 */
public void insertBefore(final Node[] newChilds,final Node refChild){
  if (newChilds.length == 0) {
    return;
  }
  int siblingIndex=refChild.getSiblingIndex();
  refChild.parentNode.insertChild(newChilds,siblingIndex);
}
