/** 
 * Format this XML data as a String.
 * @webref xml:method
 * @brief Formats XML data as a String
 * @param indent -1 for a single line (and no declaration), >= 0 for indents and newlines
 * @return the content
 * @see XML#toString()
 */
public String format(int indent){
  try {
    boolean useIndentAmount=false;
    TransformerFactory factory=TransformerFactory.newInstance();
    if (indent != -1) {
      try {
        factory.setAttribute("indent-number",indent);
      }
 catch (      IllegalArgumentException e) {
        useIndentAmount=true;
      }
    }
    Transformer transformer=factory.newTransformer();
    if (indent == -1 || parent == null) {
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
    }
 else {
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
    }
    transformer.setOutputProperty(OutputKeys.METHOD,"xml");
    if (useIndentAmount) {
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",String.valueOf(indent));
    }
    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    final String decl="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    final String sep=System.getProperty("line.separator");
    StringWriter tempWriter=new StringWriter();
    StreamResult tempResult=new StreamResult(tempWriter);
    transformer.transform(new DOMSource(node),tempResult);
    String[] tempLines=PApplet.split(tempWriter.toString(),sep);
    if (tempLines[0].startsWith("<?xml")) {
      int declEnd=tempLines[0].indexOf("?>") + 2;
      if (tempLines[0].length() == declEnd) {
        tempLines=PApplet.subset(tempLines,1);
      }
 else {
        tempLines[0]=tempLines[0].substring(declEnd);
      }
    }
    String singleLine=PApplet.join(PApplet.trim(tempLines),"");
    if (indent == -1) {
      return singleLine;
    }
    if (singleLine.trim().length() == 0) {
      return decl + sep + singleLine;
    }
    StringWriter stringWriter=new StringWriter();
    StreamResult xmlOutput=new StreamResult(stringWriter);
    Source source=new StreamSource(new StringReader(singleLine));
    transformer.transform(source,xmlOutput);
    String outgoing=stringWriter.toString();
    if (outgoing.startsWith(decl)) {
      int declen=decl.length();
      int seplen=sep.length();
      if (outgoing.length() > declen + seplen && !outgoing.substring(declen,declen + seplen).equals(sep)) {
        return outgoing.substring(0,decl.length()) + sep + outgoing.substring(decl.length());
      }
      return outgoing;
    }
 else {
      return decl + sep + outgoing;
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
