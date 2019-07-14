/** 
 * Gets key pairs.
 * @param key   the key
 * @param value the value
 * @return the key pairs
 */
private String getKeyPairs(String key,Object value){
  if (value != null) {
    return "&" + key + "=" + value.toString();
  }
 else {
    return "";
  }
}
