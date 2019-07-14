/** 
 * ????host?????????mysql? ???hostId???????mysql??????ip:hostId
 * @param hostId      ??id
 * @param collectTime ????????yyyyMMddHHmm
 * @param ip          ip
 * @return ???????
 */
@Override public Map<String,Object> collectMachineInfo(final long hostId,final long collectTime,final String ip){
  Map<String,Object> infoMap=new HashMap<String,Object>();
  MachineStats machineStats=null;
  try {
    int sshPort=SSHUtil.getSshPort(ip);
    machineStats=SSHUtil.getMachineInfo(ip,sshPort,ConstUtils.USERNAME,ConstUtils.PASSWORD);
    machineStats.setHostId(hostId);
    if (machineStats != null) {
      infoMap.put(MachineConstant.Ip.getValue(),machineStats.getIp());
      infoMap.put(MachineConstant.CpuUsage.getValue(),machineStats.getCpuUsage());
      infoMap.put(MachineConstant.MemoryUsageRatio.getValue(),machineStats.getMemoryUsageRatio());
      if (machineStats.getMemoryFree() != null) {
        infoMap.put(MachineConstant.MemoryFree.getValue(),Long.valueOf(machineStats.getMemoryFree()) * ConstUtils._1024);
      }
 else {
        infoMap.put(MachineConstant.MemoryFree.getValue(),0);
      }
      infoMap.put(MachineConstant.MemoryTotal.getValue(),Long.valueOf(machineStats.getMemoryTotal()) * ConstUtils._1024);
      infoMap.put(MachineConstant.Load.getValue(),machineStats.getLoad());
      infoMap.put(MachineConstant.Traffic.getValue(),machineStats.getTraffic());
      infoMap.put(MachineConstant.DiskUsage.getValue(),machineStats.getDiskUsageMap());
      infoMap.put(ConstUtils.COLLECT_TIME,collectTime);
      instanceStatsCenter.saveStandardStats(infoMap,new HashMap<String,Object>(0),ip,(int)hostId,ConstUtils.MACHINE);
      machineStats.setMemoryFree(Long.valueOf(machineStats.getMemoryFree()) * ConstUtils._1024 + "");
      machineStats.setMemoryTotal(Long.valueOf(machineStats.getMemoryTotal()) * ConstUtils._1024 + "");
      machineStats.setModifyTime(new Date());
      machineStatsDao.mergeMachineStats(machineStats);
      logger.info("collect machine info done, host: {}, time: {}",ip,collectTime);
    }
  }
 catch (  Exception e) {
    logger.error("collectMachineErrorStats=>" + machineStats);
    logger.error(e.getMessage(),e);
  }
  return infoMap;
}
