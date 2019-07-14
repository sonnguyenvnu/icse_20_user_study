protected int isIterableOf(Object value,Class boxedClass){
  if (!(value instanceof Iterable))   return -1;
  Iterable c=(Iterable)value;
  int size=0;
  for (  Object element : c) {
    if (element == null || !element.getClass().equals(boxedClass))     return -1;
    size++;
  }
  return size;
}
