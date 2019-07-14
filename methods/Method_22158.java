@Override public void shutdown(final Optional<String> jobName,final Optional<String> serverIp){
  Preconditions.checkArgument(jobName.isPresent() || serverIp.isPresent(),"At least indicate jobName or serverIp.");
  if (jobName.isPresent() && serverIp.isPresent()) {
    JobNodePath jobNodePath=new JobNodePath(jobName.get());
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath())) {
      if (serverIp.get().equals(each.split("@-@")[0])) {
        regCenter.remove(jobNodePath.getInstanceNodePath(each));
      }
    }
  }
 else   if (jobName.isPresent()) {
    JobNodePath jobNodePath=new JobNodePath(jobName.get());
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath())) {
      regCenter.remove(jobNodePath.getInstanceNodePath(each));
    }
  }
 else   if (serverIp.isPresent()) {
    List<String> jobNames=regCenter.getChildrenKeys("/");
    for (    String job : jobNames) {
      JobNodePath jobNodePath=new JobNodePath(job);
      List<String> instances=regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath());
      for (      String each : instances) {
        if (serverIp.get().equals(each.split("@-@")[0])) {
          regCenter.remove(jobNodePath.getInstanceNodePath(each));
        }
      }
    }
  }
}
