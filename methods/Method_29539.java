/** 
 * ??????
 * @param appId
 * @param model
 */
private void fillAppMachineStat(Long appId,Model model){
  List<InstanceInfo> instanceList=appService.getAppInstanceInfo(appId);
  Map<String,MachineStats> machineStatsMap=new HashMap<String,MachineStats>();
  Map<String,Long> machineCanUseMem=new HashMap<String,Long>();
  for (  InstanceInfo instanceInfo : instanceList) {
    if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
      continue;
    }
    String ip=instanceInfo.getIp();
    if (machineStatsMap.containsKey(ip)) {
      continue;
    }
    MachineStats machineStats=machineCenter.getMachineMemoryDetail(ip);
    machineStatsMap.put(ip,machineStats);
    machineCanUseMem.put(ip,machineStats.getMachineMemInfo().getLockedMem());
  }
  model.addAttribute("machineCanUseMem",machineCanUseMem);
  model.addAttribute("machineStatsMap",machineStatsMap);
}
