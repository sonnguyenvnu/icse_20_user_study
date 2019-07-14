public String extractKey(OAuth2ProtectedResourceDetails resource,Authentication authentication){
  Map<String,String> values=new LinkedHashMap<String,String>();
  if (authentication != null) {
    values.put(USERNAME,authentication.getName());
  }
  values.put(CLIENT_ID,resource.getClientId());
  if (resource.getScope() != null) {
    values.put(SCOPE,OAuth2Utils.formatParameterList(resource.getScope()));
  }
  MessageDigest digest;
  try {
    digest=MessageDigest.getInstance("MD5");
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
  }
  try {
    byte[] bytes=digest.digest(values.toString().getBytes("UTF-8"));
    return String.format("%032x",new BigInteger(1,bytes));
  }
 catch (  UnsupportedEncodingException e) {
    throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
  }
}
