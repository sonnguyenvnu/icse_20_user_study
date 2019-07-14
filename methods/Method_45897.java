@Override public SofaResponse invoke(FilterInvoker invoker,SofaRequest request) throws SofaRpcException {
  ConsumerConfig config=(ConsumerConfig)invoker.getConfig();
  RpcReferenceContext referenceCtx=new RpcReferenceContext();
  referenceCtx.setGeneric(config.isGeneric());
  referenceCtx.setInterfaceName(config.getInterfaceId());
  referenceCtx.setUniqueId(config.getUniqueId());
  referenceCtx.setServiceName(request.getTargetServiceUniqueName());
  referenceCtx.setMethodName(request.getMethodName());
  RpcInternalContext context=RpcInternalContext.getContext();
  ProviderInfo providerInfo=context.getProviderInfo();
  if (providerInfo != null) {
    referenceCtx.setTargetAppName(providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_APP_NAME));
    referenceCtx.setTargetUrl(providerInfo.getHost() + ":" + providerInfo.getPort());
  }
  referenceCtx.setProtocol(config.getProtocol());
  referenceCtx.setInvokeType(request.getInvokeType());
  referenceCtx.setRouteRecord((String)context.getAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD));
  RpcInvokeContext.getContext().put(RemotingConstants.INVOKE_CTX_RPC_REF_CTX,referenceCtx);
  SofaResponse response=invoker.invoke(request);
  InetSocketAddress local=context.getLocalAddress();
  if (local != null) {
    referenceCtx.setClientIP(NetUtils.toIpString(local));
    referenceCtx.setClientPort(local.getPort());
  }
  Long ct=(Long)context.getAttachment(RpcConstants.INTERNAL_KEY_CONN_CREATE_TIME);
  if (ct != null) {
    referenceCtx.setConnEstablishedSpan(ct);
  }
  Integer qs=(Integer)context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE);
  if (qs != null) {
    referenceCtx.setRequestSize(qs.longValue());
  }
  Integer ps=(Integer)context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE);
  if (ps != null) {
    referenceCtx.setResponseSize(ps.longValue());
  }
  referenceCtx.setTraceId((String)context.getAttachment(RpcConstants.INTERNAL_KEY_TRACE_ID));
  referenceCtx.setRpcId((String)context.getAttachment(RpcConstants.INTERNAL_KEY_SPAN_ID));
  Long ce=(Long)context.getAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE);
  if (ce != null) {
    referenceCtx.setCostTime(ce);
  }
  return response;
}
