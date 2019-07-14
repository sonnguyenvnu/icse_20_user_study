/** 
 * ?????key??"_"??"."??
 * @param key ??key
 * @return ????
 */
public static boolean isValidInternalParamKey(String key){
  char c=key.charAt(0);
  return c == RpcConstants.INTERNAL_KEY_PREFIX || c == RpcConstants.HIDE_KEY_PREFIX;
}
