/** 
 * Returns pretty print of the specified xml string.
 * @param xml the specified xml string
 * @return the pretty print of the specified xml string
 */
public static String format(final String xml){
  try {
    final DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
    final Document doc=db.parse(new InputSource(new StringReader(xml)));
    final Transformer transformer=TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
    final StreamResult result=new StreamResult(new StringWriter());
    final DOMSource source=new DOMSource(doc);
    transformer.transform(source,result);
    return result.getWriter().toString();
  }
 catch (  final Exception e) {
    LOGGER.log(Level.WARN,"Formats pretty XML failed: " + e.getMessage());
    return xml;
  }
}
