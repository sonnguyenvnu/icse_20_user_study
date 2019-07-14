private void doConvert(String key,Object value){
  if (value == null) {
    return;
  }
  if (value instanceof Class) {
    return;
  }
  Class type=org.springframework.util.ClassUtils.getUserClass(value);
  if (basicClass.contains(type) || value instanceof Number || value instanceof Enum) {
    parameter.put(getParameterKey(key),convertValue(value));
    return;
  }
  if (value instanceof Object[]) {
    value=Arrays.asList(((Object[])value));
  }
  if (value instanceof Collection) {
    Collection coll=((Collection)value);
    int count=0;
    for (    Object o : coll) {
      doConvert(key + "[" + count++ + "]",o);
    }
  }
 else {
    HttpParameterConverter converter=new HttpParameterConverter(value);
    converter.setPrefix(getParameterKey(key).concat("."));
    parameter.putAll(converter.convert());
  }
}
