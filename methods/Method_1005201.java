/** 
 * ??LIST????EASYUI???JSON??
 * @param fields
 * @param total
 * @param list
 * @param dataStyle 
 * @param page 
 */
private static String listtojson(String[] fields,int total,List<?> list,String[] footers,String dataStyle,int pageSize) throws Exception {
  StringBuffer jsonTemp=new StringBuffer();
  if ("jqgrid".equals(dataStyle)) {
    int totalPage=total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
    if (totalPage == 0)     totalPage=1;
    jsonTemp.append("{\"total\":" + totalPage);
  }
 else {
    jsonTemp.append("{\"total\":" + total);
  }
  jsonTemp.append(",\"rows\":[");
  int i;
  String fieldName;
  if (list == null) {
    list=new ArrayList();
  }
  for (int j=0; j < list.size(); ++j) {
    jsonTemp.append("{\"state\":\"closed\",");
    Object fieldValue=null;
    for (i=0; i < fields.length; ++i) {
      fieldName=fields[i].toString();
      if (list.get(j) instanceof Map)       fieldValue=((Map<?,?>)list.get(j)).get(fieldName);
 else {
        fieldValue=fieldNametoValues(fieldName,list.get(j));
      }
      jsonTemp.append("\"" + fieldName + "\"" + ":\"" + oConvertUtils.escapeJava(fieldValue) + "\"");
      if (i != fields.length - 1) {
        jsonTemp.append(",");
      }
    }
    if (j != list.size() - 1)     jsonTemp.append("},");
 else {
      jsonTemp.append("}");
    }
  }
  jsonTemp.append("]");
  if (footers != null && footers.length > 0) {
    jsonTemp.append(",");
    jsonTemp.append("\"footer\":[");
    jsonTemp.append("{");
    for (i=0; i < footers.length; i++) {
      String footerFiled=footers[i].split(":")[0];
      Object value=null;
      if (footers[i].split(":").length == 2)       value=footers[i].split(":")[1];
 else {
        value=getTotalValue(footerFiled,list);
      }
      if (i == 0) {
        jsonTemp.append("\"" + footerFiled + "\":\"" + value + "\"");
      }
 else {
        jsonTemp.append(",\"" + footerFiled + "\":\"" + value + "\"");
      }
    }
    jsonTemp.append("}");
    jsonTemp.append("]");
  }
  jsonTemp.append("}");
  return jsonTemp.toString();
}
