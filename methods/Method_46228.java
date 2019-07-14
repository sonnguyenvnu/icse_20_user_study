@Override public void handleRequest(BizContext bizCtx,AsyncContext asyncCtx,SofaRequest request){
  RpcInternalContext context=RpcInternalContext.getContext();
  context.setProviderSide(true);
  String appName=request.getTargetAppName();
  if (appName == null) {
    appName=(String)RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPNAME);
  }
  boolean isAsyncChain=false;
  try {
    processingCount.incrementAndGet();
    context.setRemoteAddress(bizCtx.getRemoteHost(),bizCtx.getRemotePort());
    context.setAttachment(RpcConstants.HIDDEN_KEY_ASYNC_CONTEXT,asyncCtx);
    if (RpcInternalContext.isAttachmentEnable()) {
      InvokeContext boltInvokeCtx=bizCtx.getInvokeContext();
      if (boltInvokeCtx != null) {
        putToContextIfNotNull(boltInvokeCtx,InvokeContext.BOLT_PROCESS_WAIT_TIME,context,RpcConstants.INTERNAL_KEY_PROCESS_WAIT_TIME);
      }
    }
    if (EventBus.isEnable(ServerReceiveEvent.class)) {
      EventBus.post(new ServerReceiveEvent(request));
    }
    SofaResponse response=null;
    Throwable throwable=null;
    ProviderConfig providerConfig=null;
    String serviceName=request.getTargetServiceUniqueName();
    try {
      invoke: {
        if (!boltServer.isStarted()) {
          throwable=new SofaRpcException(RpcErrorType.SERVER_CLOSED,LogCodes.getLog(LogCodes.WARN_PROVIDER_STOPPED,SystemInfo.getLocalHost() + ":" + boltServer.serverConfig.getPort()));
          response=MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
          break invoke;
        }
        if (bizCtx.isRequestTimeout()) {
          throwable=clientTimeoutWhenReceiveRequest(appName,serviceName,bizCtx.getRemoteAddress());
          break invoke;
        }
        Invoker invoker=boltServer.findInvoker(serviceName);
        if (invoker == null) {
          throwable=cannotFoundService(appName,serviceName);
          response=MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
          break invoke;
        }
        if (invoker instanceof ProviderProxyInvoker) {
          providerConfig=((ProviderProxyInvoker)invoker).getProviderConfig();
          appName=providerConfig != null ? providerConfig.getAppName() : null;
        }
        String methodName=request.getMethodName();
        Method serviceMethod=ReflectCache.getOverloadMethodCache(serviceName,methodName,request.getMethodArgSigs());
        if (serviceMethod == null) {
          throwable=cannotFoundServiceMethod(appName,methodName,serviceName);
          response=MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
          break invoke;
        }
 else {
          request.setMethod(serviceMethod);
        }
        response=doInvoke(serviceName,invoker,request);
        if (bizCtx.isRequestTimeout()) {
          throwable=clientTimeoutWhenSendResponse(appName,serviceName,bizCtx.getRemoteAddress());
          break invoke;
        }
      }
    }
 catch (    Exception e) {
      LOGGER.errorWithApp(appName,"Server Processor Error!",e);
      throwable=e;
      response=MessageBuilder.buildSofaErrorResponse(e.getMessage());
    }
    if (response != null) {
      RpcInvokeContext invokeContext=RpcInvokeContext.peekContext();
      isAsyncChain=CommonUtils.isTrue(invokeContext != null ? (Boolean)invokeContext.remove(RemotingConstants.INVOKE_CTX_IS_ASYNC_CHAIN) : null);
      if (!isAsyncChain) {
        try {
          asyncCtx.sendResponse(response);
        }
  finally {
          if (EventBus.isEnable(ServerSendEvent.class)) {
            EventBus.post(new ServerSendEvent(request,response,throwable));
          }
        }
      }
    }
  }
 catch (  Throwable e) {
    if (LOGGER.isErrorEnabled(appName)) {
      LOGGER.errorWithApp(appName,e.getMessage(),e);
    }
  }
 finally {
    processingCount.decrementAndGet();
    if (!isAsyncChain) {
      if (EventBus.isEnable(ServerEndHandleEvent.class)) {
        EventBus.post(new ServerEndHandleEvent());
      }
    }
    RpcInvokeContext.removeContext();
    RpcInternalContext.removeAllContext();
  }
}
