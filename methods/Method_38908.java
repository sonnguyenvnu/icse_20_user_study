/** 
 * Selects single node for single selector and appends it to the results.
 */
protected void selectAndAdd(final Node node,final CssSelector cssSelector,final List<Node> result){
  if (node.getNodeType() != Node.NodeType.ELEMENT) {
    return;
  }
  boolean matched=cssSelector.accept(node);
  if (matched) {
    if (result.contains(node)) {
      return;
    }
    result.add(node);
  }
}
