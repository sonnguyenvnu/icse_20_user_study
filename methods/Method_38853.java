/** 
 * Removes last child node if contains just empty text.
 */
protected void removeLastChildNodeIfEmptyText(final Node parentNode,final boolean closedTag){
  if (parentNode == null) {
    return;
  }
  Node lastChild=parentNode.getLastChild();
  if (lastChild == null) {
    return;
  }
  if (lastChild.getNodeType() != Node.NodeType.TEXT) {
    return;
  }
  if (closedTag) {
    if (parentNode.getChildNodesCount() == 1) {
      return;
    }
  }
  Text text=(Text)lastChild;
  if (text.isBlank()) {
    lastChild.detachFromParent();
  }
}
