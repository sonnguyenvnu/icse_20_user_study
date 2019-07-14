public static List<ProviderInfo> matchProviderInfos(ConsumerConfig consumerConfig,List<ProviderInfo> providerInfos){
  String protocol=consumerConfig.getProtocol();
  List<ProviderInfo> result=new ArrayList<ProviderInfo>();
  for (  ProviderInfo providerInfo : providerInfos) {
    if (providerInfo.getProtocolType().equalsIgnoreCase(protocol) && StringUtils.equals(consumerConfig.getUniqueId(),providerInfo.getAttr(ProviderInfoAttrs.ATTR_UNIQUEID))) {
      result.add(providerInfo);
    }
  }
  return result;
}
