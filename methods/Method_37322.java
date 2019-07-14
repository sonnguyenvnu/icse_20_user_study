/** 
 * Converts non-collection value to collection.
 */
protected Collection<T> convertValueToCollection(Object value){
  if (value instanceof Iterable) {
    Iterable iterable=(Iterable)value;
    Collection<T> collection=createCollection(0);
    for (    Object element : iterable) {
      collection.add(convertType(element));
    }
    return collection;
  }
  if (value instanceof CharSequence) {
    value=CsvUtil.toStringArray(value.toString());
  }
  Class type=value.getClass();
  if (type.isArray()) {
    Class componentType=type.getComponentType();
    if (componentType.isPrimitive()) {
      return convertPrimitiveArrayToCollection(value,componentType);
    }
 else {
      Object[] array=(Object[])value;
      Collection<T> result=createCollection(array.length);
      for (      Object a : array) {
        result.add(convertType(a));
      }
      return result;
    }
  }
  return convertToSingleElementCollection(value);
}
