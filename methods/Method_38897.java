/** 
 * Returns this node's previous sibling of <b>element</b> type or <code>null</code> if this is the first sibling.
 */
public Node getPreviousSiblingElement(){
  parentNode.initChildElementNodes();
  if (siblingElementIndex == -1) {
    for (int i=siblingIndex - 1; i >= 0; i--) {
      Node sibling=parentNode.childNodes.get(i);
      if (sibling.getNodeType() == NodeType.ELEMENT) {
        return sibling;
      }
    }
    return null;
  }
  int index=siblingElementIndex - 1;
  if (index < 0) {
    return null;
  }
  return parentNode.childElementNodes[index];
}
