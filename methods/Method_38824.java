protected Map<String,String> createPropertiesMap(final String attrValue,final char propertiesDelimiter,final char valueDelimiter){
  if (attrValue == null) {
    return new LinkedHashMap<>();
  }
  String[] properties=StringUtil.splitc(attrValue,propertiesDelimiter);
  LinkedHashMap<String,String> map=new LinkedHashMap<>(properties.length);
  for (  String property : properties) {
    int valueDelimiterIndex=property.indexOf(valueDelimiter);
    if (valueDelimiterIndex != -1) {
      String propertyName=property.substring(0,valueDelimiterIndex).trim();
      String propertyValue=property.substring(valueDelimiterIndex + 1).trim();
      map.put(propertyName,propertyValue);
    }
  }
  return map;
}
