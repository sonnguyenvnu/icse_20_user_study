/** 
 * ??host????
 * @param host
 * @return
 */
private boolean checkHostExist(String host){
  try {
    MachineInfo machineInfo=machineCenter.getMachineInfoByIp(host);
    if (machineInfo == null) {
      return false;
    }
    if (machineInfo.isOffline()) {
      logger.warn("host {} is offline",host);
      return false;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
  return true;
}
