private static void add(JSONObject json,WebMapping field,boolean value){
  json.put(field.getMapping().name(),value);
}
