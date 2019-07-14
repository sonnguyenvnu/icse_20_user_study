/** 
 * Creates a private key from the PKCS#8-encoded value of the given bytes.
 * @param privateKey The PKCS#8-encoded private key bytes.
 * @return The private key.
 */
public static PrivateKey createPrivateKey(byte[] privateKey){
  if (privateKey == null) {
    return null;
  }
  try {
    KeyFactory fac=KeyFactory.getInstance("RSA");
    EncodedKeySpec spec=new PKCS8EncodedKeySpec(privateKey);
    return fac.generatePrivate(spec);
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException(e);
  }
catch (  InvalidKeySpecException e) {
    throw new IllegalStateException(e);
  }
}
