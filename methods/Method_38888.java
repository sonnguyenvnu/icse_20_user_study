/** 
 * Checks the health of child nodes. Useful during complex tree manipulation, to check if everything is OK. Not optimized for speed, should be used just for testing purposes.
 */
public boolean check(){
  if (childNodes == null) {
    return true;
  }
  int siblingElementIndex=0;
  for (int i=0, childNodesSize=childNodes.size(); i < childNodesSize; i++) {
    Node childNode=childNodes.get(i);
    if (childNode.siblingIndex != i) {
      return false;
    }
    if (childNode.getNodeType() == NodeType.ELEMENT) {
      if (childNode.siblingElementIndex != siblingElementIndex) {
        return false;
      }
      siblingElementIndex++;
    }
  }
  if (childElementNodesCount != siblingElementIndex) {
    return false;
  }
  if (childElementNodes != null) {
    if (childElementNodes.length != childElementNodesCount) {
      return false;
    }
    int childCount=getChildNodesCount();
    for (int i=0; i < childCount; i++) {
      Node child=getChild(i);
      if (child.siblingElementIndex >= 0) {
        if (childElementNodes[child.siblingElementIndex] != child) {
          return false;
        }
      }
    }
  }
  if (siblingNameIndex != -1) {
    List<Node> siblings=parentNode.childNodes;
    int index=0;
    for (int i=0, siblingsSize=siblings.size(); i < siblingsSize; i++) {
      Node sibling=siblings.get(i);
      if (sibling.siblingNameIndex == -1 && nodeType == NodeType.ELEMENT && nodeName.equals(sibling.getNodeName())) {
        if (sibling.siblingNameIndex != index++) {
          return false;
        }
      }
    }
  }
  for (  Node childNode : childNodes) {
    if (!childNode.check()) {
      return false;
    }
  }
  return true;
}
