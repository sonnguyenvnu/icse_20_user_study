/** 
 * Converts value to a string.
 */
protected String valueToString(final String name,final Object valueObject){
  if (!valueObject.getClass().isArray()) {
    return valueObject.toString();
  }
  String[] array=(String[])valueObject;
  if (valueNameIndexes == null) {
    valueNameIndexes=new HashMap<>();
  }
  MutableInteger index=valueNameIndexes.get(name);
  if (index == null) {
    index=new MutableInteger(0);
    valueNameIndexes.put(name,index);
  }
  if (index.value >= array.length) {
    return null;
  }
  String result=array[index.value];
  index.value++;
  return result;
}
