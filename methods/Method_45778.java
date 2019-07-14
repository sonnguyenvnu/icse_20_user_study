/** 
 * ?????
 * @param transport ?????
 * @param request   Request??
 * @return ????
 * @throws SofaRpcException rpc??
 */
protected SofaResponse doSendMsg(ProviderInfo providerInfo,ClientTransport transport,SofaRequest request) throws SofaRpcException {
  RpcInternalContext context=RpcInternalContext.getContext();
  RpcInternalContext.getContext().setRemoteAddress(providerInfo.getHost(),providerInfo.getPort());
  try {
    checkProviderVersion(providerInfo,request);
    String invokeType=request.getInvokeType();
    int timeout=resolveTimeout(request,consumerConfig,providerInfo);
    SofaResponse response=null;
    if (RpcConstants.INVOKER_TYPE_SYNC.equals(invokeType)) {
      long start=RpcRuntimeContext.now();
      try {
        response=transport.syncSend(request,timeout);
      }
  finally {
        if (RpcInternalContext.isAttachmentEnable()) {
          long elapsed=RpcRuntimeContext.now() - start;
          context.setAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE,elapsed);
        }
      }
    }
 else     if (RpcConstants.INVOKER_TYPE_ONEWAY.equals(invokeType)) {
      long start=RpcRuntimeContext.now();
      try {
        transport.oneWaySend(request,timeout);
        response=buildEmptyResponse(request);
      }
  finally {
        if (RpcInternalContext.isAttachmentEnable()) {
          long elapsed=RpcRuntimeContext.now() - start;
          context.setAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE,elapsed);
        }
      }
    }
 else     if (RpcConstants.INVOKER_TYPE_CALLBACK.equals(invokeType)) {
      SofaResponseCallback sofaResponseCallback=request.getSofaResponseCallback();
      if (sofaResponseCallback == null) {
        SofaResponseCallback methodResponseCallback=consumerConfig.getMethodOnreturn(request.getMethodName());
        if (methodResponseCallback != null) {
          request.setSofaResponseCallback(methodResponseCallback);
        }
      }
      context.setAttachment(RpcConstants.INTERNAL_KEY_CLIENT_SEND_TIME,RpcRuntimeContext.now());
      transport.asyncSend(request,timeout);
      response=buildEmptyResponse(request);
    }
 else     if (RpcConstants.INVOKER_TYPE_FUTURE.equals(invokeType)) {
      context.setAttachment(RpcConstants.INTERNAL_KEY_CLIENT_SEND_TIME,RpcRuntimeContext.now());
      ResponseFuture future=transport.asyncSend(request,timeout);
      RpcInternalContext.getContext().setFuture(future);
      response=buildEmptyResponse(request);
    }
 else {
      throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Unknown invoke type:" + invokeType);
    }
    return response;
  }
 catch (  SofaRpcException e) {
    throw e;
  }
catch (  Throwable e) {
    throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,e);
  }
}
