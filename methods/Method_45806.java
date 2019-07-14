@Override public ProviderInfo doSelect(SofaRequest invocation,List<ProviderInfo> providerInfos){
  String localhost=SystemInfo.getLocalHost();
  if (StringUtils.isEmpty(localhost)) {
    return super.doSelect(invocation,providerInfos);
  }
  List<ProviderInfo> localProviderInfo=new ArrayList<ProviderInfo>();
  for (  ProviderInfo providerInfo : providerInfos) {
    if (localhost.equals(providerInfo.getHost())) {
      localProviderInfo.add(providerInfo);
    }
  }
  if (CommonUtils.isNotEmpty(localProviderInfo)) {
    return super.doSelect(invocation,localProviderInfo);
  }
 else {
    return super.doSelect(invocation,providerInfos);
  }
}
