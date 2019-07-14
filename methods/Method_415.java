@SuppressWarnings({"rawtypes","unchecked"}) Set<?> evalKeySet(Object currentObject){
  if (currentObject == null) {
    return null;
  }
  if (currentObject instanceof Map) {
    return ((Map)currentObject).keySet();
  }
  if (currentObject instanceof Collection || currentObject instanceof Object[] || currentObject.getClass().isArray()) {
    return null;
  }
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentObject.getClass());
  if (beanSerializer == null) {
    return null;
  }
  try {
    return beanSerializer.getFieldNames(currentObject);
  }
 catch (  Exception e) {
    throw new JSONPathException("evalKeySet error : " + path,e);
  }
}
