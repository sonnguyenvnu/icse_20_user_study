private void notifyToListener(ProviderInfoListener listener,ComposeUserData mergedResult){
  if ("".equalsIgnoreCase(mergedResult.getLocalZone()) || DEFAULT_ZONE.equalsIgnoreCase(mergedResult.getLocalZone())) {
    listener.updateProviders(new ProviderGroup(flatComposeData(mergedResult)));
  }
 else {
    final Map<String,List<ProviderInfo>> zoneData=mergedResult.getZoneData();
    List<ProviderGroup> result=new ArrayList<ProviderGroup>();
    for (    Map.Entry<String,List<ProviderInfo>> dataEntry : zoneData.entrySet()) {
      if (dataEntry.getKey().equalsIgnoreCase(mergedResult.getLocalZone())) {
        result.add(new ProviderGroup(dataEntry.getValue()));
      }
      result.add(new ProviderGroup(dataEntry.getKey(),dataEntry.getValue()));
    }
    listener.updateAllProviders(result);
  }
}
