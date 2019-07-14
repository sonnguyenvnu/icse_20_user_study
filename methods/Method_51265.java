/** 
 * Get the XPath queries associated with the node name. If there are none, the XPath queries for the  {@link #AST_ROOT}are obtained.
 * @param nodeName the id of the node
 * @return the list of XPath queries that match the node name
 */
private List<XPath> getXPathsForNodeOrDefault(final String nodeName){
  List<XPath> xPaths=nodeNameToXPaths.get(nodeName);
  if (xPaths == null) {
    xPaths=nodeNameToXPaths.get(AST_ROOT);
  }
  return xPaths;
}
