@Override protected Map<String,List<InstanceInfo>> getSplitMap(){
  List<InstanceInfo> list=getAllInstanceList();
  Map<String,List<InstanceInfo>> hostMap=new TreeMap<String,List<InstanceInfo>>();
  for (  InstanceInfo instanceInfo : list) {
    String appId=String.valueOf(instanceInfo.getAppId());
    if (hostMap.containsKey(appId)) {
      hostMap.get(appId).add(instanceInfo);
    }
 else {
      List<InstanceInfo> hostInstances=new ArrayList<InstanceInfo>();
      hostInstances.add(instanceInfo);
      hostMap.put(appId,hostInstances);
    }
  }
  return hostMap;
}
