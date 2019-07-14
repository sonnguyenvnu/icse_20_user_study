private static boolean checkType(String mime,String check){
  return mime != null && mime.contains("/") && check.equals(mime.substring(0,mime.indexOf("/")));
}
