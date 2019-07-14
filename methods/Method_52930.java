private String unexpandEntities(Node n,String te,boolean withQuotes){
  String result=te;
  DocumentType doctype=n.getOwnerDocument().getDoctype();
  result=result.replaceAll(Matcher.quoteReplacement("&"),"&amp;");
  result=result.replaceAll(Matcher.quoteReplacement("<"),"&lt;");
  result=result.replaceAll(Matcher.quoteReplacement(">"),"&gt;");
  if (withQuotes) {
    result=result.replaceAll(Matcher.quoteReplacement("\""),"&quot;");
    result=result.replaceAll(Matcher.quoteReplacement("'"),"&apos;");
  }
  if (doctype != null) {
    NamedNodeMap entities=doctype.getEntities();
    String internalSubset=doctype.getInternalSubset();
    if (internalSubset == null) {
      internalSubset="";
    }
    for (int i=0; i < entities.getLength(); i++) {
      Node item=entities.item(i);
      String entityName=item.getNodeName();
      Node firstChild=item.getFirstChild();
      if (firstChild != null) {
        result=result.replaceAll(Matcher.quoteReplacement(firstChild.getNodeValue()),"&" + entityName + ";");
      }
 else {
        Matcher m=Pattern.compile(Matcher.quoteReplacement("<!ENTITY " + entityName + " ") + "[']([^']*)[']>").matcher(internalSubset);
        if (m.find()) {
          result=result.replaceAll(Matcher.quoteReplacement(m.group(1)),"&" + entityName + ";");
        }
      }
    }
  }
  return result;
}
