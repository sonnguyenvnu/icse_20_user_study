protected String generateKey(Map<String,String> values){
  MessageDigest digest;
  try {
    digest=MessageDigest.getInstance("MD5");
    byte[] bytes=digest.digest(values.toString().getBytes("UTF-8"));
    return String.format("%032x",new BigInteger(1,bytes));
  }
 catch (  NoSuchAlgorithmException nsae) {
    throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).",nsae);
  }
catch (  UnsupportedEncodingException uee) {
    throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).",uee);
  }
}
