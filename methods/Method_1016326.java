private static void add(JSONObject json,WebMapping field,String[] values){
  JSONArray a=new JSONArray();
  for (  String s : values)   a.put(s);
  json.put(field.getMapping().name(),a);
}
