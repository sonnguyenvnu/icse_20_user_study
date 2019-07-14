private static Object getValue(Class<?> type,Object value,String delimiter) throws NoSuchMethodException {
  if (type != String.class && type != Boolean.class && type != Boolean.TYPE) {
    String string=(String)value;
    if (type.isArray()) {
      String[] strings=string.split(delimiter);
      type=type.getComponentType();
      if (type == String.class) {
        value=strings;
      }
 else {
        Object[] array=(Object[])Array.newInstance(type,strings.length);
        for (int i=0; i < array.length; i++) {
          array[i]=createValue(type,strings[i]);
        }
        value=array;
      }
    }
 else {
      value=createValue(type,string);
    }
  }
  return value;
}
