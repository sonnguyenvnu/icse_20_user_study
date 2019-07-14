protected boolean propertyInScope(Object obj,String property,Set<Object> scope){
  if (null == obj) {
    return false;
  }
  try {
    Object value=BeanUtilsBean.getInstance().getProperty(obj,property);
    if (null != value) {
      return scope.contains(value);
    }
  }
 catch (  Exception ignore) {
    logger.warn("can not get property {} from {},{}",property,obj,ignore.getMessage());
  }
  return true;
}
