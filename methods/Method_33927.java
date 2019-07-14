private static RSAPrivateKey loadPrivateKey(String key){
  KeyPair kp=RsaKeyHelper.parseKeyPair(key);
  if (kp.getPrivate() == null) {
    throw new IllegalArgumentException("Not a private key");
  }
  return (RSAPrivateKey)kp.getPrivate();
}
