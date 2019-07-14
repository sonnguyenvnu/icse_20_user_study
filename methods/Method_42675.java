/** 
 * ??????????XML?
 * @param inputStream
 * @return
 * @throws Exception
 */
@SuppressWarnings("unchecked") public static Map<String,String> parseXml(InputStream inputStream) throws Exception {
  if (inputStream == null) {
    return null;
  }
  Map<String,String> map=new HashMap<String,String>();
  SAXReader reader=new SAXReader();
  Document document=reader.read(inputStream);
  Element root=document.getRootElement();
  List<Element> elementList=root.elements();
  for (  Element e : elementList) {
    map.put(e.getName(),e.getText());
  }
  inputStream.close();
  inputStream=null;
  return map;
}
