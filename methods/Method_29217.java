@Override public List<MachineStats> getMachineStats(String ipLike){
  List<MachineInfo> machineInfoList=machineDao.getMachineInfoByLikeIp(ipLike);
  List<MachineStats> machineStatsList=new ArrayList<MachineStats>();
  for (  MachineInfo machineInfo : machineInfoList) {
    String ip=machineInfo.getIp();
    MachineStats machineStats=machineStatsDao.getMachineStatsByIp(ip);
    if (machineStats == null) {
      machineStats=new MachineStats();
    }
    machineStats.setMemoryAllocated(instanceDao.getMemoryByHost(ip));
    machineStats.setInfo(machineInfo);
    machineStatsList.add(machineStats);
  }
  return machineStatsList;
}
