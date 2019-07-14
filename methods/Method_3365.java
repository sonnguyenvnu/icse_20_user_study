static void setProperty(Class<?> type,PropertyDescriptor property,Object target,Object value,String delimiter){
  try {
    value=getValue(type,value,delimiter);
    property.getWriteMethod().invoke(target,value);
  }
 catch (  IllegalAccessException iae) {
    throw new IllegalArgumentException("Could not set property " + property,iae);
  }
catch (  NoSuchMethodException e) {
    throw new IllegalArgumentException("Could not find constructor in class " + type.getName() + " that takes a string",e);
  }
catch (  InvocationTargetException e) {
    throw new IllegalArgumentException("Failed to validate argument " + value + " for " + property);
  }
}
