@SuppressWarnings("unchecked") private Map<String,Object> toMap(Properties properties){
  Map<String,Object> result=new HashMap<>();
  Enumeration<String> keys=(Enumeration<String>)properties.propertyNames();
  while (keys.hasMoreElements()) {
    String key=keys.nextElement();
    Object value=properties.getProperty(key);
    if (value != null) {
      result.put(key,((String)value).trim());
    }
 else {
      result.put(key,null);
    }
  }
  return result;
}
