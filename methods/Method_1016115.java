/** 
 * Returns text decoded using base64 and decrypted through xor.
 * @param text text to decrypt
 * @param key  xor key
 * @return decrypted text
 */
public static String decrypt(final String text,final String key){
  return xorText(base64decode(text),key);
}
