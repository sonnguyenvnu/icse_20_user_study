/** 
 * Returns this node's next <b>element</b>.
 */
public Node getNextSiblingElement(){
  parentNode.initChildElementNodes();
  if (siblingElementIndex == -1) {
    int max=parentNode.getChildNodesCount();
    for (int i=siblingIndex; i < max; i++) {
      Node sibling=parentNode.childNodes.get(i);
      if (sibling.getNodeType() == NodeType.ELEMENT) {
        return sibling;
      }
    }
    return null;
  }
  int index=siblingElementIndex + 1;
  if (index >= parentNode.childElementNodesCount) {
    return null;
  }
  return parentNode.childElementNodes[index];
}
