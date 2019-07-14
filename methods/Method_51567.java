private static boolean isHttpUrl(String name){
  String stripped=StringUtils.strip(name);
  if (stripped == null) {
    return false;
  }
  return stripped.startsWith("http://") || stripped.startsWith("https://");
}
