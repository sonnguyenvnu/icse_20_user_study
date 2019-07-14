/** 
 * create RpcClientLookoutModel
 * @param request
 * @param response
 * @return
 */
private RpcClientLookoutModel createClientMetricsModel(SofaRequest request,SofaResponse response){
  RpcClientLookoutModel clientMetricsModel=new RpcClientLookoutModel();
  RpcInternalContext context=RpcInternalContext.getContext();
  String app=getStringAvoidNull(context.getAttachment(RpcConstants.INTERNAL_KEY_APP_NAME));
  String service=request.getTargetServiceUniqueName();
  String method=request.getMethodName();
  String protocol=getStringAvoidNull(context.getAttachment(RpcConstants.INTERNAL_KEY_PROTOCOL_NAME));
  String invokeType=request.getInvokeType();
  String targetApp=request.getTargetAppName();
  Long requestSize=getLongAvoidNull(context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE));
  Long responseSize=getLongAvoidNull(context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE));
  Long elapsedTime=getLongAvoidNull(context.getAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE));
  Boolean success=response != null && !response.isError() && response.getErrorMsg() == null && (!(response.getAppResponse() instanceof Throwable));
  clientMetricsModel.setApp(app);
  clientMetricsModel.setService(service);
  clientMetricsModel.setMethod(method);
  clientMetricsModel.setProtocol(protocol);
  clientMetricsModel.setInvokeType(invokeType);
  clientMetricsModel.setTargetApp(targetApp);
  clientMetricsModel.setRequestSize(requestSize);
  clientMetricsModel.setResponseSize(responseSize);
  clientMetricsModel.setElapsedTime(elapsedTime);
  clientMetricsModel.setSuccess(success);
  return clientMetricsModel;
}
