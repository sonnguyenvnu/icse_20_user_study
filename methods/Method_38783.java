/** 
 * Matches element to css selector. All non-element types are ignored.
 */
protected boolean matchElement(final Node node){
  if (node.getNodeType() != Node.NodeType.ELEMENT) {
    return false;
  }
  String element=getElement();
  String nodeName=node.getNodeName();
  return element.equals(StringPool.STAR) || element.equals(nodeName);
}
