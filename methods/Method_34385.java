protected String extractTokenKey(String value){
  if (value == null) {
    return null;
  }
  MessageDigest digest;
  try {
    digest=MessageDigest.getInstance("MD5");
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
  }
  try {
    byte[] bytes=digest.digest(value.getBytes("UTF-8"));
    return String.format("%032x",new BigInteger(1,bytes));
  }
 catch (  UnsupportedEncodingException e) {
    throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
  }
}
