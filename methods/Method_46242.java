@Override public SofaResponse syncSend(SofaRequest request,int timeout) throws SofaRpcException {
  checkConnection();
  RpcInternalContext context=RpcInternalContext.getContext();
  InvokeContext boltInvokeContext=createInvokeContext(request);
  SofaResponse response=null;
  SofaRpcException throwable=null;
  try {
    beforeSend(context,request);
    response=doInvokeSync(request,boltInvokeContext,timeout);
    return response;
  }
 catch (  Exception e) {
    throwable=convertToRpcException(e);
    throw throwable;
  }
 finally {
    afterSend(context,boltInvokeContext,request);
    if (EventBus.isEnable(ClientSyncReceiveEvent.class)) {
      EventBus.post(new ClientSyncReceiveEvent(transportConfig.getConsumerConfig(),transportConfig.getProviderInfo(),request,response,throwable));
    }
  }
}
