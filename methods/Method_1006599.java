private static String unquote(String value){
  if (value == null) {
    return value;
  }
  return (value.length() > 1 && value.startsWith("\"") && value.endsWith("\"")) ? value.substring(1,value.length() - 1) : value;
}
