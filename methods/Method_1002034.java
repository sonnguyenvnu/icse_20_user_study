/** 
 * HKDF-Extract(salt, IKM) -&gt; PRK
 * @param salt optional salt value (a non-secret random value); if not provided, it is set to a string of HashLen zeros.
 * @param ikm input keying material
 * @return a pseudorandom key (of HashLen bytes)
 */
public SecretKey extract(SecretKey salt,byte[] ikm){
  requireNonNull(ikm,"ikm must not be null");
  if (salt == null) {
    salt=new SecretKeySpec(new byte[hash.getByteLength()],hash.getAlgorithm());
  }
  Mac mac=initMac(salt);
  byte[] keyBytes=mac.doFinal(ikm);
  return new SecretKeySpec(keyBytes,hash.getAlgorithm());
}
