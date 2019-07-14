public static BitcointoyouDigest createInstance(String secretKeyBase64,String apiKey){
  return secretKeyBase64 == null ? null : new BitcointoyouDigest(secretKeyBase64,apiKey);
}
