/** 
 * Encrypts the set phrase/password with a symmetric encryption algorithm.
 * @return Encrypted phrase/password
 */
public String encrypt() throws GeneralSecurityException, UnsupportedEncodingException {
  cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivSpec);
  return new String(Base64.getEncoder().encode(cipher.doFinal(phrase)),StandardCharsets.UTF_8);
}
