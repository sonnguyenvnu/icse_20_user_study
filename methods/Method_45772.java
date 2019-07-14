/** 
 * Select provider.
 * @param targetIP the target ip
 * @return the provider
 */
protected ProviderInfo selectPinpointProvider(String targetIP,List<ProviderInfo> providerInfos){
  ProviderInfo tp=ProviderHelper.toProviderInfo(targetIP);
  for (  ProviderInfo providerInfo : providerInfos) {
    if (providerInfo.getHost().equals(tp.getHost()) && StringUtils.equals(providerInfo.getProtocolType(),tp.getProtocolType()) && providerInfo.getPort() == tp.getPort()) {
      return providerInfo;
    }
  }
  return null;
}
