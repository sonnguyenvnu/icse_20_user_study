private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
  final DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
  try {
    dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
    dbf.setFeature("http://xml.org/sax/features/external-general-entities",false);
    dbf.setFeature("http://xml.org/sax/features/external-parameter-entities",false);
    dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
    dbf.setXIncludeAware(false);
    dbf.setExpandEntityReferences(false);
  }
 catch (  final ParserConfigurationException e) {
    LOG.log(Level.WARNING,"Ignored unsupported XML Parser Feature for parsing rulesets",e);
  }
  return dbf.newDocumentBuilder();
}
