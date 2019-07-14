@Override protected Map<String,List<InstanceInfo>> getSplitMap(){
  List<InstanceInfo> list=getAllInstanceList();
  Map<String,List<InstanceInfo>> hostMap=new TreeMap<String,List<InstanceInfo>>();
  for (  InstanceInfo instanceInfo : list) {
    String host=instanceInfo.getIp();
    if (hostMap.containsKey(host)) {
      hostMap.get(host).add(instanceInfo);
    }
 else {
      List<InstanceInfo> hostInstances=new ArrayList<InstanceInfo>();
      hostInstances.add(instanceInfo);
      hostMap.put(host,hostInstances);
    }
  }
  return hostMap;
}
