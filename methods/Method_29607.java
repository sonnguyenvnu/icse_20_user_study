@Override public List<MachineStats> getAppMachineDetail(Long appId){
  Assert.isTrue(appId != null && appId > 0L);
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    logger.error("appDesc:id={} is not exist");
    return Collections.emptyList();
  }
  List<InstanceInfo> appInstanceList=getAppInstanceInfo(appId);
  if (CollectionUtils.isEmpty(appInstanceList)) {
    return Collections.emptyList();
  }
  Set<String> instanceMachineHosts=new HashSet<String>();
  List<MachineStats> machineDetailVOList=new ArrayList<MachineStats>();
  for (  InstanceInfo instanceInfo : appInstanceList) {
    String ip=instanceInfo.getIp();
    if (instanceMachineHosts.contains(ip)) {
      continue;
    }
 else {
      instanceMachineHosts.add(ip);
    }
    MachineStats machineStats=machineStatsDao.getMachineStatsByIp(ip);
    if (machineStats == null) {
      continue;
    }
    int memoryHost=instanceDao.getMemoryByHost(ip);
    machineStats.setMemoryAllocated(memoryHost);
    MachineInfo machineInfo=machineCenter.getMachineInfoByIp(ip);
    if (machineInfo == null) {
      continue;
    }
    if (machineInfo.isOffline()) {
      continue;
    }
    machineStats.setInfo(machineInfo);
    machineDetailVOList.add(machineStats);
  }
  return machineDetailVOList;
}
