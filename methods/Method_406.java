protected void deepGetPropertyValues(final Object currentObject,List<Object> outValues){
  final Class<?> currentClass=currentObject.getClass();
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentClass);
  Collection collection=null;
  if (beanSerializer != null) {
    try {
      collection=beanSerializer.getFieldValues(currentObject);
    }
 catch (    Exception e) {
      throw new JSONPathException("jsonpath error, path " + path,e);
    }
  }
 else   if (currentObject instanceof Map) {
    Map map=(Map)currentObject;
    collection=map.values();
  }
 else   if (currentObject instanceof Collection) {
    collection=(Collection)currentObject;
  }
  if (collection != null) {
    for (    Object fieldValue : collection) {
      if (fieldValue == null || ParserConfig.isPrimitive2(fieldValue.getClass())) {
        outValues.add(fieldValue);
      }
 else {
        deepGetPropertyValues(fieldValue,outValues);
      }
    }
    return;
  }
  throw new UnsupportedOperationException(currentClass.getName());
}
