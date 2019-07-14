private static ConfigNamespace stringToNamespace(String raw) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
  int i=raw.lastIndexOf(".");
  String fullClassName=raw.substring(0,i);
  String fieldName=raw.substring(i + 1);
  return (ConfigNamespace)Class.forName(fullClassName).getField(fieldName).get(null);
}
