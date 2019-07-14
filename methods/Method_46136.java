/** 
 * Convert instances to providers list.
 * @param allInstances the all instances 
 * @return the list
 */
static List<ProviderInfo> convertInstancesToProviders(List<Instance> allInstances){
  List<ProviderInfo> providerInfos=new ArrayList<ProviderInfo>();
  if (CommonUtils.isEmpty(allInstances)) {
    return providerInfos;
  }
  for (  Instance instance : allInstances) {
    String url=convertInstanceToUrl(instance);
    ProviderInfo providerInfo=ProviderHelper.toProviderInfo(url);
    providerInfos.add(providerInfo);
  }
  return providerInfos;
}
