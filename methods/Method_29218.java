@Override public MachineStats getMachineMemoryDetail(String ip){
  long applyMem=0;
  long usedMem=0;
  List<InstanceStats> instanceStats=instanceStatsDao.getInstanceStatsByIp(ip);
  for (  InstanceStats instance : instanceStats) {
    applyMem+=instance.getMaxMemory();
    usedMem+=instance.getUsedMemory();
  }
  MachineStats machineStats=machineStatsDao.getMachineStatsByIp(ip);
  machineStats.setInfo(machineDao.getMachineInfoByIp(ip));
  MachineMemInfo machineMemInfo=new MachineMemInfo();
  machineMemInfo.setIp(ip);
  machineMemInfo.setApplyMem(applyMem);
  machineMemInfo.setUsedMem(usedMem);
  machineStats.setMachineMemInfo(machineMemInfo);
  int memoryHost=instanceDao.getMemoryByHost(ip);
  machineStats.setMemoryAllocated(memoryHost);
  return machineStats;
}
