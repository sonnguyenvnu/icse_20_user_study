@Override public SofaResponse invoke(FilterInvoker invoker,SofaRequest request) throws SofaRpcException {
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  SofaTracerSpan clientSpan=sofaTraceContext.getCurrentSpan();
  clientSpan.setTag(RpcSpanTags.INVOKE_TYPE,request.getInvokeType());
  RpcInternalContext context=RpcInternalContext.getContext();
  clientSpan.setTag(RpcSpanTags.ROUTE_RECORD,(String)context.getAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD));
  ProviderInfo providerInfo=context.getProviderInfo();
  if (providerInfo != null) {
    clientSpan.setTag(RpcSpanTags.REMOTE_APP,providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_APP_NAME));
    clientSpan.setTag(RpcSpanTags.REMOTE_IP,providerInfo.getHost() + ":" + providerInfo.getPort());
  }
  return invoker.invoke(request);
}
