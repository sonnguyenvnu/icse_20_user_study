/** 
 * ????key????
 * @param paramkey ??key
 * @return ????
 */
public static boolean isValidParamKey(String paramkey){
  char c=paramkey.charAt(0);
  return c != RpcConstants.HIDE_KEY_PREFIX && c != RpcConstants.INTERNAL_KEY_PREFIX;
}
