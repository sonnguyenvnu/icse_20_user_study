private static Map<String,String> flatten(Properties properties){
  Map<String,String> flat=new LinkedHashMap<>();
  for (  String key : properties.stringPropertyNames()) {
    flat.put(key,properties.getProperty(key));
  }
  return flat;
}
