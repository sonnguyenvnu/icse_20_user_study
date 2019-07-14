/** 
 * ?xml??
 * @param paramMap
 * @return
 */
private static String mapToXml(SortedMap<String,Object> paramMap){
  StringBuilder dataBuilder=new StringBuilder("<xml>");
  for (  Map.Entry<String,Object> entry : paramMap.entrySet()) {
    if (!StringUtil.isEmpty(entry.getValue())) {
      dataBuilder.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
    }
  }
  dataBuilder.append("</xml>");
  logger.info("Map?Xml??:{}",dataBuilder.toString());
  return dataBuilder.toString();
}
