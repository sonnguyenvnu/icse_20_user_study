/** 
 * ??????
 * @param appId
 * @param model
 */
protected void fillAppInstanceStats(Long appId,Model model){
  List<InstanceInfo> instanceList=appService.getAppInstanceInfo(appId);
  model.addAttribute("instanceList",instanceList);
  Map<Integer,InstanceInfo> instanceInfoMap=new HashMap<Integer,InstanceInfo>();
  for (  InstanceInfo instanceInfo : instanceList) {
    instanceInfoMap.put(instanceInfo.getId(),instanceInfo);
  }
  model.addAttribute("instanceInfoMap",instanceInfoMap);
  List<InstanceStats> appInstanceStats=appService.getAppInstanceStats(appId);
  Map<String,InstanceStats> instanceStatsMap=new HashMap<String,InstanceStats>();
  for (  InstanceStats instanceStats : appInstanceStats) {
    instanceStatsMap.put(instanceStats.getIp() + ":" + instanceStats.getPort(),instanceStats);
  }
  model.addAttribute("instanceStatsMap",instanceStatsMap);
  Map<String,InstanceSlotModel> clusterSlotsMap=redisCenter.getClusterSlotsMap(appId);
  model.addAttribute("clusterSlotsMap",clusterSlotsMap);
}
