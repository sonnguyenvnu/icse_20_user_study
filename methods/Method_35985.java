private NodeList findXmlNodesMatching(String value){
  if (value == null || !value.trim().startsWith("<")) {
    notifier().info(String.format("Warning: failed to parse the XML document\nXML: %s",value));
    return null;
  }
  try {
    DocumentBuilder documentBuilder=Xml.newDocumentBuilderFactory().newDocumentBuilder();
    documentBuilder.setErrorHandler(new SilentErrorHandler());
    Document inDocument=XMLUnit.buildDocument(documentBuilder,new StringReader(value));
    XpathEngine simpleXpathEngine=XMLUnit.newXpathEngine();
    if (xpathNamespaces != null) {
      NamespaceContext namespaceContext=new SimpleNamespaceContext(xpathNamespaces);
      simpleXpathEngine.setNamespaceContext(namespaceContext);
    }
    return simpleXpathEngine.getMatchingNodes(expectedValue,inDocument);
  }
 catch (  SAXException e) {
    notifier().info(String.format("Warning: failed to parse the XML document. Reason: %s\nXML: %s",e.getMessage(),value));
    return null;
  }
catch (  IOException e) {
    notifier().info(e.getMessage());
    return null;
  }
catch (  XpathException e) {
    notifier().info("Warning: failed to evaluate the XPath expression " + expectedValue);
    return null;
  }
catch (  Exception e) {
    return throwUnchecked(e,NodeList.class);
  }
}
