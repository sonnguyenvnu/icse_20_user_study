public byte[] sign(byte[] bytes){
  try {
    Mac mac=Mac.getInstance(algorithm);
    mac.init(key);
    return mac.doFinal(bytes);
  }
 catch (  GeneralSecurityException e) {
    throw new RuntimeException(e);
  }
}
