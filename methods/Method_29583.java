/** 
 * ??????
 * @param appAudit
 * @param model
 */
protected void fillInstanceModel(List<InstanceInfo> instanceList,List<InstanceStats> appInstanceStats,Model model){
  Map<String,MachineStats> machineStatsMap=new HashMap<String,MachineStats>();
  Map<String,Long> machineCanUseMem=new HashMap<String,Long>();
  Map<String,InstanceStats> instanceStatsMap=new HashMap<String,InstanceStats>();
  Map<Long,AppDesc> appInfoMap=new HashMap<Long,AppDesc>();
  for (  InstanceStats instanceStats : appInstanceStats) {
    instanceStatsMap.put(instanceStats.getIp() + ":" + instanceStats.getPort(),instanceStats);
    appInfoMap.put(instanceStats.getAppId(),appService.getByAppId(instanceStats.getAppId()));
  }
  for (  InstanceInfo instanceInfo : instanceList) {
    if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
      continue;
    }
    String ip=instanceInfo.getIp();
    if (machineStatsMap.containsKey(ip)) {
      continue;
    }
    List<MachineStats> machineStatsList=machineCenter.getMachineStats(ip);
    MachineStats machineStats=null;
    for (    MachineStats stats : machineStatsList) {
      if (stats.getIp().equals(ip)) {
        machineStats=stats;
        machineStatsMap.put(ip,machineStats);
        break;
      }
    }
    MachineStats ms=machineCenter.getMachineMemoryDetail(ip);
    machineCanUseMem.put(ip,ms.getMachineMemInfo().getLockedMem());
  }
  model.addAttribute("appInfoMap",appInfoMap);
  model.addAttribute("machineCanUseMem",machineCanUseMem);
  model.addAttribute("machineStatsMap",machineStatsMap);
  model.addAttribute("instanceList",instanceList);
  model.addAttribute("instanceStatsMap",instanceStatsMap);
}
