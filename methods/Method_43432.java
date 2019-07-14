public static BleutradeDigest createInstance(String secretKeyBase64){
  return secretKeyBase64 == null ? null : new BleutradeDigest(secretKeyBase64);
}
