private String generateHash(String ApiKey,String timestamp){
  Mac sha256_HMAC=null;
  String message=ApiKey + timestamp;
  try {
    sha256_HMAC=Mac.getInstance(HMAC_SHA_256);
    SecretKeySpec keySpec=new SecretKeySpec(SecretKey,HMAC_SHA_256);
    sha256_HMAC.init(keySpec);
    return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
  }
 catch (  NoSuchAlgorithmException e) {
    e.printStackTrace();
  }
catch (  InvalidKeyException e) {
    e.printStackTrace();
  }
  return "";
}
