public static List<Node> xpath(Node node,String xpathExpression,NamespaceContext nsContext){
  if (log.isDebugEnabled()) {
    log.debug(w3CDomNodeToString(node));
  }
  XPath xpath=XPathFactoryUtil.newXPath();
  try {
    List<Node> result=new ArrayList<Node>();
    xpath.setNamespaceContext(nsContext);
    NodeList nl=(NodeList)xpath.evaluate(xpathExpression,node,XPathConstants.NODESET);
    if (log.isDebugEnabled()) {
      log.debug("evaluate returned " + nl.getLength());
    }
    if (nl.getLength() == 0) {
      log.info("no results for xpath " + xpathExpression);
    }
    for (int i=0; i < nl.getLength(); i++) {
      result.add(nl.item(i));
    }
    return result;
  }
 catch (  XPathExpressionException e) {
    throw new RuntimeException("Problem with '" + xpathExpression + "'",e);
  }
}
