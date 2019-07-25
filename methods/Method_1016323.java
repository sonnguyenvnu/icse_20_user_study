private static void add(JSONObject json,WebMapping field,String value){
  json.put(field.getMapping().name(),value);
}
