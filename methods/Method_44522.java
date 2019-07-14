public static String makeSign(String data,String key) throws Exception {
  SecretKeySpec sign=new SecretKeySpec(key.getBytes(),HMAC_SHA_1);
  Mac mac=Mac.getInstance(HMAC_SHA_1);
  mac.init(sign);
  byte[] rawHmac=mac.doFinal(data.getBytes());
  return arrayHex(rawHmac);
}
