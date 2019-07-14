public static CoinbaseProDigest createInstance(String secretKey){
  return secretKey == null ? null : new CoinbaseProDigest(Base64.getDecoder().decode(secretKey));
}
