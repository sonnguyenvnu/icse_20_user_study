/** 
 * Relates an XPath query to a node by adding the query to the  {@link #nodeNameToXPaths}.
 * @param xPath    the query to do over a node
 * @param nodeName the node on which to do the query
 */
private void addQueryToNode(final XPath xPath,final String nodeName){
  List<XPath> xPathsForNode=nodeNameToXPaths.get(nodeName);
  if (xPathsForNode == null) {
    xPathsForNode=new ArrayList<>();
    nodeNameToXPaths.put(nodeName,xPathsForNode);
  }
  xPathsForNode.add(xPath);
}
