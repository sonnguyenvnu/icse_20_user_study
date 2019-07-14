/** 
 * Initializes siblings elements of the same name.
 */
protected void initSiblingNames(){
  if (siblingNameIndex == -1) {
    List<Node> siblings=parentNode.childNodes;
    int index=0;
    for (int i=0, siblingsSize=siblings.size(); i < siblingsSize; i++) {
      Node sibling=siblings.get(i);
      if (sibling.siblingNameIndex == -1 && nodeType == NodeType.ELEMENT && nodeName.equals(sibling.getNodeName())) {
        sibling.siblingNameIndex=index++;
      }
    }
  }
}
