private int determineLocation(Node n,int index){
  int nextIndex=index;
  int nodeLength=0;
  int textLength=0;
  if (n.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
    nextIndex=xmlString.indexOf("<!DOCTYPE",nextIndex);
  }
 else   if (n.getNodeType() == Node.COMMENT_NODE) {
    nextIndex=xmlString.indexOf("<!--",nextIndex);
  }
 else   if (n.getNodeType() == Node.ELEMENT_NODE) {
    nextIndex=xmlString.indexOf("<" + n.getNodeName(),nextIndex);
    nodeLength=xmlString.indexOf(">",nextIndex) - nextIndex + 1;
  }
 else   if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
    nextIndex=xmlString.indexOf("<![CDATA[",nextIndex);
  }
 else   if (n.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
    ProcessingInstruction pi=(ProcessingInstruction)n;
    nextIndex=xmlString.indexOf("<?" + pi.getTarget(),nextIndex);
  }
 else   if (n.getNodeType() == Node.TEXT_NODE) {
    String te=unexpandEntities(n,n.getNodeValue(),true);
    int newIndex=xmlString.indexOf(te,nextIndex);
    if (newIndex == -1) {
      te=unexpandEntities(n,n.getNodeValue(),false);
      newIndex=xmlString.indexOf(te,nextIndex);
    }
    if (newIndex > 0) {
      textLength=te.length();
      nextIndex=newIndex;
    }
  }
 else   if (n.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
    nextIndex=xmlString.indexOf("&" + n.getNodeName() + ";",nextIndex);
  }
  setBeginLocation(n,nextIndex);
  nextIndex+=nodeLength;
  if (n.hasChildNodes()) {
    NodeList childs=n.getChildNodes();
    for (int i=0; i < childs.getLength(); i++) {
      nextIndex=determineLocation(childs.item(i),nextIndex);
    }
  }
  boolean isAutoClose=!n.hasChildNodes() && n.getNodeType() == Node.ELEMENT_NODE && xmlString.startsWith("/>",nextIndex - 2);
  if (n.getNodeType() == Node.ELEMENT_NODE && !isAutoClose) {
    nextIndex+=2 + n.getNodeName().length() + 1;
  }
 else   if (n.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
    Node nextSibling=n.getNextSibling();
    if (nextSibling.getNodeType() == Node.ELEMENT_NODE) {
      nextIndex=xmlString.indexOf("<" + nextSibling.getNodeName(),nextIndex) - 1;
    }
 else     if (nextSibling.getNodeType() == Node.COMMENT_NODE) {
      nextIndex=xmlString.indexOf("<!--",nextIndex);
    }
 else {
      nextIndex=xmlString.indexOf(">",nextIndex);
    }
  }
 else   if (n.getNodeType() == Node.COMMENT_NODE) {
    nextIndex+=4 + 3;
    nextIndex+=n.getNodeValue().length();
  }
 else   if (n.getNodeType() == Node.TEXT_NODE) {
    nextIndex+=textLength;
  }
 else   if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
    nextIndex+="<![CDATA[".length() + n.getNodeValue().length() + "]]>".length();
  }
 else   if (n.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
    ProcessingInstruction pi=(ProcessingInstruction)n;
    nextIndex+="<?".length() + pi.getTarget().length() + "?>".length() + pi.getData().length();
  }
  setEndLocation(n,nextIndex - 1);
  return nextIndex;
}
