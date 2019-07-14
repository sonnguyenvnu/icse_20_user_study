private boolean runSentinelGroup(AppDesc appDesc,List<String> sentinelList,String masterHost,int masterPort,long appId,String password){
  for (  String sentinelHost : sentinelList) {
    boolean isRun=runSentinel(appDesc,sentinelHost,getMasterName(masterHost,masterPort),masterHost,masterPort);
    if (!isRun) {
      return false;
    }
  }
  return true;
}
