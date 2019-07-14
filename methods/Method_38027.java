public static String newString(final byte[] bytes,final String charsetName){
  try {
    return new String(bytes,charsetName);
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
