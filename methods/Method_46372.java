@Override public void serverSend(SofaRequest request,SofaResponse response,Throwable exception){
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  SofaTracerSpan serverSpan=sofaTraceContext.pop();
  if (serverSpan == null) {
    return;
  }
  serverSpan.log(LogData.SERVER_SEND_EVENT_VALUE);
  RpcInternalContext context=RpcInternalContext.getContext();
  serverSpan.setTag(RpcSpanTags.RESP_SERIALIZE_TIME,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SERIALIZE_TIME));
  serverSpan.setTag(RpcSpanTags.REQ_DESERIALIZE_TIME,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_DESERIALIZE_TIME));
  serverSpan.setTag(RpcSpanTags.RESP_SIZE,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE));
  serverSpan.setTag(RpcSpanTags.REQ_SIZE,(Number)context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE));
  serverSpan.setTag(RpcSpanTags.CURRENT_THREAD_NAME,Thread.currentThread().getName());
  Throwable throwableShow=exception;
  String tracerErrorCode=StringUtils.EMPTY;
  String errorSourceApp=StringUtils.EMPTY;
  String resultCode=StringUtils.EMPTY;
  if (throwableShow != null) {
    errorSourceApp=serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
    resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
    tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
  }
 else   if (response != null) {
    if (response.isError()) {
      errorSourceApp=serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
      resultCode=TracerResultCode.RPC_RESULT_RPC_FAILED;
      tracerErrorCode=TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
      throwableShow=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,response.getErrorMsg());
    }
 else {
      Object ret=response.getAppResponse();
      if (ret instanceof Throwable) {
        throwableShow=(Throwable)ret;
        errorSourceApp=serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
        resultCode=TracerResultCode.RPC_RESULT_BIZ_FAILED;
        tracerErrorCode=TracerResultCode.RPC_RESULT_BIZ_FAILED;
      }
 else {
        resultCode=TracerResultCode.RPC_RESULT_SUCCESS;
      }
    }
  }
  if (throwableShow != null) {
    Map<String,String> errorContext=new HashMap<String,String>();
    this.generateServerErrorContext(errorContext,request,serverSpan);
    serverSpan.reportError(tracerErrorCode,errorContext,throwableShow,errorSourceApp,ERROR_SOURCE);
  }
  serverSpan.setTag(RpcSpanTags.RESULT_CODE,resultCode);
  serverSpan.finish();
}
