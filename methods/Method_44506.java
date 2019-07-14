public static KucoinDigest createInstance(String secretKey){
  return Strings.isNullOrEmpty(secretKey) ? null : new KucoinDigest(secretKey.getBytes(StandardCharsets.UTF_8));
}
