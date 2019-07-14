/** 
 * ??"."?????key
 * @param key ??key
 * @return ????key
 */
static boolean isHiddenParamKey(String key){
  char c=key.charAt(0);
  return c == RpcConstants.HIDE_KEY_PREFIX;
}
