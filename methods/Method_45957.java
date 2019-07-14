/** 
 * Subscribe provider list from direct url
 * @param directUrl direct url of consume config
 * @return Provider group list
 */
protected List<ProviderGroup> subscribeFromDirectUrl(String directUrl){
  List<ProviderGroup> result=new ArrayList<ProviderGroup>();
  List<ProviderInfo> tmpProviderInfoList=new ArrayList<ProviderInfo>();
  String[] providerStrs=StringUtils.splitWithCommaOrSemicolon(directUrl);
  for (  String providerStr : providerStrs) {
    ProviderInfo providerInfo=convertToProviderInfo(providerStr);
    if (providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_SOURCE) == null) {
      providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_SOURCE,"direct");
    }
    tmpProviderInfoList.add(providerInfo);
  }
  result.add(new ProviderGroup(RpcConstants.ADDRESS_DIRECT_GROUP,tmpProviderInfoList));
  return result;
}
