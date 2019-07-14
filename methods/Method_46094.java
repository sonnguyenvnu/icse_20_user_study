public List<ProviderInfo> currentProviders(){
  return currentData.getValue().stream().map(HealthService::getService).map(service -> ProviderHelper.toProviderInfo(convertInstanceToUrl(service.getAddress(),service.getPort(),service.getMeta()))).collect(Collectors.toList());
}
