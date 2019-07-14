/** 
 * Returns an unformatted xml string (without the declaration)
 * @throws TransformerException if the XML cannot be converted to a string
 */
private static String getXmlString(Node node) throws TransformerException {
  StringWriter writer=new StringWriter();
  Source source=new DOMSource(node.getAsDocument());
  Result result=new StreamResult(writer);
  TransformerFactory transformerFactory=TransformerFactory.newInstance();
  Transformer xformer=transformerFactory.newTransformer();
  xformer.setOutputProperty(OutputKeys.INDENT,"yes");
  xformer.transform(source,result);
  return writer.toString();
}
