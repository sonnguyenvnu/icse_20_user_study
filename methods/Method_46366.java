@Override public void clientBeforeSend(SofaRequest request){
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  SofaTracerSpan clientSpan=sofaTraceContext.getCurrentSpan();
  if (clientSpan == null) {
    SelfLog.warn("ClientSpan is null.Before call interface=" + request.getInterfaceName() + ",method=" + request.getMethodName());
    return;
  }
  SofaTracerSpanContext sofaTracerSpanContext=clientSpan.getSofaTracerSpanContext();
  RpcInternalContext rpcInternalContext=RpcInternalContext.getContext();
  ProviderInfo providerInfo;
  if ((providerInfo=rpcInternalContext.getProviderInfo()) != null && providerInfo.getRpcVersion() >= 50100) {
    String serializedSpanContext=sofaTracerSpanContext.serializeSpanContext();
    request.addRequestProp(RemotingConstants.NEW_RPC_TRACE_NAME,serializedSpanContext);
  }
 else {
    Map<String,String> oldTracerContext=new HashMap<String,String>();
    oldTracerContext.put(TracerCompatibleConstants.TRACE_ID_KEY,sofaTracerSpanContext.getTraceId());
    oldTracerContext.put(TracerCompatibleConstants.RPC_ID_KEY,sofaTracerSpanContext.getSpanId());
    oldTracerContext.put(TracerCompatibleConstants.SAMPLING_MARK,String.valueOf(sofaTracerSpanContext.isSampled()));
    oldTracerContext.put(TracerCompatibleConstants.PEN_ATTRS_KEY,sofaTracerSpanContext.getBizSerializedBaggage());
    oldTracerContext.put(TracerCompatibleConstants.PEN_SYS_ATTRS_KEY,sofaTracerSpanContext.getSysSerializedBaggage());
    Map<String,Object> attachments=rpcInternalContext.getAttachments();
    oldTracerContext.put(TracerCompatibleConstants.CALLER_APP_KEY,getEmptyStringIfNull(attachments,RpcSpanTags.REMOTE_APP));
    oldTracerContext.put(TracerCompatibleConstants.CALLER_ZONE_KEY,getEmptyStringIfNull(attachments,RpcSpanTags.REMOTE_ZONE));
    oldTracerContext.put(TracerCompatibleConstants.CALLER_IDC_KEY,getEmptyStringIfNull(attachments,RpcSpanTags.REMOTE_IDC));
    oldTracerContext.put(TracerCompatibleConstants.CALLER_IP_KEY,getEmptyStringIfNull(attachments,RpcSpanTags.REMOTE_IP));
    request.addRequestProp(RemotingConstants.RPC_TRACE_NAME,oldTracerContext);
  }
  if (request.isAsync()) {
    clientSpan=sofaTraceContext.pop();
    if (clientSpan != null) {
      clientSpan.log(LogData.CLIENT_SEND_EVENT_VALUE);
    }
    rpcInternalContext.setAttachment(RpcConstants.INTERNAL_KEY_TRACER_SPAN,clientSpan);
    if (clientSpan != null && clientSpan.getParentSofaTracerSpan() != null) {
      sofaTraceContext.push(clientSpan.getParentSofaTracerSpan());
    }
  }
 else {
    clientSpan.log(LogData.CLIENT_SEND_EVENT_VALUE);
  }
}
