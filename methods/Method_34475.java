/** 
 * {@inheritDoc}
 */
@Override public String getActionName(){
  if (hystrixCommand != null && hystrixCommand instanceof HystrixInvokableInfo) {
    HystrixInvokableInfo info=(HystrixInvokableInfo)hystrixCommand;
    return info.getCommandKey().name();
  }
  return "";
}
