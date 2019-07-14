/** 
 * ?????Provider????????????????????????
 * @return ???Provider??
 */
public Collection<ProviderInfo> currentProviderList(){
  List<ProviderInfo> providerInfos=new ArrayList<ProviderInfo>();
  List<ProviderGroup> providerGroups=addressHolder.getProviderGroups();
  if (CommonUtils.isNotEmpty(providerGroups)) {
    for (    ProviderGroup entry : providerGroups) {
      providerInfos.addAll(entry.getProviderInfos());
    }
  }
  return providerInfos;
}
