@SuppressWarnings("unchecked") default <T>T getProperty(String propertyName,T defaultValue){
  Map<String,Object> map=getProperties();
  if (map == null) {
    return null;
  }
  return (T)map.getOrDefault(propertyName,defaultValue);
}
