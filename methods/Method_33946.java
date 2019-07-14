/** 
 * Sign the signature base string. The signature is the digest octet string, first base64-encoded per RFC2045, section 6.8, then URL-encoded per OAuth Parameter Encoding.
 * @param signatureBaseString The signature base string.
 * @return The signature.
 */
public String sign(String signatureBaseString){
  try {
    Mac mac=Mac.getInstance(MAC_NAME);
    mac.init(key);
    byte[] text=signatureBaseString.getBytes("UTF-8");
    byte[] signatureBytes=mac.doFinal(text);
    signatureBytes=Base64.encodeBase64(signatureBytes);
    String signature=new String(signatureBytes,"UTF-8");
    if (LOG.isDebugEnabled()) {
      LOG.debug("signature base: " + signatureBaseString);
      LOG.debug("signature: " + signature);
    }
    return signature;
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException(e);
  }
catch (  InvalidKeyException e) {
    throw new IllegalStateException(e);
  }
catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
