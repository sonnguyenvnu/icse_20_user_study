@Override public Collection<ServerBriefInfo> getAllServersBriefInfo(){
  ConcurrentHashMap<String,ServerBriefInfo> servers=new ConcurrentHashMap<>();
  for (  String jobName : regCenter.getChildrenKeys("/")) {
    JobNodePath jobNodePath=new JobNodePath(jobName);
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getServerNodePath())) {
      servers.putIfAbsent(each,new ServerBriefInfo(each));
      ServerBriefInfo serverInfo=servers.get(each);
      if ("DISABLED".equalsIgnoreCase(regCenter.get(jobNodePath.getServerNodePath(each)))) {
        serverInfo.getDisabledJobsNum().incrementAndGet();
      }
      serverInfo.getJobNames().add(jobName);
      serverInfo.setJobsNum(serverInfo.getJobNames().size());
    }
    List<String> instances=regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath());
    for (    String each : instances) {
      String serverIp=each.split("@-@")[0];
      ServerBriefInfo serverInfo=servers.get(serverIp);
      if (null != serverInfo) {
        serverInfo.getInstances().add(each);
        serverInfo.setInstancesNum(serverInfo.getInstances().size());
      }
    }
  }
  List<ServerBriefInfo> result=new ArrayList<>(servers.values());
  Collections.sort(result);
  return result;
}
