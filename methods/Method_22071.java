private boolean hasOnlineInstances(final String ip){
  for (  String each : jobNodeStorage.getJobNodeChildrenKeys(InstanceNode.ROOT)) {
    if (each.startsWith(ip)) {
      return true;
    }
  }
  return false;
}
