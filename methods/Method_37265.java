/** 
 * Converts <b>Map</b> index to key type. If conversion fails, original value will be returned.
 */
protected Object convertIndexToMapKey(final Getter getter,final Object index){
  Class indexType=null;
  if (getter != null) {
    indexType=getter.getGetterRawKeyComponentType();
  }
  if (indexType == null) {
    indexType=Object.class;
  }
  if (indexType == Object.class) {
    return index;
  }
  try {
    return convertType(index,indexType);
  }
 catch (  Exception ignore) {
    return index;
  }
}
