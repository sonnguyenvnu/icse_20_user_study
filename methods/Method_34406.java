/** 
 * Sets the JWT signing key. It can be either a simple MAC key or an RSA key. RSA keys should be in OpenSSH format, as produced by <tt>ssh-keygen</tt>.
 * @param key the key to be used for signing JWTs.
 */
public void setSigningKey(String key){
  Assert.hasText(key);
  key=key.trim();
  this.signingKey=key;
  if (isPublic(key)) {
    signer=new RsaSigner(key);
    logger.info("Configured with RSA signing key");
  }
 else {
    this.verifierKey=key;
    signer=new MacSigner(key);
  }
}
