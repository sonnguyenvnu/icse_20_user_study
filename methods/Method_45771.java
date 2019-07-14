/** 
 * ??????????
 * @param message              ????
 * @param invokedProviderInfos ?????
 * @return ?????provider
 * @throws SofaRpcException rpc??
 */
protected ProviderInfo select(SofaRequest message,List<ProviderInfo> invokedProviderInfos) throws SofaRpcException {
  if (consumerConfig.isSticky()) {
    if (lastProviderInfo != null) {
      ProviderInfo providerInfo=lastProviderInfo;
      ClientTransport lastTransport=connectionHolder.getAvailableClientTransport(providerInfo);
      if (lastTransport != null && lastTransport.isAvailable()) {
        checkAlias(providerInfo,message);
        return providerInfo;
      }
    }
  }
  List<ProviderInfo> providerInfos=routerChain.route(message,null);
  List<ProviderInfo> orginalProviderInfos=new ArrayList<ProviderInfo>(providerInfos);
  if (CommonUtils.isEmpty(providerInfos)) {
    throw noAvailableProviderException(message.getTargetServiceUniqueName());
  }
  if (CommonUtils.isNotEmpty(invokedProviderInfos) && providerInfos.size() > invokedProviderInfos.size()) {
    providerInfos.removeAll(invokedProviderInfos);
  }
  String targetIP=null;
  ProviderInfo providerInfo;
  RpcInternalContext context=RpcInternalContext.peekContext();
  if (context != null) {
    targetIP=(String)RpcInternalContext.getContext().getAttachment(RpcConstants.HIDDEN_KEY_PINPOINT);
  }
  if (StringUtils.isNotBlank(targetIP)) {
    providerInfo=selectPinpointProvider(targetIP,providerInfos);
    if (providerInfo == null) {
      throw unavailableProviderException(message.getTargetServiceUniqueName(),targetIP);
    }
    ClientTransport clientTransport=selectByProvider(message,providerInfo);
    if (clientTransport == null) {
      throw unavailableProviderException(message.getTargetServiceUniqueName(),targetIP);
    }
    return providerInfo;
  }
 else {
    do {
      providerInfo=loadBalancer.select(message,providerInfos);
      ClientTransport transport=selectByProvider(message,providerInfo);
      if (transport != null) {
        return providerInfo;
      }
      providerInfos.remove(providerInfo);
    }
 while (!providerInfos.isEmpty());
  }
  throw unavailableProviderException(message.getTargetServiceUniqueName(),convertProviders2Urls(orginalProviderInfos));
}
