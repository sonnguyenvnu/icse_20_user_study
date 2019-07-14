private static String formatClassAndValue(Object value,String valueString){
  String className=value == null ? "null" : value.getClass().getName();
  return className + "<" + valueString + ">";
}
