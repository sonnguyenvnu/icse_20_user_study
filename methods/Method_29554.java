/** 
 * ?????????
 * @param appId
 * @param model
 */
protected void fillAppMachineInstanceTopology(Long appId,Model model){
  List<InstanceInfo> instanceList=appService.getAppInstanceInfo(appId);
  int groupId=1;
  for (int i=0; i < instanceList.size(); i++) {
    InstanceInfo instance=instanceList.get(i);
    if (instance.getGroupId() > 0) {
      continue;
    }
    if (instance.isOffline()) {
      continue;
    }
    for (int j=i + 1; j < instanceList.size(); j++) {
      InstanceInfo instanceCompare=instanceList.get(j);
      if (instanceCompare.isOffline()) {
        continue;
      }
      if (instanceCompare.getMasterInstanceId() == instance.getId() || instance.getMasterInstanceId() == instanceCompare.getId()) {
        instanceCompare.setGroupId(groupId);
      }
    }
    instance.setGroupId(groupId++);
  }
  Map<String,List<InstanceInfo>> machineInstanceMap=new HashMap<String,List<InstanceInfo>>();
  for (  InstanceInfo instance : instanceList) {
    String ip=instance.getIp();
    if (machineInstanceMap.containsKey(ip)) {
      machineInstanceMap.get(ip).add(instance);
    }
 else {
      List<InstanceInfo> tempInstanceList=new ArrayList<InstanceInfo>();
      tempInstanceList.add(instance);
      machineInstanceMap.put(ip,tempInstanceList);
    }
  }
  model.addAttribute("machineInstanceMap",machineInstanceMap);
  model.addAttribute("instancePairCount",groupId - 1);
}
