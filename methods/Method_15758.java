public SecretKey generalKey(){
  byte[] encodedKey=Base64.decodeBase64(secret);
  return new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
}
