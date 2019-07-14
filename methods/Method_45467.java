private static Map<String,List<ProviderInfo>> convertToMap(List<ProviderGroup> providerGroups){
  Map<String,List<ProviderInfo>> map=new HashMap<String,List<ProviderInfo>>(providerGroups.size());
  for (  ProviderGroup providerGroup : providerGroups) {
    List<ProviderInfo> ps=map.get(providerGroup.getName());
    if (ps == null) {
      ps=new ArrayList<ProviderInfo>();
      map.put(providerGroup.getName(),ps);
    }
    ps.addAll(providerGroup.getProviderInfos());
  }
  return map;
}
