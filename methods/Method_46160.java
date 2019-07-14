protected List<ProviderInfo> flatComposeData(ComposeUserData userData){
  List<ProviderInfo> result=new ArrayList<ProviderInfo>();
  Map<String,List<ProviderInfo>> zoneData=userData.getZoneData();
  for (  Map.Entry<String,List<ProviderInfo>> entry : zoneData.entrySet()) {
    result.addAll(entry.getValue());
  }
  return result;
}
