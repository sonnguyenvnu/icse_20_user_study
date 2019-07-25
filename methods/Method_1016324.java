private static void add(JSONObject json,WebMapping field,double value){
  json.put(field.getMapping().name(),value);
}
