/** 
 * ???????????????
 * @param machineInfo
 * @return
 */
@Override public boolean removeMachine(MachineInfo machineInfo){
  if (machineInfo == null || Strings.isNullOrEmpty(machineInfo.getIp())) {
    logger.warn("machineInfo is null or ip is empty.");
    return false;
  }
  String machineIp=machineInfo.getIp();
  try {
    MachineInfo thisMachine=machineDao.getMachineInfoByIp(machineIp);
    long hostId=thisMachine.getId();
    if (!machineCenter.unDeployMachineCollection(hostId,machineIp)) {
      logger.error("remove trigger for machine error: {}",thisMachine.toString());
      return false;
    }
    if (!machineCenter.unDeployMachineMonitor(hostId,machineIp)) {
      logger.error("remove trigger for machine monitor error: {}",thisMachine.toString());
      return false;
    }
    if (!machineCenter.unDeployServerCollection(hostId,machineIp)) {
      logger.error("remove trigger for server monitor error: {}",thisMachine.toString());
      return false;
    }
  }
 catch (  Exception e) {
    logger.error("query machineInfo from db error: {}",machineInfo.toString());
  }
  try {
    machineDao.removeMachineInfoByIp(machineIp);
    machineStatsDao.deleteMachineStatsByIp(machineIp);
    serverStatusDao.deleteServerInfo(machineIp);
  }
 catch (  Exception e) {
    logger.error("remove machineInfo from db error, machineInfo: {}",machineInfo.toString(),e);
    return false;
  }
  logger.info("remove and undeploy machine ok: {}",machineInfo.toString());
  return true;
}
