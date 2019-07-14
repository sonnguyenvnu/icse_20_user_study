@Override public void clientReceived(SofaRequest request,SofaResponse response,Throwable exceptionThrow){
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  SofaTracerSpan clientSpan=sofaTraceContext.pop();
  if (clientSpan == null) {
    return;
  }
  clientSpan.log(LogData.CLIENT_RECV_EVENT_VALUE);
  RpcInternalContext context=null;
  if (RpcInternalContext.isAttachmentEnable()) {
    context=RpcInternalContext.getContext();
    if (!clientSpan.getTagsWithStr().containsKey(RpcSpanTags.ROUTE_RECORD)) {
      clientSpan.setTag(RpcSpanTags.ROUTE_RECORD,(String)context.getAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD));
    }
    clientSpan.setTag(RpcSpanTags.REQ_SERIALIZE_TIME,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SERIALIZE_TIME));
    clientSpan.setTag(RpcSpanTags.RESP_DESERIALIZE_TIME,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_DESERIALIZE_TIME));
    clientSpan.setTag(RpcSpanTags.RESP_SIZE,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE));
    clientSpan.setTag(RpcSpanTags.REQ_SIZE,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE));
    clientSpan.setTag(RpcSpanTags.CLIENT_CONN_TIME,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_CONN_CREATE_TIME));
    Long ce=(Long)context.getAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE);
    if (ce != null) {
      clientSpan.setTag(RpcSpanTags.CLIENT_ELAPSE_TIME,ce);
    }
    InetSocketAddress address=context.getLocalAddress();
    if (address != null) {
      clientSpan.setTag(RpcSpanTags.LOCAL_IP,NetUtils.toIpString(address));
      clientSpan.setTag(RpcSpanTags.LOCAL_PORT,address.getPort());
    }
  }
  Throwable throwableShow=exceptionThrow;
  String resultCode=StringUtils.EMPTY;
  String errorSourceApp=StringUtils.EMPTY;
  String tracerErrorCode=StringUtils.EMPTY;
  if (throwableShow != null) {
    if (throwableShow instanceof SofaRpcException) {
      SofaRpcException exception=(SofaRpcException)throwableShow;
      int errorType=exception.getErrorType();
switch (errorType) {
case RpcErrorType.CLIENT_TIMEOUT:
        resultCode=TracerResultCode.RPC_RESULT_TIMEOUT_FAILED;
      errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
    tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_TIMEOUT_ERROR;
  break;
case RpcErrorType.CLIENT_ROUTER:
resultCode=TracerResultCode.RPC_RESULT_ROUTE_FAILED;
errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_ADDRESS_ROUTE_ERROR;
break;
case RpcErrorType.CLIENT_SERIALIZE:
case RpcErrorType.CLIENT_DESERIALIZE:
resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_SERIALIZE_ERROR;
break;
default :
resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
errorSourceApp=ExceptionUtils.isServerException(exception) ? clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP) : clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
break;
}
}
 else {
resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
}
}
 else if (response != null) {
if (response.isError()) {
errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP);
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
throwableShow=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,response.getErrorMsg());
}
 else {
Object ret=response.getAppResponse();
if (ret instanceof Throwable || "true".equals(response.getResponseProp(RemotingConstants.HEAD_RESPONSE_ERROR))) {
errorSourceApp=clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP);
resultCode=TracerResultCode.RPC_RESULT_BIZ_FAILED;
tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_BIZ_ERROR;
}
 else {
resultCode=TracerResultCode.RPC_RESULT_SUCCESS;
}
}
}
if (throwableShow != null) {
Map<String,String> contextMap=new HashMap<String,String>();
this.generateClientErrorContext(contextMap,request,clientSpan);
clientSpan.reportError(tracerErrorCode,contextMap,throwableShow,errorSourceApp,ERROR_SOURCE);
}
clientSpan.setTag(RpcSpanTags.RESULT_CODE,resultCode);
clientSpan.finish();
if (context != null) {
context.setAttachment(RpcConstants.INTERNAL_KEY_RESULT_CODE,resultCode);
}
if (clientSpan.getParentSofaTracerSpan() != null) {
sofaTraceContext.push(clientSpan.getParentSofaTracerSpan());
}
}
