/** 
 * ??????
 * @return ???Future?Callback??????
 */
public boolean isAsync(){
  return invokeType != null && (RpcConstants.INVOKER_TYPE_CALLBACK.equals(invokeType) || RpcConstants.INVOKER_TYPE_FUTURE.equals(invokeType));
}
