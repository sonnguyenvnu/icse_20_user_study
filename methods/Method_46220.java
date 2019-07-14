/** 
 * ?????
 */
protected void init(){
  RpcInternalContext context=RpcInternalContext.getContext();
  asyncContext=(AsyncContext)context.getAttachment(RpcConstants.HIDDEN_KEY_ASYNC_CONTEXT);
  request=(SofaRequest)context.getAttachment(RpcConstants.HIDDEN_KEY_ASYNC_REQUEST);
}
