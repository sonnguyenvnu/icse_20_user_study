/** 
 * Xml?Map
 * @param resultStr ??????
 * @return
 */
public final static Map<String,Object> xmlToMap(final String resultStr){
  if (resultStr == null || StringUtil.isEmpty(resultStr)) {
    logger.error("?????--????XML?????");
    return null;
  }
  try {
    Map<String,Object> resultMap=new HashMap<>();
    Document doc=DocumentHelper.parseText(resultStr);
    List<Element> list=doc.getRootElement().elements();
    for (    Element element : list) {
      resultMap.put(element.getName(),element.getText());
    }
    return resultMap;
  }
 catch (  DocumentException e) {
    logger.error("?????--??XML???{}",e);
    return null;
  }
}
