private boolean deployStandalone(long appId,String[] nodeInfo){
  String host=nodeInfo[0];
  int memory=NumberUtils.createInteger(nodeInfo[1]);
  return redisDeployCenter.deployStandaloneInstance(appId,host,memory);
}
