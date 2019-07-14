@Override public String select(String text){
  try {
    HtmlCleaner htmlCleaner=new HtmlCleaner();
    TagNode tagNode=htmlCleaner.clean(text);
    Document document=new DomSerializer(new CleanerProperties()).createDOM(tagNode);
    Object result;
    try {
      result=xPathExpression.evaluate(document,XPathConstants.NODESET);
    }
 catch (    XPathExpressionException e) {
      result=xPathExpression.evaluate(document,XPathConstants.STRING);
    }
    if (result instanceof NodeList) {
      NodeList nodeList=(NodeList)result;
      if (nodeList.getLength() == 0) {
        return null;
      }
      Node item=nodeList.item(0);
      if (item.getNodeType() == Node.ATTRIBUTE_NODE || item.getNodeType() == Node.TEXT_NODE) {
        return item.getTextContent();
      }
 else {
        StreamResult xmlOutput=new StreamResult(new StringWriter());
        Transformer transformer=TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
        transformer.transform(new DOMSource(item),xmlOutput);
        return xmlOutput.getWriter().toString();
      }
    }
    return result.toString();
  }
 catch (  Exception e) {
    logger.error("select text error! " + xpathStr,e);
  }
  return null;
}
