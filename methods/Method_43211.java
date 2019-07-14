public static BitMarketDigest createInstance(String secretKeyBase64){
  return secretKeyBase64 == null ? null : new BitMarketDigest(secretKeyBase64);
}
