/** 
 * ???????
 * @return
 */
private List<HostAndPort> getMasterNodeList(long appId){
  List<HostAndPort> masterNodeList=new ArrayList<HostAndPort>();
  JedisCluster jedisCluster=new JedisCluster(hosts,defaultTimeout);
  Collection<JedisPool> allNodes=jedisCluster.getConnectionHandler().getNodes().values();
  try {
    for (    JedisPool jedisPool : allNodes) {
      String host=jedisPool.getHost();
      int port=jedisPool.getPort();
      if (!redisCenter.isMaster(appId,host,port)) {
        continue;
      }
      masterNodeList.add(new HostAndPort(host,port));
    }
  }
  finally {
    jedisCluster.close();
  }
  return masterNodeList;
}
