/** 
 * Decrypts the set phrase/password which was encrypted via  {@link Password#encrypt()}.
 * @return Decrypted phrase/password
 */
public String decrypt() throws GeneralSecurityException, UnsupportedEncodingException {
  cipher.init(Cipher.DECRYPT_MODE,secretKey,ivSpec);
  return new String(cipher.doFinal(Base64.getDecoder().decode(phrase)),StandardCharsets.UTF_8);
}
