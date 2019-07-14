/** 
 * ?????????
 * @param context RPC???
 * @param request ????
 */
protected void beforeSend(RpcInternalContext context,SofaRequest request){
  currentRequests.incrementAndGet();
  context.setLocalAddress(localAddress());
}
