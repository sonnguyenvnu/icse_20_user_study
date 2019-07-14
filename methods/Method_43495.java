public static BTCTurkDigest createInstance(String secretKey,String apiKey){
  return secretKey == null ? null : new BTCTurkDigest(secretKey);
}
