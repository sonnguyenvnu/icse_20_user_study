public static String getGenericMIME(String mime){
  return mime.split("/")[0] + "/*";
}
