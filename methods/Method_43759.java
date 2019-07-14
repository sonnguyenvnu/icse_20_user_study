private String generateHash(String key,String data){
  Mac sha256_HMAC=null;
  String result=null;
  try {
    byte[] byteKey=key.getBytes("UTF-8");
    final String HMAC_SHA256="HmacSHA256";
    sha256_HMAC=Mac.getInstance(HMAC_SHA256);
    SecretKeySpec keySpec=new SecretKeySpec(byteKey,HMAC_SHA256);
    sha256_HMAC.init(keySpec);
    byte[] mac_data=sha256_HMAC.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(mac_data);
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
catch (  NoSuchAlgorithmException e) {
    e.printStackTrace();
  }
catch (  InvalidKeyException e) {
    e.printStackTrace();
  }
 finally {
  }
  return "";
}
