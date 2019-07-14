@Override public PropsData clone(){
  final HashMap<String,Map<String,PropsEntry>> newProfiles=new HashMap<>();
  final HashMap<String,PropsEntry> newBase=new HashMap<>(baseProperties);
  for (  final Map.Entry<String,Map<String,PropsEntry>> entry : profileProperties.entrySet()) {
    final Map<String,PropsEntry> map=new HashMap<>(entry.getValue().size());
    map.putAll(entry.getValue());
    newProfiles.put(entry.getKey(),map);
  }
  final PropsData pd=new PropsData(newBase,newProfiles);
  pd.appendDuplicateProps=appendDuplicateProps;
  pd.ignoreMissingMacros=ignoreMissingMacros;
  pd.skipEmptyProps=skipEmptyProps;
  return pd;
}
