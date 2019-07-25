private static void add(JSONObject json,WebMapping field,List<?> values){
  JSONArray a=new JSONArray();
  for (  Object s : values) {
    if (s instanceof Date)     a.put(DateParser.iso8601MillisFormat.format((Date)s));
 else     a.put(s);
  }
  json.put(field.getMapping().name(),a);
}
