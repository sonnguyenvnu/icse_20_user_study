public static String normalizeUri(String value){
  if (value == null) {
    return value;
  }
  if (value.startsWith("/")) {
    value=value.substring(1);
  }
  if (value.endsWith("/")) {
    value=value.substring(0,value.length() - 1);
  }
  return value;
}
