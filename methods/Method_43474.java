public static BTCTradeDigest createInstance(String secret){
  return new BTCTradeDigest(secret.getBytes(CHARSET));
}
