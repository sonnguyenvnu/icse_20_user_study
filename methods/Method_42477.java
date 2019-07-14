/** 
 * Map?Xml
 * @param paramMap ?????
 * @return
 */
public final static String mapToXml(final Map<String,Object> paramMap){
  StringBuilder xmlBuilder=new StringBuilder();
  xmlBuilder.append("<xml>");
  for (  Map.Entry<String,Object> entry : paramMap.entrySet()) {
    xmlBuilder.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
  }
  xmlBuilder.append("</xml>");
  logger.info("?????--Map?XML??:{}",xmlBuilder.toString());
  return xmlBuilder.toString();
}
