public static String newString(final byte[] bytes){
  try {
    return new String(bytes,JoddCore.encoding);
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
