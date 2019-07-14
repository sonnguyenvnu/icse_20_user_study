private static IllegalArgumentException createBadEnumError(String propName,String propValue,Enum... values){
  throw new IllegalArgumentException("bad property value. property name '" + propName + "'. Expected correct enum value, one of the [" + Arrays.toString(values) + "] , actual = " + propValue);
}
