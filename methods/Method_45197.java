private DocumentBuilder documentBuilder(){
  DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
  dbf.setNamespaceAware(true);
  dbf.setCoalescing(true);
  dbf.setIgnoringElementContentWhitespace(true);
  dbf.setIgnoringComments(true);
  try {
    return dbf.newDocumentBuilder();
  }
 catch (  ParserConfigurationException e) {
    throw new MocoException(e);
  }
}
