/** 
 * merge url
 * @param userDatas
 * @param configDatas
 * @return
 */
List<ProviderInfo> mergeProviderInfo(List<String> userDatas,List<String> configDatas){
  List<ProviderInfo> providers=SofaRegistryHelper.parseProviderInfos(userDatas);
  if (CommonUtils.isNotEmpty(providers) && CommonUtils.isNotEmpty(configDatas)) {
    List<ProviderInfo> override=SofaRegistryHelper.parseProviderInfos(configDatas);
    Iterator<ProviderInfo> iterator=providers.iterator();
    while (iterator.hasNext()) {
      ProviderInfo origin=iterator.next();
      for (      ProviderInfo over : override) {
        if (PROTOCOL_TYPE_OVERRIDE.equals(over.getProtocolType()) && StringUtils.equals(origin.getHost(),over.getHost()) && origin.getPort() == over.getPort()) {
          if (over.getWeight() != origin.getWeight()) {
            origin.setWeight(over.getWeight());
          }
          if (CommonUtils.isTrue(over.getAttr(ProviderInfoAttrs.ATTR_DISABLED))) {
            if (LOGGER.isInfoEnabled()) {
              LOGGER.info("Provider is disabled by override. {}",origin.toUrl());
            }
            iterator.remove();
          }
        }
      }
    }
  }
  return providers;
}
