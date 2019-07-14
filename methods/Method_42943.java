public static BiboxDigest createInstance(String secretKey){
  return secretKey == null ? null : new BiboxDigest(secretKey);
}
