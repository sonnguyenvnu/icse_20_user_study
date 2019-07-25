private static <OBJECT_TYPE>boolean contains(OBJECT_TYPE[] array,OBJECT_TYPE value){
  if (array == null || array.length == 0)   return false;
  for (  OBJECT_TYPE anArray : array) {
    if (Objects.equals(value,anArray))     return true;
  }
  return false;
}
