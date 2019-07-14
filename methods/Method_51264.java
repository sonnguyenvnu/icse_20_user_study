@Override public List<Node> evaluate(final Node node,final RuleContext data){
  final List<Node> results=new ArrayList<>();
  try {
    initializeExpressionIfStatusIsNoneOrPartial(data.getLanguageVersion().getLanguageVersionHandler().getXPathHandler().getNavigator());
    List<XPath> xPaths=getXPathsForNodeOrDefault(node.getXPathNodeName());
    for (    XPath xpath : xPaths) {
      @SuppressWarnings("unchecked") final List<Node> matchedNodes=xpath.selectNodes(node);
      results.addAll(matchedNodes);
    }
  }
 catch (  final JaxenException e) {
    throw new RuntimeException(e);
  }
  return results;
}
