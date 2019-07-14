/** 
 * Gets the call type corresponding to the method name
 * @param methodName the method name
 * @return the call type
 */
public String getMethodInvokeType(String methodName){
  return (String)getMethodConfigValue(methodName,RpcConstants.CONFIG_KEY_INVOKE_TYPE,getInvokeType());
}
