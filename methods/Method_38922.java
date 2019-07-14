protected Object value(final String name,final PageContext pageContext){
  String thisRef=BeanUtil.pojo.extractThisReference(name);
  Object value=ServletUtil.value(pageContext,thisRef);
  if (value == null) {
    return ServletUtil.value(pageContext,name);
  }
  if (thisRef.equals(name)) {
    return value;
  }
  String propertyName=name.substring(thisRef.length() + 1);
  return BeanUtil.declaredSilent.getProperty(value,propertyName);
}
