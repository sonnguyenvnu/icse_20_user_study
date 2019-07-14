/** 
 * @return true if the signing key is a public key
 */
public boolean isPublic(){
  return signer instanceof RsaSigner;
}
