@SuppressWarnings({"rawtypes","unchecked"}) protected Collection<Object> getPropertyValues(final Object currentObject){
  final Class<?> currentClass=currentObject.getClass();
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentClass);
  if (beanSerializer != null) {
    try {
      return beanSerializer.getFieldValues(currentObject);
    }
 catch (    Exception e) {
      throw new JSONPathException("jsonpath error, path " + path,e);
    }
  }
  if (currentObject instanceof Map) {
    Map map=(Map)currentObject;
    return map.values();
  }
  if (currentObject instanceof Collection) {
    return (Collection)currentObject;
  }
  throw new UnsupportedOperationException();
}
