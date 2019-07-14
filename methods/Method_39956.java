public static boolean validate(final Object target,final Object value,final String fieldName){
  if (value == null) {
    return true;
  }
  Object valueToCompare;
  try {
    valueToCompare=BeanUtil.pojo.getProperty(target,fieldName);
  }
 catch (  BeanException bex) {
    throw new VtorException("Invalid value: " + fieldName,bex);
  }
  if (valueToCompare == null) {
    return false;
  }
  return value.equals(valueToCompare);
}
