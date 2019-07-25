private static void add(JSONObject json,WebMapping field,Date[] values){
  JSONArray a=new JSONArray();
  for (  Date s : values)   a.put(DateParser.iso8601MillisFormat.format(s));
  json.put(field.getMapping().name(),a);
}
