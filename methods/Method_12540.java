@SuppressWarnings("unchecked") private static Map<String,Object> convertEnv(Map<String,Object> body){
  Map<String,Object> converted=new LinkedHashMap<>();
  List<Map<String,Object>> propertySources=new ArrayList<>(body.size());
  body.forEach((key,value) -> {
    if ("profiles".equals(key)) {
      converted.put("activeProfiles",value);
    }
 else     if (value instanceof Map) {
      Map<String,Object> values=(Map<String,Object>)value;
      Map<String,Object> properties=new LinkedHashMap<>();
      values.forEach((propKey,propValue) -> properties.put(propKey,singletonMap("value",propValue)));
      Map<String,Object> propertySource=new LinkedHashMap<>();
      propertySource.put("name",key);
      propertySource.put("properties",properties);
      propertySources.add(propertySource);
    }
  }
);
  converted.put("propertySources",propertySources);
  return converted;
}
