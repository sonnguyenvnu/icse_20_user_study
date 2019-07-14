public String asXmlString(Object contribution,String encoding,List<String> filters,boolean pretty) throws Exception {
  Document doc=asXml(contribution,filters);
  TransformerFactory tf=TransformerFactory.newInstance();
  Transformer transformer=tf.newTransformer();
  DOMSource source=new DOMSource(doc);
  if (encoding != null && encoding.length() > 0) {
    transformer.setOutputProperty(OutputKeys.ENCODING,encoding);
  }
  if (pretty) {
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
  }
 else {
    transformer.setOutputProperty(OutputKeys.INDENT,"no");
  }
  StringWriter sw=new StringWriter();
  StreamResult result=new StreamResult(sw);
  transformer.transform(source,result);
  return sw.toString();
}
