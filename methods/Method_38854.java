/** 
 * Finds matching parent open tag or <code>null</code> if not found.
 */
protected Node findMatchingParentOpenTag(String tagName){
  Node parent=parentNode;
  if (!rootNode.config.isCaseSensitive()) {
    tagName=tagName.toLowerCase();
  }
  while (parent != null) {
    String parentNodeName=parent.getNodeName();
    if (parentNodeName != null) {
      if (!rootNode.config.isCaseSensitive()) {
        parentNodeName=parentNodeName.toLowerCase();
      }
    }
    if (tagName.equals(parentNodeName)) {
      return parent;
    }
    parent=parent.getParentNode();
  }
  return null;
}
