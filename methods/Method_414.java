@SuppressWarnings("rawtypes") int evalSize(Object currentObject){
  if (currentObject == null) {
    return -1;
  }
  if (currentObject instanceof Collection) {
    return ((Collection)currentObject).size();
  }
  if (currentObject instanceof Object[]) {
    return ((Object[])currentObject).length;
  }
  if (currentObject.getClass().isArray()) {
    return Array.getLength(currentObject);
  }
  if (currentObject instanceof Map) {
    int count=0;
    for (    Object value : ((Map)currentObject).values()) {
      if (value != null) {
        count++;
      }
    }
    return count;
  }
  JavaBeanSerializer beanSerializer=getJavaBeanSerializer(currentObject.getClass());
  if (beanSerializer == null) {
    return -1;
  }
  try {
    return beanSerializer.getSize(currentObject);
  }
 catch (  Exception e) {
    throw new JSONPathException("evalSize error : " + path,e);
  }
}
