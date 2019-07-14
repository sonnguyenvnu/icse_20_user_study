/** 
 * ???token??
 * @return ?????token boolean
 */
public boolean hasToken(){
  if (getParameter(RpcConstants.HIDDEN_KEY_TOKEN) != null) {
    return true;
  }
  if (CommonUtils.isNotEmpty(methods)) {
    for (    MethodConfig methodConfig : methods.values()) {
      if (methodConfig.getParameter(RpcConstants.HIDDEN_KEY_TOKEN) != null) {
        return true;
      }
    }
  }
  return false;
}
