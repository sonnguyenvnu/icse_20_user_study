<T>T convertInternal(Object value,Class primitiveClass,Class boxedClass){
  int size;
  if (value == null) {
    return null;
  }
  if (primitiveClass != null && (value.getClass().isArray()) && (value.getClass().getComponentType().equals(primitiveClass))) {
    return (T)value;
  }
 else   if ((size=isIterableOf(value,boxedClass)) >= 0) {
    Object array=getArray(size);
    int pos=0;
    for (    Object element : (Iterable)value)     setArray(array,pos++,element);
    return (T)array;
  }
 else   if ((size=isArrayOf(value,boxedClass)) >= 0) {
    Object array=getArray(size);
    for (int i=0; i < size; i++) {
      setArray(array,i,Array.get(value,i));
    }
    return (T)array;
  }
  return null;
}
