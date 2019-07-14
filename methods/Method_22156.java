private void disableOrEnableJobs(final Optional<String> jobName,final Optional<String> serverIp,final boolean disabled){
  Preconditions.checkArgument(jobName.isPresent() || serverIp.isPresent(),"At least indicate jobName or serverIp.");
  if (jobName.isPresent() && serverIp.isPresent()) {
    persistDisabledOrEnabledJob(jobName.get(),serverIp.get(),disabled);
  }
 else   if (jobName.isPresent()) {
    JobNodePath jobNodePath=new JobNodePath(jobName.get());
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getServerNodePath())) {
      if (disabled) {
        regCenter.persist(jobNodePath.getServerNodePath(each),"DISABLED");
      }
 else {
        regCenter.persist(jobNodePath.getServerNodePath(each),"");
      }
    }
  }
 else   if (serverIp.isPresent()) {
    List<String> jobNames=regCenter.getChildrenKeys("/");
    for (    String each : jobNames) {
      if (regCenter.isExisted(new JobNodePath(each).getServerNodePath(serverIp.get()))) {
        persistDisabledOrEnabledJob(each,serverIp.get(),disabled);
      }
    }
  }
}
