protected void deepSet(final Object currentObject,final String propertyName,long propertyNameHash,Object value){
  if (currentObject == null) {
    return;
  }
  if (currentObject instanceof Map) {
    Map map=(Map)currentObject;
    if (map.containsKey(propertyName)) {
      Object val=map.get(propertyName);
      map.put(propertyName,value);
      return;
    }
    for (    Object val : map.values()) {
      deepSet(val,propertyName,propertyNameHash,value);
    }
    return;
  }
  final Class<?> currentClass=currentObject.getClass();
  JavaBeanDeserializer beanDeserializer=getJavaBeanDeserializer(currentClass);
  if (beanDeserializer != null) {
    try {
      FieldDeserializer fieldDeser=beanDeserializer.getFieldDeserializer(propertyName);
      if (fieldDeser != null) {
        fieldDeser.setValue(currentObject,value);
        return;
      }
      JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentClass);
      List<Object> fieldValues=beanSerializer.getObjectFieldValues(currentObject);
      for (      Object val : fieldValues) {
        deepSet(val,propertyName,propertyNameHash,value);
      }
      return;
    }
 catch (    Exception e) {
      throw new JSONPathException("jsonpath error, path " + path + ", segement " + propertyName,e);
    }
  }
  if (currentObject instanceof List) {
    List list=(List)currentObject;
    for (int i=0; i < list.size(); ++i) {
      Object val=list.get(i);
      deepSet(val,propertyName,propertyNameHash,value);
    }
    return;
  }
}
