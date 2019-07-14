/** 
 * Convert url to provider list.
 * @param providerPath
 * @param currentData  the current data
 * @return the list
 * @throws UnsupportedEncodingException decode exception
 */
static List<ProviderInfo> convertUrlsToProviders(String providerPath,List<ChildData> currentData) throws UnsupportedEncodingException {
  List<ProviderInfo> providerInfos=new ArrayList<ProviderInfo>();
  if (CommonUtils.isEmpty(currentData)) {
    return providerInfos;
  }
  for (  ChildData childData : currentData) {
    providerInfos.add(convertUrlToProvider(providerPath,childData));
  }
  return providerInfos;
}
