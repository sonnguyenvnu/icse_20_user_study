@Override public SofaResponse sendMsg(ProviderInfo providerInfo,SofaRequest request) throws SofaRpcException {
  ClientTransport clientTransport=connectionHolder.getAvailableClientTransport(providerInfo);
  if (clientTransport != null && clientTransport.isAvailable()) {
    return doSendMsg(providerInfo,clientTransport,request);
  }
 else {
    throw unavailableProviderException(request.getTargetServiceUniqueName(),providerInfo.getOriginUrl());
  }
}
