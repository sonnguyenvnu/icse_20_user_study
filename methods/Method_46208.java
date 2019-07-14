/** 
 * ??????????????
 * @param requestCommand ????
 */
protected void recordSerializeRequest(RequestCommand requestCommand,InvokeContext invokeContext){
  if (!RpcInternalContext.isAttachmentEnable()) {
    return;
  }
  RpcInternalContext context=null;
  if (invokeContext != null) {
    context=invokeContext.get(RemotingConstants.INVOKE_CTX_RPC_CTX);
  }
  if (context == null) {
    context=RpcInternalContext.getContext();
  }
  int cost=context.getStopWatch().tick().read();
  int requestSize=RpcProtocol.getRequestHeaderLength() + requestCommand.getClazzLength() + requestCommand.getContentLength() + requestCommand.getHeaderLength();
  context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE,requestSize);
  context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_SERIALIZE_TIME,cost);
}
