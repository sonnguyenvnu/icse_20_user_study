String generate(String content) throws Exception {
  SecretKeySpec signingKey=new SecretKeySpec(secret.getBytes(),HMAC_SHA256_ALGORITHM);
  Mac mac=Mac.getInstance(HMAC_SHA256_ALGORITHM);
  mac.init(signingKey);
  byte[] rawHmac=mac.doFinal(content.getBytes());
  return DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
}
