/** 
 * ?????????
 * @param appId
 * @return
 */
private Set<HostAndPort> getEffectiveInstanceList(long appId){
  Set<HostAndPort> clusterHosts=new HashSet<HostAndPort>();
  List<InstanceInfo> instanceInfos=instanceDao.getInstListByAppId(appId);
  for (  InstanceInfo instance : instanceInfos) {
    if (instance.isOffline()) {
      continue;
    }
    clusterHosts.add(new HostAndPort(instance.getIp(),instance.getPort()));
  }
  return clusterHosts;
}
