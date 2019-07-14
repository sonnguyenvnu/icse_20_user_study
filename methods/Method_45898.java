/** 
 * ??????
 * @param invoker ???
 * @return ????????
 */
@Override public boolean needToLoad(FilterInvoker invoker){
  return RpcInternalContext.isAttachmentEnable();
}
