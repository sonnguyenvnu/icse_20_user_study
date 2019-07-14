@Override public String executeCommand(Long instanceId,String command){
  InstanceInfo instanceInfo=getInstanceInfo(instanceId);
  return executeCommand(instanceInfo.getIp(),instanceInfo.getPort(),command);
}
