/** 
 * Injects value into the targets property.
 */
protected void injectValueIntoObject(final Object target,final PropertyDescriptor pd,final Object value){
  Object convertedValue=value;
  if (value != null) {
    Class targetClass=pd.getType();
    convertedValue=convertType(value,targetClass);
  }
  try {
    Setter setter=pd.getSetter(true);
    if (setter != null) {
      setter.invokeSetter(target,convertedValue);
    }
  }
 catch (  Exception ex) {
    throw new JsonException(ex);
  }
}
