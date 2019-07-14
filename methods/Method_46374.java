@Override public void clientAsyncReceivedPrepare(){
  RpcInternalContext rpcInternalContext=RpcInternalContext.getContext();
  SofaTracerSpan clientSpan=(SofaTracerSpan)rpcInternalContext.getAttachment(RpcConstants.INTERNAL_KEY_TRACER_SPAN);
  if (clientSpan == null) {
    return;
  }
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  sofaTraceContext.push(clientSpan);
}
