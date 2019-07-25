@NotNull private static Map<String,String> collect(Document document){
  Map<String,String> parameterMap=new ConcurrentHashMap<>();
  Object nodeList;
  try {
    XPath xpath=XPathFactory.newInstance().newXPath();
    XPathExpression xPathExpr=xpath.compile("/container/parameters/parameter[@key]");
    nodeList=xPathExpr.evaluate(document,XPathConstants.NODESET);
  }
 catch (  XPathExpressionException e) {
    return Collections.emptyMap();
  }
  if (!(nodeList instanceof NodeList)) {
    return Collections.emptyMap();
  }
  for (int i=0; i < ((NodeList)nodeList).getLength(); i++) {
    Element node=(Element)((NodeList)nodeList).item(i);
    String parameterValue=node.hasAttribute("type") && node.getAttribute("type").equals("collection") ? "collection" : node.getTextContent();
    parameterMap.put(node.getAttribute("key"),parameterValue);
  }
  return parameterMap;
}
