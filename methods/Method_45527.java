/** 
 * Buildmkey string.
 * @param methodName the method name
 * @param key        the key
 * @return the string
 */
private String buildmkey(String methodName,String key){
  return RpcConstants.HIDE_KEY_PREFIX + methodName + RpcConstants.HIDE_KEY_PREFIX + key;
}
