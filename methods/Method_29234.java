@Override public Map<String,Object> getClusterInfoStats(final long appId,final String host,final int port){
  InstanceInfo instanceInfo=instanceDao.getAllInstByIpAndPort(host,port);
  return getClusterInfoStats(appId,instanceInfo);
}
