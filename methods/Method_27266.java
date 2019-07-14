private static String trimAlias(String alias){
  String result=alias;
  if (result.startsWith(":")) {
    result=result.substring(1,result.length());
  }
  if (result.endsWith(":")) {
    result=result.substring(0,result.length() - 1);
  }
  return result;
}
