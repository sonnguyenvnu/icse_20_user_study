@Override public int getServersTotalCount(){
  Set<String> servers=new HashSet<>();
  for (  String jobName : regCenter.getChildrenKeys("/")) {
    JobNodePath jobNodePath=new JobNodePath(jobName);
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getServerNodePath())) {
      servers.add(each);
    }
  }
  return servers.size();
}
