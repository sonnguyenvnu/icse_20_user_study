/** 
 * Creates a public key from the X509-encoded value of the given bytes.
 * @param publicKey The X509-encoded public key bytes.
 * @return The public key.
 */
public static PublicKey createPublicKey(byte[] publicKey){
  if (publicKey == null) {
    return null;
  }
  try {
    KeyFactory fac=KeyFactory.getInstance("RSA");
    EncodedKeySpec spec=new X509EncodedKeySpec(publicKey);
    return fac.generatePublic(spec);
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException(e);
  }
catch (  InvalidKeySpecException e) {
    throw new IllegalStateException(e);
  }
}
