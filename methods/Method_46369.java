@Override public void serverReceived(SofaRequest request){
  Map<String,String> tags=new HashMap<String,String>();
  tags.put(Tags.SPAN_KIND.getKey(),Tags.SPAN_KIND_SERVER);
  String spanStrs=(String)request.getRequestProp(RemotingConstants.NEW_RPC_TRACE_NAME);
  SofaTracerSpanContext spanContext=null;
  if (StringUtils.isBlank(spanStrs)) {
    Object oldInstanceMap=request.getRequestProp(RemotingConstants.RPC_TRACE_NAME);
    spanContext=this.saveSpanContextAndTags(tags,oldInstanceMap);
  }
 else {
    spanContext=SofaTracerSpanContext.deserializeFromString(spanStrs);
  }
  if (spanContext == null) {
    SelfLog.error("SpanContext created error when server received and root SpanContext created.");
    spanContext=SofaTracerSpanContext.rootStart();
  }
  SofaTracerSpan serverSpan=new SofaTracerSpan(this.sofaTracer,System.currentTimeMillis(),request.getInterfaceName(),spanContext,tags);
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  serverSpan.log(LogData.SERVER_RECV_EVENT_VALUE);
  sofaTraceContext.push(serverSpan);
  if (RpcInternalContext.isAttachmentEnable()) {
    RpcInternalContext context=RpcInternalContext.getContext();
    context.setAttachment(RpcConstants.INTERNAL_KEY_TRACE_ID,spanContext.getTraceId());
    context.setAttachment(RpcConstants.INTERNAL_KEY_SPAN_ID,spanContext.getSpanId());
  }
}
