private void persistDisabledOrEnabledJob(final String jobName,final String serverIp,final boolean disabled){
  JobNodePath jobNodePath=new JobNodePath(jobName);
  String serverNodePath=jobNodePath.getServerNodePath(serverIp);
  if (disabled) {
    regCenter.persist(serverNodePath,"DISABLED");
  }
 else {
    regCenter.persist(serverNodePath,"");
  }
}
