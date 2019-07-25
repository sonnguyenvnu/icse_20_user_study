public Settings merge(Properties properties){
  if (properties == null || properties.isEmpty()) {
    return this;
  }
  Enumeration<?> propertyNames=properties.propertyNames();
  Object prop=null;
  for (; propertyNames.hasMoreElements(); ) {
    prop=propertyNames.nextElement();
    if (prop instanceof String) {
      Object value=properties.get(prop);
      setProperty((String)prop,value.toString());
    }
  }
  return this;
}
