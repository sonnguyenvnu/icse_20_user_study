/** 
 */
@SuppressWarnings("unchecked") public static Map<String,Object> xmlToMap(String xmlDoc) throws DocumentException {
  StringReader read=new StringReader(xmlDoc);
  InputSource source=new InputSource(read);
  SAXReader saxReader=new SAXReader();
  Map<String,Object> xmlMap=new HashMap<String,Object>();
  Document doc=saxReader.read(source);
  Element root=doc.getRootElement();
  List<Element> elements=root.elements();
  for (  Element et : elements) {
    xmlMap.put(et.getName(),et.getText());
  }
  return xmlMap;
}
