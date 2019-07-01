private Properties _XXXXX_(Map<String,String> map){
  Properties properties=new Properties();
  if (map == null || map.isEmpty()) {
    return properties;
  }
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    properties.put(entry.getKey(),entry.getValue());
  }
  return properties;
}