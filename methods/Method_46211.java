/** 
 * ??????????????????????
 * @param responseCommand ???
 */
private void recordDeserializeResponse(RpcResponseCommand responseCommand,InvokeContext invokeContext){
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
  int respSize=RpcProtocol.getResponseHeaderLength() + responseCommand.getClazzLength() + responseCommand.getContentLength() + responseCommand.getHeaderLength();
  context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE,respSize);
  context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_DESERIALIZE_TIME,cost);
}
