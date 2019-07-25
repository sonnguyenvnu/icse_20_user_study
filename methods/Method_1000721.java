/** 
 * ???????? DocumentBuilder??? XML ???
 * @return ?? DocumentBuilder ??
 * @throws ParserConfigurationException
 */
public static DocumentBuilder xmls() throws ParserConfigurationException {
  DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
  String FEATURE=null;
  FEATURE="http://apache.org/xml/features/disallow-doctype-decl";
  dbf.setFeature(FEATURE,true);
  FEATURE="http://xml.org/sax/features/external-general-entities";
  dbf.setFeature(FEATURE,false);
  FEATURE="http://xml.org/sax/features/external-parameter-entities";
  dbf.setFeature(FEATURE,false);
  FEATURE="http://apache.org/xml/features/nonvalidating/load-external-dtd";
  dbf.setFeature(FEATURE,false);
  dbf.setXIncludeAware(false);
  dbf.setExpandEntityReferences(false);
  return dbf.newDocumentBuilder();
}
