public static String unescape(String str,Escaping escaping){
  if (escaping == Escaping.NO_ESCAPING)   return str;
  try {
    return URLDecoder.decode(str,RestConstants.DEFAULT_CHARSET_NAME);
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
