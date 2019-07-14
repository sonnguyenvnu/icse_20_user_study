/** 
 * ?????trigger??????????????????????
 */
public void initAsync(){
  List<MachineInfo> machineInfoList=machineDao.getAllMachines();
  for (  MachineInfo machineInfo : machineInfoList) {
    long hostId=machineInfo.getId();
    String ip=machineInfo.getIp();
    Assert.hasText(ip);
    Assert.isTrue(hostId > 0);
    machineCenter.deployMachineCollection(hostId,ip);
    machineCenter.deployMachineMonitor(hostId,ip);
    if (machineInfo.getCollect() == 1) {
      machineCenter.deployServerCollection(hostId,ip);
    }
  }
  logger.info("init deploy all host and monitor done.");
}
