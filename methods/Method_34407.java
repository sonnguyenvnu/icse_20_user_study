/** 
 * @return true if the key has a public verifier
 */
private boolean isPublic(String key){
  return key.startsWith("-----BEGIN");
}
