protected int isArrayOf(Object value,Class boxedClass){
  if (!value.getClass().isArray() || !value.getClass().getComponentType().equals(boxedClass))   return -1;
  for (int i=0; i < Array.getLength(value); i++) {
    if (Array.get(value,i) == null)     return -1;
    assert Array.get(value,i).getClass().equals(boxedClass);
  }
  return Array.getLength(value);
}
