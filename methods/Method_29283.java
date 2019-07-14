private InstanceInfo saveInstance(long appId,String host,int port,int maxMemory,int type,String cmd){
  InstanceInfo instanceInfo=new InstanceInfo();
  instanceInfo.setAppId(appId);
  MachineInfo machineInfo=machineDao.getMachineInfoByIp(host);
  instanceInfo.setHostId(machineInfo.getId());
  instanceInfo.setConn(0);
  instanceInfo.setMem(maxMemory);
  instanceInfo.setStatus(InstanceStatusEnum.GOOD_STATUS.getStatus());
  instanceInfo.setPort(port);
  instanceInfo.setType(type);
  instanceInfo.setCmd(cmd);
  instanceInfo.setIp(host);
  instanceDao.saveInstance(instanceInfo);
  return instanceInfo;
}
