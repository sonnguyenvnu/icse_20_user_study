private void init() throws XPathExpressionException {
  XPathEvaluator xPathEvaluator=new XPathEvaluator();
  xPathEvaluator.setNamespaceContext(XPath2NamespaceContext.INSTANCE);
  xPathExpression=xPathEvaluator.compile(xpathStr);
}
