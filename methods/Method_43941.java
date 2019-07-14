protected static byte[] decodeBase64(String secretKey){
  return Base64.getDecoder().decode(secretKey);
}
