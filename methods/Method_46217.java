@Override public void onException(Throwable e){
  if (callback == null) {
    return;
  }
  ClassLoader cl=Thread.currentThread().getContextClassLoader();
  try {
    Thread.currentThread().setContextClassLoader(this.classLoader);
    RpcInternalContext.setContext(context);
    if (EventBus.isEnable(ClientAsyncReceiveEvent.class)) {
      EventBus.post(new ClientAsyncReceiveEvent(consumerConfig,providerInfo,request,null,e));
    }
    FilterChain chain=consumerConfig.getConsumerBootstrap().getCluster().getFilterChain();
    if (chain != null) {
      chain.onAsyncResponse(consumerConfig,request,null,e);
    }
    recordClientElapseTime();
    if (EventBus.isEnable(ClientEndInvokeEvent.class)) {
      EventBus.post(new ClientEndInvokeEvent(request,null,e));
    }
    SofaRpcException sofaRpcException=null;
    if (e instanceof InvokeTimeoutException) {
      sofaRpcException=new SofaTimeOutException(e);
    }
 else {
      sofaRpcException=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,e.getMessage(),e);
    }
    callback.onSofaException(sofaRpcException,request.getMethodName(),request);
  }
  finally {
    Thread.currentThread().setContextClassLoader(cl);
    RpcInvokeContext.removeContext();
    RpcInternalContext.removeAllContext();
  }
}
