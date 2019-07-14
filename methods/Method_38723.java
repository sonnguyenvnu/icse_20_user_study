/** 
 * Sets the property value.
 */
private void setValue(final Object target,final PropertyDescriptor pd,Object value) throws InvocationTargetException, IllegalAccessException {
  Class propertyType;
  Setter setter=pd.getSetter(true);
  if (setter != null) {
    if (value != null) {
      propertyType=setter.getSetterRawType();
      value=jsonParser.convertType(value,propertyType);
    }
    setter.invokeSetter(target,value);
  }
}
