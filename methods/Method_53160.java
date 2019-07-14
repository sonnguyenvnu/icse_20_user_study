/** 
 * Computes RFC 2104-compliant HMAC signature.
 * @param data  the data to be signed
 * @param token the token
 * @return signature
 * @see <a href="http://oauth.net/core/1.0a/#rfc.section.9.2.1">OAuth Core - 9.2.1.  Generating Signature</a>
 */
String generateSignature(String data,OAuthToken token){
  byte[] byteHMAC=null;
  try {
    Mac mac=Mac.getInstance(HMAC_SHA1);
    SecretKeySpec spec;
    if (null == token) {
      String oauthSignature=HttpParameter.encode(consumerSecret) + "&";
      spec=new SecretKeySpec(oauthSignature.getBytes(),HMAC_SHA1);
    }
 else {
      spec=token.getSecretKeySpec();
      if (null == spec) {
        String oauthSignature=HttpParameter.encode(consumerSecret) + "&" + HttpParameter.encode(token.getTokenSecret());
        spec=new SecretKeySpec(oauthSignature.getBytes(),HMAC_SHA1);
        token.setSecretKeySpec(spec);
      }
    }
    mac.init(spec);
    byteHMAC=mac.doFinal(data.getBytes());
  }
 catch (  InvalidKeyException ike) {
    logger.error("Failed initialize \"Message Authentication Code\" (MAC)",ike);
    throw new AssertionError(ike);
  }
catch (  NoSuchAlgorithmException nsae) {
    logger.error("Failed to get HmacSHA1 \"Message Authentication Code\" (MAC)",nsae);
    throw new AssertionError(nsae);
  }
  return BASE64Encoder.encode(byteHMAC);
}
