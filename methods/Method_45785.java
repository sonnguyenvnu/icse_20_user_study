private String convertProviders2Urls(List<ProviderInfo> providerInfos){
  StringBuilder sb=new StringBuilder();
  if (CommonUtils.isNotEmpty(providerInfos)) {
    for (    ProviderInfo providerInfo : providerInfos) {
      sb.append(providerInfo).append(",");
    }
  }
  return sb.toString();
}
