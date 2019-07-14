private static String marshalCache(Map<String,ProviderGroup> memoryCache){
  StringBuilder sb=new StringBuilder();
  for (  Map.Entry<String,ProviderGroup> entry : memoryCache.entrySet()) {
    ProviderGroup group=entry.getValue();
    if (group != null) {
      List<ProviderInfo> ps=group.getProviderInfos();
      if (CommonUtils.isNotEmpty(ps)) {
        sb.append(entry.getKey()).append(SEPARATORSTR);
        for (        ProviderInfo providerInfo : ps) {
          sb.append(ProviderHelper.toUrl(providerInfo)).append(SEPARATORSTR);
        }
        sb.append(FileUtils.LINE_SEPARATOR);
      }
    }
  }
  return sb.toString();
}
