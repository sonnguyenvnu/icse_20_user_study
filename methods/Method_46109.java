private static Map<String,ProviderGroup> unMarshal(String context){
  if (StringUtils.isBlank(context)) {
    return null;
  }
  Map<String,ProviderGroup> map=new HashMap<String,ProviderGroup>();
  String[] lines=StringUtils.split(context,FileUtils.LINE_SEPARATOR);
  for (  String line : lines) {
    String[] fields=line.split(SEPARATORSTR);
    if (fields.length > 1) {
      String key=fields[0];
      Set<ProviderInfo> values=new HashSet<ProviderInfo>();
      for (int i=1; i < fields.length; i++) {
        String pstr=fields[i];
        if (StringUtils.isNotEmpty(pstr)) {
          ProviderInfo providerInfo=ProviderHelper.toProviderInfo(pstr);
          providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_SOURCE,"local");
          values.add(providerInfo);
        }
      }
      map.put(key,new ProviderGroup(new ArrayList<ProviderInfo>(values)));
    }
  }
  return map;
}
