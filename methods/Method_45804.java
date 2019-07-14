@Override public SofaResponse doInvoke(SofaRequest request) throws SofaRpcException {
  String methodName=request.getMethodName();
  int retries=consumerConfig.getMethodRetries(methodName);
  int time=0;
  SofaRpcException throwable=null;
  List<ProviderInfo> invokedProviderInfos=new ArrayList<ProviderInfo>(retries + 1);
  do {
    ProviderInfo providerInfo=select(request,invokedProviderInfos);
    try {
      SofaResponse response=filterChain(providerInfo,request);
      if (response != null) {
        if (throwable != null) {
          if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
            LOGGER.warnWithApp(consumerConfig.getAppName(),LogCodes.getLog(LogCodes.WARN_SUCCESS_BY_RETRY,throwable.getClass() + ":" + throwable.getMessage(),invokedProviderInfos));
          }
        }
        return response;
      }
 else {
        throwable=new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Failed to call " + request.getInterfaceName() + "." + methodName + " on remote server " + providerInfo + ", return null");
        time++;
      }
    }
 catch (    SofaRpcException e) {
      if (e.getErrorType() == RpcErrorType.SERVER_BUSY || e.getErrorType() == RpcErrorType.CLIENT_TIMEOUT) {
        throwable=e;
        time++;
      }
 else {
        throw e;
      }
    }
catch (    Exception e) {
      throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Failed to call " + request.getInterfaceName() + "." + request.getMethodName() + " on remote server: " + providerInfo + ", cause by unknown exception: " + e.getClass().getName() + ", message is: " + e.getMessage(),e);
    }
 finally {
      if (RpcInternalContext.isAttachmentEnable()) {
        RpcInternalContext.getContext().setAttachment(RpcConstants.INTERNAL_KEY_INVOKE_TIMES,time + 1);
      }
    }
    invokedProviderInfos.add(providerInfo);
  }
 while (time <= retries);
  throw throwable;
}
