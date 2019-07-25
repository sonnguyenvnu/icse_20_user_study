public static String listtojson(String[] fields,int total,List list) throws Exception {
  Object[] values=new Object[fields.length];
  String jsonTemp="{\"total\":" + total + ",\"rows\":[";
  for (int j=0; j < list.size(); j++) {
    jsonTemp=jsonTemp + "{\"state\":\"closed\",";
    for (int i=0; i < fields.length; i++) {
      String fieldName=fields[i].toString();
      values[i]=org.jeecgframework.tag.core.easyui.TagUtil.fieldNametoValues(fieldName,list.get(j));
      jsonTemp=jsonTemp + "\"" + fieldName + "\"" + ":\"" + values[i] + "\"";
      if (i != fields.length - 1) {
        jsonTemp=jsonTemp + ",";
      }
    }
    if (j != list.size() - 1) {
      jsonTemp=jsonTemp + "},";
    }
 else {
      jsonTemp=jsonTemp + "}";
    }
  }
  jsonTemp=jsonTemp + "]}";
  return jsonTemp;
}
