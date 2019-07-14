@Override public SofaResponse invoke(SofaRequest sofaRequest) throws SofaRpcException {
  ProviderInfo providerInfo=RpcInternalContext.getContext().getProviderInfo();
  String appName=providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_APP_NAME);
  if (StringUtils.isNotEmpty(appName)) {
    sofaRequest.setTargetAppName(appName);
  }
  return consumerBootstrap.getCluster().sendMsg(providerInfo,sofaRequest);
}
