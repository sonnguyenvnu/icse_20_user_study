@Override public List<ProviderInfo> route(SofaRequest request,List<ProviderInfo> providerInfos){
  AddressHolder addressHolder=consumerBootstrap.getCluster().getAddressHolder();
  if (addressHolder != null) {
    List<ProviderInfo> current=addressHolder.getProviderInfos(RpcConstants.ADDRESS_DIRECT_GROUP);
    if (providerInfos != null) {
      providerInfos.addAll(current);
    }
 else {
      providerInfos=current;
    }
  }
  recordRouterWay(RPC_DIRECT_URL_ROUTER);
  return providerInfos;
}
