/** 
 * ???????????????
 * @param methodName ?????????
 * @return method onReturn
 */
public SofaResponseCallback getMethodOnreturn(String methodName){
  return (SofaResponseCallback)getMethodConfigValue(methodName,RpcConstants.CONFIG_KEY_ONRETURN,getOnReturn());
}
