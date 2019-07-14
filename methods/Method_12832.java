/** 
 * Get an array of types for an array of objects
 * @see Object#getClass()
 */
private static Class<?>[] types(Object... values){
  if (values == null) {
    return new Class[0];
  }
  Class<?>[] result=new Class[values.length];
  for (int i=0; i < values.length; i++) {
    Object value=values[i];
    result[i]=value == null ? NULL.class : value.getClass();
  }
  return result;
}
