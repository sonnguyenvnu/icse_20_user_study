@Override public void trigger(final Optional<String> jobName,final Optional<String> serverIp){
  if (jobName.isPresent()) {
    JobNodePath jobNodePath=new JobNodePath(jobName.get());
    for (    String each : regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath())) {
      regCenter.persist(jobNodePath.getInstanceNodePath(each),"TRIGGER");
    }
  }
}
