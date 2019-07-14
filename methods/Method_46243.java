@Override public void oneWaySend(SofaRequest request,int timeout) throws SofaRpcException {
  checkConnection();
  RpcInternalContext context=RpcInternalContext.getContext();
  InvokeContext invokeContext=createInvokeContext(request);
  SofaRpcException throwable=null;
  try {
    beforeSend(context,request);
    doOneWay(request,invokeContext,timeout);
  }
 catch (  Exception e) {
    throwable=convertToRpcException(e);
    throw throwable;
  }
 finally {
    afterSend(context,invokeContext,request);
    if (EventBus.isEnable(ClientSyncReceiveEvent.class)) {
      EventBus.post(new ClientSyncReceiveEvent(transportConfig.getConsumerConfig(),transportConfig.getProviderInfo(),request,null,throwable));
    }
  }
}
