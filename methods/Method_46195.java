static ProviderInfo convertUrlToProvider(String providerPath,ChildData childData) throws UnsupportedEncodingException {
  String url=childData.getPath().substring(providerPath.length() + 1);
  url=URLDecoder.decode(url,"UTF-8");
  ProviderInfo providerInfo=ProviderHelper.toProviderInfo(url);
  processWarmUpWeight(providerInfo);
  return providerInfo;
}
