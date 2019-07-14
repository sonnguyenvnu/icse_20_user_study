private boolean deploySentinel(long appId,List<String[]> nodes){
  String[] dataNodeInfo=nodes.get(0);
  String master=dataNodeInfo[0];
  int memory=NumberUtils.createInteger(dataNodeInfo[1]);
  String slave=dataNodeInfo[2];
  List<String> sentinelList=new ArrayList<String>();
  if (nodes.size() < 2) {
    logger.error("sentinelList is none,don't generate sentinel app!");
    return false;
  }
  for (int i=1; i < nodes.size(); i++) {
    String[] nodeInfo=nodes.get(i);
    if (nodeInfo.length == 0 || StringUtils.isBlank(nodeInfo[0])) {
      logger.error("sentinel line {} may be empty",i);
      return false;
    }
    sentinelList.add(nodeInfo[0]);
  }
  return redisDeployCenter.deploySentinelInstance(appId,master,slave,memory,sentinelList);
}
