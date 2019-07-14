private String getUniqueName(ProviderConfig providerConfig){
  String uniqueId=providerConfig.getUniqueId();
  return providerConfig.getInterfaceId() + (StringUtils.isEmpty(uniqueId) ? StringUtils.EMPTY : ":" + uniqueId);
}
