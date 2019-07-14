public Object getValue(){
  if (value != null) {
    return value;
  }
  if (method.isAnnotationPresent(Deprecated.class) && LOG.isLoggable(Level.WARNING) && DETECTED_DEPRECATED_ATTRIBUTES.putIfAbsent(getLoggableAttributeName(),Boolean.TRUE) == null) {
    LOG.warning("Use of deprecated attribute '" + getLoggableAttributeName() + "' in XPath query");
  }
  try {
    value=method.invoke(parent,EMPTY_OBJ_ARRAY);
    return value;
  }
 catch (  IllegalAccessException|InvocationTargetException iae) {
    iae.printStackTrace();
  }
  return null;
}
