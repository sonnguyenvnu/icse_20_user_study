/** 
 * ??LIST????DATATABLE???JSON?? ?DataTable?
 * @param fields
 * @param total
 * @param list
 */
private static String datatable(String field,int total,List list) throws Exception {
  String[] fields=field.split(",");
  Object[] values=new Object[fields.length];
  StringBuffer jsonTemp=new StringBuffer();
  jsonTemp.append("{\"iTotalDisplayRecords\":" + total + ",\"iTotalRecords\":" + total + ",\"aaData\":[");
  for (int j=0; j < list.size(); j++) {
    jsonTemp.append("{");
    for (int i=0; i < fields.length; i++) {
      String fieldName=fields[i].toString();
      values[i]=fieldNametoValues(fieldName,list.get(j));
      jsonTemp.append("\"" + fieldName + "\"" + ":\"" + oConvertUtils.escapeJava(values[i]) + "\"");
      if (i != fields.length - 1) {
        jsonTemp.append(",");
      }
    }
    if (j != list.size() - 1) {
      jsonTemp.append("},");
    }
 else {
      jsonTemp.append("}");
    }
  }
  jsonTemp.append("]}");
  return jsonTemp.toString();
}
