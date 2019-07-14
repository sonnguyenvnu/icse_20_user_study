private static String getTypeName(Class<?> type){
  String typeName=type.getName();
  int beginIndex=typeName.lastIndexOf(".");
  typeName=typeName.substring(beginIndex + 1);
  return typeName;
}
