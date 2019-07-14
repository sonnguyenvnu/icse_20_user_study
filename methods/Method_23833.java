/** 
 * Removes whitespace nodes. Those whitespace nodes are required to reconstruct the original XML's spacing and indentation. If you call this and use saveXML() your original spacing will be gone.
 * @nowebref
 * @brief Removes whitespace nodes
 */
public void trim(){
  try {
    XPathFactory xpathFactory=XPathFactory.newInstance();
    XPathExpression xpathExp=xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");
    NodeList emptyTextNodes=(NodeList)xpathExp.evaluate(node,XPathConstants.NODESET);
    for (int i=0; i < emptyTextNodes.getLength(); i++) {
      Node emptyTextNode=emptyTextNodes.item(i);
      emptyTextNode.getParentNode().removeChild(emptyTextNode);
    }
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
